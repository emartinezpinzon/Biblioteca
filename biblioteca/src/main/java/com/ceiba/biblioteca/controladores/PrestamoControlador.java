package com.ceiba.biblioteca.controladores;


import com.ceiba.biblioteca.modelos.PrestamoModelo;
import com.ceiba.biblioteca.servicios.IPrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prestamo")
public class PrestamoControlador {

    @Autowired
    IPrestamoServicio prestamoServicio;

    @PostMapping()
    public Map<String, ?> crearPrestamo(@RequestBody PrestamoModelo prestamo, HttpServletResponse response) {
        Map<String, Object> respuestaPrestamo = prestamoServicio.crearPrestamo(prestamo);
        Object[] llaves = respuestaPrestamo.keySet().toArray();
        String id = (String) llaves[0];
        String fechaDevolucion = (String) respuestaPrestamo.get(id);

        if (respuestaPrestamo.containsKey("mensaje")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            return respuestaPrestamo;
        }

        respuestaPrestamo.put("id", Integer.parseInt(id));
        respuestaPrestamo.put("fechaMaximaDevolucion", fechaDevolucion);
        respuestaPrestamo.remove(id);

        response.setStatus(HttpServletResponse.SC_OK);
        return respuestaPrestamo;
    }

    @GetMapping(path = "/{id-prestamo}")
    public Optional<PrestamoModelo> getUserById(@PathVariable("id-prestamo") long id) {
        PrestamoModelo prestamo = new PrestamoModelo();
        prestamo.setId(id);

        return prestamoServicio.consultarPrestamoPorId(prestamo);
    }
}
