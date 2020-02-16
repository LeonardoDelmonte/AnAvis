package com.moc.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moc.models.Utente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Utente->SedeAvis
 */

@Entity
@Table(name = "sedeAvis", uniqueConstraints = @UniqueConstraint(columnNames = { "comune" }))
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("SEDE_AVIS")

public class SedeAvis extends Utente {

    @Column
    private String regione, comune, provincia;
    @Column
    private String indirizzo, telefono;

    @OneToMany(mappedBy = "idSedeAvis", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "idSedeAvis" })
    private List<Prenotazione> listaPrenotazioni;

}