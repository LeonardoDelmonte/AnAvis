package com.moc.models;


import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



/**
 * Utente questa classe rappresenta il modello per qualsiasi tipo di utente che
 * esiste in questo "sito"
 */

@Entity
@Table(name="utente",uniqueConstraints = @UniqueConstraint(columnNames={"email"}))
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="DISC",discriminatorType = DiscriminatorType.STRING)

public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column@NotNull@NotBlank
    private String email;
    @Column@NotNull@NotBlank
    private String password;
    @Column@NotNull@NotBlank
    private String ruolo;
    @Column@Lob@NotNull
    private ArrayList<SimpleGrantedAuthority> autorizzazioni;
       
}