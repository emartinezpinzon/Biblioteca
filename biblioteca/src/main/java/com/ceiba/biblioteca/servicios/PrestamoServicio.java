package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.modelos.PrestamoModelo;
import com.ceiba.biblioteca.repositorios.IPrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PrestamoServicio implements  IPrestamoServicio {

    @Autowired
    private IPrestamoRepositorio prestamoRepositorio;

    @Override
    public Map<String, Object> crearPrestamo(PrestamoModelo prestamo) {
        String idUsuario = prestamo.getIdentificacionUsuario();
        byte tipoUsuario = prestamo.getTipoUsuario();
        String fechaDevolucion = this.calcularFechaDevolucion(tipoUsuario);
        Map<String, Object> respuesta = new LinkedHashMap<>();

        if (tipoUsuario > 3){
            respuesta.put("mensaje",
                    "Tipo de usuario no permitido en la biblioteca");

            return respuesta;
        }

        if (tipoUsuario == 3 &&
                !consultarPrestamoPorUsuario(idUsuario).isEmpty()){
            respuesta.put("mensaje",
                    "El usuario con identificación "
                            + idUsuario +
                            " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");

            return respuesta;
        }

        prestamo.setFechaMaximaDevolucion(fechaDevolucion);
        PrestamoModelo prestamoModelo = prestamoRepositorio.save(prestamo);
        respuesta.put(String.valueOf(prestamoModelo.getId()), fechaDevolucion);

        return respuesta;
    }

    @Override
    public Optional<PrestamoModelo> consultarPrestamoPorId(@NotNull PrestamoModelo prestamo) {
        return prestamoRepositorio.findById(prestamo.getId());
    }

    private List<PrestamoModelo> consultarPrestamoPorUsuario(String idUsuario) {
        return prestamoRepositorio.findByidentificacionUsuario(idUsuario);
    }

    private String calcularFechaDevolucion(byte tipoUsuario) {
        LocalDate dateNow = LocalDate.now();
        int diasReglamentarios = 0;
        int diasSumar = 0;

        if (tipoUsuario == 1)
            diasReglamentarios = 10;
        else if (tipoUsuario == 2)
            diasReglamentarios = 8;
        else if (tipoUsuario == 3)
            diasReglamentarios = 7;

        while (diasSumar < diasReglamentarios) {
            dateNow = dateNow.plusDays(1);

            if (!(dateNow.getDayOfWeek() == DayOfWeek.SATURDAY || dateNow.getDayOfWeek() == DayOfWeek.SUNDAY))
                ++diasSumar;
        }

        return formatearFecha(dateNow);
    }

    private String formatearFecha(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy");

        return formatter.format(date);
    }
}
