package com.cogollo.restaurante;

import com.cogollo.usuario.Usuario;
import jakarta.persistence.*;

@Entity
public class Restaurante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @ManyToOne(optional = false)
    private Usuario propietario;

    protected Restaurante() { }
    public Restaurante(String nombre, Usuario propietario) { this.nombre = nombre; this.propietario = propietario; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Usuario getPropietario() { return propietario; }
}
