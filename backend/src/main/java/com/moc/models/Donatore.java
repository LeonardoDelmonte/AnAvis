package com.moc.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moc.models.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;




/**
 * Utente->Donatore
 */

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("DONATORE")

public class Donatore extends Utente{ 

    @Column@NotNull@NotBlank
    private String nome;
    @Column@NotNull@NotBlank
    private String cognome;
    @Column
    private String citta,professione;
    @Column
    private int anni;
    @Column
    private Boolean abilitaDonazione;

    @OneToMany(mappedBy="idDonatore",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"idDonatore"})
    private List<Prenotazione> listaPrenotazioni;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    private Modulo modulo;

        
}