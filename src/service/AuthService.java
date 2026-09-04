package service;

public class AuthService {

    public void validarCredenciales(String usuario, String password) {
        if (!usuario.equals("admin") || !password.equals("123")) {
            throw new SecurityException("Acceso denegado: Usuario o contraseña incorrectos.");
        }
        System.out.println("Autenticación exitosa para el endpoint.");
    }
}
