package model;

// HU-01: datos del propietario.
public class Propietario {
    private String nombre;
    private String apellido;
    private String documentoDeIdentidad;
    private String celular;
    private String fechaNacimiento;
    private String correo;
    private String clave;

    public Propietario(String nombre, String apellido, String documentoDeIdentidad, String celular, String fechaNacimiento, String correo, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documentoDeIdentidad = documentoDeIdentidad;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.clave = clave;
    }

    // Se necesitan para revisar que no vengan vacios y para encriptar.
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumentoDeIdentidad() {
        return documentoDeIdentidad;
    }

    public String getCelular() {
        return celular;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Propietario{nombre='" + nombre + "', correo='" + correo + "'}";
    }
}
