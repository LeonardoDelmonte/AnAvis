package com.moc.components;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.moc.dto.RangeDateDto;

import org.springframework.stereotype.Component;

/**
 * GestioneDateService formatta date e po vedo
 */
@Component
public class ControllaDate {

    public List<Timestamp> formattaRangeDate(RangeDateDto rangeDto) {

        if (rangeDto == null)
            throw new NullPointerException("rangeDate NULL");

        List<Timestamp> list = new ArrayList<>();

        if (!compareTo(rangeDto))
            return list;

        Timestamp dataIniziale = rangeDto.getDataIniziale();
        Timestamp dataFinale = rangeDto.getDataFinale();

        // vedere se entrambe le date sono quarti d'ora

        while (!dataIniziale.equals(dataFinale)) {
            list.add(dataIniziale);
            dataIniziale = new Timestamp(dataIniziale.getTime() + TimeUnit.MINUTES.toMillis(15));
        }

        return list;

    }

    public boolean controllaCorrettezzaDate(RangeDateDto rangeDto) {
        return compareTo(rangeDto);
    }

    private boolean compareTo(RangeDateDto rangeDto) {
        if (rangeDto.getDataIniziale().compareTo(rangeDto.getDataFinale()) > 0)
            return false;
        return true;
    }

}