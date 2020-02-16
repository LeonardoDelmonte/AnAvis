package com.moc.dto;

import com.moc.models.CentroTrasfusione;
import com.moc.models.Donatore;
import com.moc.models.SedeAvis;

import lombok.Data;

/**
 * RegistrazioneDto
 */

@Data
public class RegistrazioneDto {

    private final Donatore donatore;
    private final SedeAvis sedeAvis;
    private final CentroTrasfusione centroTrasfusione;
    
}