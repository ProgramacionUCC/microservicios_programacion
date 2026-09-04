package com.cogollo.pedido;

import com.cogollo.plato.Plato;
import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String clienteCorreo;
    @ManyToOne(optional = false)
    private Plato plato;
    @Column(nullable = false)
    private Integer cantidad;

    protected Pedido() { }
    public Pedido(String clienteCorreo, Plato plato, Integer cantidad) {
        this.clienteCorreo = clienteCorreo; this.plato = plato; this.cantidad = cantidad;
    }
    public Long getId() { return id; }
    public String getClienteCorreo() { return clienteCorreo; }
    public Plato getPlato() { return plato; }
    public Integer getCantidad() { return cantidad; }
}
