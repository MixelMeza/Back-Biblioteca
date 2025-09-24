package com.desarrollo.service;

import com.desarrollo.entity.Libro;
import com.desarrollo.entity.LibroEstado;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Libro save(Libro libro);
    List<Libro> findAll();
    Optional<Libro> findById(Long id);
    Libro update(Long id, Libro libro);
    void delete(Long id);
    Libro cambiarEstado(Long id, LibroEstado nuevoEstado);
}