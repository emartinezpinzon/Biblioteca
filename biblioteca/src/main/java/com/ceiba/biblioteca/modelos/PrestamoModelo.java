package com.ceiba.biblioteca.modelos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "prestamo")
public class PrestamoModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Size(max = 10)
    private String isbn;

    @Size(max = 10)
    private String idUser;

    @Size(max = 1)
    private char tipoUsuario;

    private Date fechaDevolucion;
}
