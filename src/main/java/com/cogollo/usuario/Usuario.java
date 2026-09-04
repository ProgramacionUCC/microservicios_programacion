package com.cogollo.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(nullable = false)
    private String clave;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private Rol rol;

    protected Usuario() { }
    public Usuario(String correo, String clave, Rol rol) {
        this.correo = correo; this.clave = clave; this.rol = rol;
    }
    public Long getId() { return id; }
    public String getCorreo() { return correo; }
    public String getClave() { return clave; }
    public Rol getRol() { return rol; }
}
