import model.Propietario;
import model.Restaurante;
import repository.PropietarioRepository;
import repository.RestauranteRepository;
import service.PropietarioService;
import service.RestauranteService;

import java.time.LocalDate;

public class Main {
    // Aqui empieza el programa.
    public static void main(String[] args) {
        // Donde se guardan.
        PropietarioRepository propietarioRepo = new PropietarioRepository();
        RestauranteRepository restauranteRepo = new RestauranteRepository();
        // El que registra usando el repo.
        PropietarioService propietarioService = new PropietarioService(propietarioRepo);
        RestauranteService restauranteService = new RestauranteService(restauranteRepo, propietarioRepo);

        // Un propietario con sus 7 datos.
        Propietario p1 = new Propietario("Carlos", "Perez", "12345678", "+573005698325", LocalDate.of(1990, 5, 10), "carlos@mail.com", "abc123");

        // Lo guardamos (ahi se encripta la clave) y lo mostramos.
        propietarioService.registrarPropietario(p1);
        System.out.println("Propietario: " + p1);
        // Mostramos la clave ya encriptada para comprobar.
        System.out.println("Clave guardada: " + p1.getClave());

        // Caso OK 1: con + (13 chars total)
        probar(restauranteService, new Restaurante("El Buen Sabor", "900123456", "Calle 10 # 5-20", "+573005698325", "https://example.com/logo.png", "12345678"));

        // Caso OK 2: sin + y 13 digitos (validaba mal antes del fix, ahora debe pasar)
        probar(restauranteService, new Restaurante("Sabor 2", "900123457", "Calle 11 # 5-20", "3005698325123", "https://example.com/logo2.png", "12345678"));

        // Caso FAIL: nombre solo numeros
        probar(restauranteService, new Restaurante("12345", "900123458", "Calle 12", "+573005698326", "https://example.com/logo.png", "12345678"));

        // Caso FAIL: NIT no numerico
        probar(restauranteService, new Restaurante("Sabor 3", "ABC123", "Calle 13", "+573005698327", "https://example.com/logo.png", "12345678"));

        // Caso FAIL: telefono 14 chars
        probar(restauranteService, new Restaurante("Sabor 4", "900123459", "Calle 14", "+5730056983251", "https://example.com/logo.png", "12345678"));

        // Caso FAIL: propietario inexistente
        probar(restauranteService, new Restaurante("Sabor 5", "900123460", "Calle 15", "+573005698328", "https://example.com/logo.png", "99999999"));

        System.out.println("Total restaurantes guardados: " + restauranteRepo.obtenerTodos().size());
    }

    private static void probar(RestauranteService service, Restaurante r) {
        try {
            service.crearRestaurante(r);
            System.out.println("OK creado: " + r);
        } catch (IllegalArgumentException e) {
            System.out.println("FAIL esperado [" + r.getNombre() + "]: " + e.getMessage());
        }
    }
}
