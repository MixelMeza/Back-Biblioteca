package com.desarrollo.entity;



import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDate fecha;
    private String descripcion;
    private String direccion;
    private String nombre;
    private String telefono;
    

    @Enumerated(EnumType.STRING)
    private PedidoEstado estado;

    private String usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;
}
