package model;

public class Plato {
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Long idRestaurante;

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Long getIdRestaurante() {
        return this.idRestaurante;
    }
}

