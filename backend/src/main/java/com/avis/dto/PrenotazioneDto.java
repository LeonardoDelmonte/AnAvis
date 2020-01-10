package com.avis.dto;

public class PrenotazioneDto {

    private Long idDataLibera;
    private String emailDonatore;

    public PrenotazioneDto(Long idDataLibera, String emailDonatore) {
        this.idDataLibera = idDataLibera;
        this.emailDonatore = emailDonatore;
    }

    public Long getIdDataLibera() {
        return idDataLibera;
    }

    public void setIdDataLibera(Long idDataLibera) {
        this.idDataLibera = idDataLibera;
    }

    public String getEmailDonatore() {
        return emailDonatore;
    }

    public void setEmailDonatore(String emailDonatore) {
        this.emailDonatore = emailDonatore;
    }

}
