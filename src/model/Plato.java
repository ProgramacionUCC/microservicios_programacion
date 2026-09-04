package model;

public class Plato {
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Long idRestaurante;

    private boolean activo = true;

    public Plato() {
        this.activo = true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
