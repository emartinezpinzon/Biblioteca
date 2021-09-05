package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.PrestamoModelo;
import com.ceiba.biblioteca.repositorios.IPrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrestamoServicio implements  IPrestamoServicio {

    @Autowired
    private IPrestamoRepositorio prestamoRepositorio;

    @Override
    public Map<String, String> crearPrestamo(PrestamoModelo prestamo) {
        return null;
    }

    @Override
    public PrestamoModelo consultarPrestamoPorId(PrestamoModelo prestamo) {
        return null;
    }
}
