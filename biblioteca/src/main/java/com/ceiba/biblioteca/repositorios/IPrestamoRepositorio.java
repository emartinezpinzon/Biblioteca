package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.modelos.PrestamoModelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrestamoRepositorio extends CrudRepository<PrestamoModelo, Long> {

}
