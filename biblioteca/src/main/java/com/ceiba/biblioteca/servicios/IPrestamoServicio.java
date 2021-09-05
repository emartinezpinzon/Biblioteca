package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.PrestamoModelo;

import java.util.Map;

public interface IPrestamoServicio {

    Map<String, Object> crearPrestamo(PrestamoModelo prestamo);

    PrestamoModelo consultarPrestamoPorId(PrestamoModelo prestamo);

}
