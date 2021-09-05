package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.PrestamoModelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrestamoRepositorio extends CrudRepository<PrestamoModelo, Long> {
    List<PrestamoModelo> findByidentificacionUsuario(String idUsuario);
}
