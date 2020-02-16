package com.moc.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moc.models.Utente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;




/**
 * Utente->CentroTrasfusione
 */

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CENTRO_TRASFUSIONE")

public class CentroTrasfusione extends Utente{

    //@OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name="shop_owned_id",nullable=true)
    
    @Column
    private String direttore,indirizzo,ospedale;
    @Column
    private String regione,provincia,comune;

    @OneToMany(mappedBy="idCentroTrasfusione",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"idCentroTrasfusione"})
    private List<Emergenza> listaEmergenze;

        
}