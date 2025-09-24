package com.desarrollo.service;

import com.desarrollo.entity.Pedido;
import com.desarrollo.entity.PedidoEstado;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Pedido save(Pedido pedido);
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Pedido update(Long id, Pedido pedido);
    void delete(Long id);
    Pedido cambiarEstado(Long id, PedidoEstado nuevoEstado);
}
