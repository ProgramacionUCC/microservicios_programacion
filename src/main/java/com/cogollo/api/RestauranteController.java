package com.cogollo.api;

import com.cogollo.restaurante.*;
import com.cogollo.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {
    private final RestauranteRepository restaurantes;
    private final UsuarioRepository usuarios;

    public RestauranteController(RestauranteRepository restaurantes, UsuarioRepository usuarios) {
        this.restaurantes = restaurantes; this.usuarios = usuarios;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante crear(@Valid @RequestBody RestauranteRequest request) {
        var propietario = usuarios.findById(request.propietarioId())
            .orElseThrow(() -> new IllegalArgumentException("Propietario no encontrado"));
        return restaurantes.save(new Restaurante(request.nombre(), propietario));
    }
}
