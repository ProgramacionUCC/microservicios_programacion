package com.cogollo.api;

import com.cogollo.plato.Plato;
import com.cogollo.plato.PlatoRepository;
import com.cogollo.restaurante.RestauranteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {
    private final PlatoRepository platos;
    private final RestauranteRepository restaurantes;

    public PlatoController(PlatoRepository platos, RestauranteRepository restaurantes) {
        this.platos = platos; this.restaurantes = restaurantes;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Plato crear(@Valid @RequestBody PlatoRequest request, Authentication auth) {
        if (request.restauranteId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El plato debe estar asociado a un restaurante");
        }
        var restaurante = restaurantes.findByIdAndPropietarioCorreo(request.restauranteId(), auth.getName())
            .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException(
                "El restaurante no existe o no pertenece al propietario autenticado"));
        return platos.save(new Plato(request.nombre(), request.precio(), request.descripcion(), restaurante));
    }

    @PutMapping("/{id}")
    public Plato modificar(@PathVariable Long id, @Valid @RequestBody PlatoUpdateRequest request, Authentication auth) {
        var plato = platos.findById(id).orElseThrow();
        if (!plato.getRestaurante().getPropietario().getCorreo().equals(auth.getName())) {
            throw new org.springframework.security.access.AccessDeniedException(
                "El plato no pertenece al propietario autenticado");
        }
        plato.update(request.precio(), request.descripcion());
        return platos.save(plato);
    }
}
