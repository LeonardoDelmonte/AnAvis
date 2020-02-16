package com.moc.models;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="prenotazioni",uniqueConstraints = @UniqueConstraint(columnNames={"date","idSedeAvis"}))
public class Prenotazione {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrenotazione;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDonatore", referencedColumnName = "id")
    @JsonIgnoreProperties({"id","password","ruolo","autorizzazioni"})
    private Donatore idDonatore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSedeAvis", referencedColumnName = "id")
    @NotNull
    @JsonIgnoreProperties({"id","password","ruolo","autorizzazioni","listaPrenotazioni"})
    private SedeAvis idSedeAvis;

    @Column @NotNull 
    private Timestamp date;


}