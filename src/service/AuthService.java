package service;

public class AuthService {

    public void verificarSesion(boolean estaLogueado) {
        if (!estaLogueado) {
            throw new SecurityException("Acceso denegado: Debe iniciar sesión.");
        }
        System.out.println("Acceso concedido al sistema.");
    }
}