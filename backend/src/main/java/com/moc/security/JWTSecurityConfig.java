package com.moc.security;

import com.moc.services.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//questa classe si occupa di configurare javaSecurity

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private UtenteService autenticazioneService;
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthTokenFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configurazione AuthenticationManager
		auth.userDetailsService(autenticazioneService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().cors().and()
				// non chiedere autorizzazione per queste richieste
				.authorizeRequests().antMatchers("/public/**").permitAll().and().authorizeRequests()
				// chiedi autorizzazione per queste richieste	
				.antMatchers("/sede/**").hasAuthority("sedeAvis").and().authorizeRequests()
				.antMatchers("/donatore/**").hasAuthority("donatore").and().authorizeRequests()
				.antMatchers("/centro/**").hasAuthority("centroTrasfusione").and().authorizeRequests()
				.antMatchers("/prenotazione/**").hasAuthority("prenotazione").and().authorizeRequests()
				.antMatchers("/vedi-richieste/**").hasAuthority("vedi-richieste").and().authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("admin").and().authorizeRequests().and()
				// definisco un ExceptionEntryPoint 
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				// assicuro un server-side stateless
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// definisco la classe filter che javaSecurity deve usare prima della sua
		httpSecurity.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}