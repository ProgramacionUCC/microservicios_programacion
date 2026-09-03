package service;

import model.Propietario;
import repository.PropietarioRepository;

import java.util.List;
import org.mindrot.BCrypt;

// registra y lista propietarios.
public class PropietarioService {
    // Repository que usa para guardar.
    private PropietarioRepository propietarioRepository;

    // Al crearlo hay que pasarle el repository.
    public PropietarioService(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    // Revisa obligatorios, encripta la clave con bcrypt y lo guarda.
    public void registrarPropietario(Propietario propietario) {
        if (vacio(propietario.getNombre())) throw new IllegalArgumentException("Nombre obligatorio");
        if (vacio(propietario.getApellido())) throw new IllegalArgumentException("Apellido obligatorio");
        if (vacio(propietario.getDocumentoDeIdentidad())) throw new IllegalArgumentException("Documento obligatorio");
        if (vacio(propietario.getCelular())) throw new IllegalArgumentException("Celular obligatorio");
        if (vacio(propietario.getFechaNacimiento())) throw new IllegalArgumentException("Fecha nacimiento obligatoria");
        if (vacio(propietario.getCorreo())) throw new IllegalArgumentException("Correo obligatorio");
        if (vacio(propietario.getClave())) throw new IllegalArgumentException("Clave obligatoria");
        String encriptada = BCrypt.hashpw(propietario.getClave(), BCrypt.gensalt());
        propietario.setClave(encriptada);
        propietarioRepository.guardarPropietario(propietario);
        System.out.println("Propietario registrado");
    }

    // Dice si un texto viene vacio o nulo.
    private boolean vacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public List<Propietario> listarPropietarios() {
        return propietarioRepository.getPropietarios();
    }
}
