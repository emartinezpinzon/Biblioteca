package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.PrestamoModelo;

import java.util.Map;
import java.util.Optional;

public interface IPrestamoServicio {

    Map<String, Object> crearPrestamo(PrestamoModelo prestamo);

    Optional<PrestamoModelo> consultarPrestamoPorId(PrestamoModelo prestamo);

}
