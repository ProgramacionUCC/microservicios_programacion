package com.cogollo.auth;

import com.cogollo.usuario.Usuario;
import com.cogollo.usuario.UsuarioRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarios;
    private final UsuarioDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final long expirationMs;

    public AuthService(AuthenticationManager authenticationManager, UsuarioRepository usuarios,
                       UsuarioDetailsServiceImpl userDetailsService, JwtService jwtService,
                       @org.springframework.beans.factory.annotation.Value("${app.jwt.expiration-ms}") long expirationMs) {
        this.authenticationManager = authenticationManager;
        this.usuarios = usuarios;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.expirationMs = expirationMs;
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.correo(), request.clave()));
        Usuario usuario = usuarios.findByCorreo(request.correo())
            .orElseThrow(() -> new BadCredentialsException("Credenciales invalidas"));
        UserDetails details = userDetailsService.loadUserByUsername(usuario.getCorreo());
        return new LoginResponse(jwtService.generateToken(details, usuario.getRol().name()),
            "Bearer", expirationMs);
    }
}
