package com.cogollo.plato;

import com.cogollo.restaurante.Restaurante;
import jakarta.persistence.*;

@Entity
public class Plato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Integer precio;
    @Column(nullable = false, length = 500)
    private String descripcion;
    @ManyToOne(optional = false)
    private Restaurante restaurante;

    protected Plato() { }
    public Plato(String nombre, Integer precio, String descripcion, Restaurante restaurante) {
        this.nombre = nombre; this.precio = precio; this.descripcion = descripcion; this.restaurante = restaurante;
    }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public Restaurante getRestaurante() { return restaurante; }
    public void update(Integer precio, String descripcion) { this.precio = precio; this.descripcion = descripcion; }
}
