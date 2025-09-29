package com.desarrollo.serviceImpl;

import com.desarrollo.entity.Libro;
import com.desarrollo.entity.LibroEstado;
import com.desarrollo.repository.LibroRepository;
import com.desarrollo.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro update(Long id, Libro libro) {
        return libroRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(libro.getTitulo());
                    existing.setAutor(libro.getAutor());
                    existing.setEstado(libro.getEstado());
                    
                    existing.setCategoria(libro.getCategoria());
                    existing.setCodigo(libro.getCodigo());
                    existing.setPrecio(libro.getPrecio());
                    existing.setDescripcion(libro.getDescripcion());

                    return libroRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    @Override
    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Libro cambiarEstado(Long id, LibroEstado nuevoEstado) {
        return libroRepository.findById(id)
                .map(libro -> {
                    libro.setEstado(nuevoEstado);
                    return libroRepository.save(libro);
                })
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }
}