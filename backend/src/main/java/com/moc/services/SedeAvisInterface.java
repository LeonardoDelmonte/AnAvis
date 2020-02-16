package com.moc.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.moc.dto.SedeAvisProfiloDto;
import com.moc.models.Prenotazione;
import com.moc.models.SedeAvis;

/**
 * DateSedeAvisInterface
 */
public interface SedeAvisInterface {

    Map<String, List<Prenotazione>> ottieni(SedeAvis sedeAvis);

    SedeAvis findByEmail(String email);

    SedeAvis findByComune(String comune);

    void modificaProfilo(SedeAvis sedeAvis, SedeAvisProfiloDto profilo);

    SedeAvisProfiloDto ottieniProfilo(SedeAvis sedeAvis);

    Set<String> getAllRegioni();

    Set<String> getAllProvince(String regione);

    Set<String> getComune(String provincia);

    List<String> getAllComuni();

}