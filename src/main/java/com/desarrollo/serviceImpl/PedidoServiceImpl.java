package com.desarrollo.serviceImpl; // Corrige el nombre del paquete

import com.desarrollo.entity.Pedido;
import com.desarrollo.entity.PedidoEstado;
import com.desarrollo.repository.PedidoRepository;
import com.desarrollo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final com.desarrollo.repository.LibroRepository libroRepository;

    @Override
    public Pedido save(Pedido pedido) {
        // Si el pedido tiene un libro con id, buscar el libro persistido
        if (pedido.getLibro() != null && pedido.getLibro().getIdLibro() != null) {
            com.desarrollo.entity.Libro libro = libroRepository.findById(pedido.getLibro().getIdLibro())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
            pedido.setLibro(libro);
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido update(Long id, Pedido pedido) {
        return pedidoRepository.findById(id)
                .map(existing -> {
                    existing.setFecha(pedido.getFecha());
                    existing.setDescripcion(pedido.getDescripcion());
                    existing.setEstado(pedido.getEstado());
                    existing.setUsuario(pedido.getUsuario());
                    existing.setLibro(pedido.getLibro());
                    return pedidoRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido cambiarEstado(Long id, PedidoEstado nuevoEstado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(nuevoEstado);
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
}