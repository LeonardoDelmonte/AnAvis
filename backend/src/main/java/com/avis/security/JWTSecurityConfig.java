package com.avis.security;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //UserDetailsService è un servizio che implementa un meccanismo di autenticazione? boh
    //Spring Security utilizzerà il servizio userDetailsService per verificare l’esistenza dell’utente e la correttezza della password.
    @Autowired
    private UserDetailsService userDetailsService;

    //leggila questa classe perchè non so che fa 
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    //uso BCrypt per criptare le pw, quindi anche per salvarle dovrò usare questa libreria
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //filter custom richiamato dal metodo configure, ultimo metodo in this class
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
        return new JwtAuthenticationTokenFilter();
    }

     //configurazione Cors per poter consumare le api restful con richieste ajax
     //sarà uguale  per azios? chi lo sa
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("POST, PUT, GET, OPTIONS, DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    /* Attraverso il metodo configure vengono impostate le direttive di autenticazione:
    Non tutti gli endpoint dovranno essere utilizzabili solo da utenti autenticati, 
    alcuni potranno essere pubblici:  vedi endpoint di login o di registrazione. */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        //vedi la classe htppSecurity
                .csrf().disable()
                //unauthorizedHandler è la mia classe gestione per autenticazione non riuscita, forse
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // non abbiamo bisogno di una sessione
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .authorizeRequests()
                //dato che è REST gli elementi statici perchè dovrei averli qui? e perchè 
                //ha diviso public da loro? notStonks
                .antMatchers(
                        //HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/public/**").permitAll()
                //tutte gli altri urls richiedono l'autenticazione
                .anyRequest().authenticated();

        //Viene creato e impostato un filtro di tipo JwtAuthenticationTokenFilter  
        //per scattare prima dei filtri di Spring Security che hanno il compito di verificare l’autenticazione,
        //credo per evitare di creare il coockie automatico di spring security
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        //chi lo sa =)
        httpSecurity.headers().cacheControl();
    }
}