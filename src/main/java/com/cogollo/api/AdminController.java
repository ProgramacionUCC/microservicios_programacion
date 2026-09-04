package com.cogollo.api;

import com.cogollo.usuario.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propietarios")
public class AdminController {
    private final UsuarioRepository usuarios;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UsuarioRepository usuarios, PasswordEncoder passwordEncoder) {
        this.usuarios = usuarios; this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse crearPropietario(@Valid @RequestBody UsuarioRequest request) {
        if (usuarios.findByCorreo(request.correo()).isPresent()) {
            throw new org.springframework.web.server.ResponseStatusException(
                HttpStatus.CONFLICT, "Ya existe un usuario con ese correo");
        }
        return UsuarioResponse.from(usuarios.save(new Usuario(
            request.correo(), passwordEncoder.encode(request.clave()), Rol.PROPIETARIO)));
    }
}
