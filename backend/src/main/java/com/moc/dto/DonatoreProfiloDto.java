package com.moc.dto;

import lombok.Data;

/**
 * DonatoreProfiloDto
 */
@Data
public class DonatoreProfiloDto {

    private String nome,cognome,professione,citta,email;
    private Boolean abilitaDonazione;
    private int anni;
    
}