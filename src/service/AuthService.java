package service;

public class AuthService {

    public void validarCredenciales(String usuario, String password) {
        if (!usuario.equals("admin") || !password.equals("123")) {
            throw new SecurityException("Usuario o contraseña incorrectos.");
        }
        System.out.println("Login exitoso.");
    }
}