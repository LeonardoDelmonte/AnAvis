package com.moc.services;

import com.moc.models.Donatore;
import com.moc.models.Modulo;

public interface ModuloInterface {

    Modulo ottieniModulo(Donatore donatore);
    Modulo modificaModulo(Donatore donatore, Modulo modulo);

}
