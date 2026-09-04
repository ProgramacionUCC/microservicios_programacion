package service;

public class PlatoService {

    // Regla asignada a Jesús Geliz en la HU-04
    public void validarModificacionPlato(Long idRestauranteDelPlato, Long idRestauranteDelUsuario) {
        if (!idRestauranteDelPlato.equals(idRestauranteDelUsuario)) {
            throw new SecurityException("No se permiten modificar platos de otros restaurantes diferentes al propio.");
        }
        System.out.println("Validación exitosa: El plato pertenece a su restaurante.");
    }
}


