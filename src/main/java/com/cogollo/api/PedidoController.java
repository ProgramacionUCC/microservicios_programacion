package com.cogollo.api;

import com.cogollo.pedido.*;
import com.cogollo.plato.PlatoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoRepository pedidos;
    private final PlatoRepository platos;

    public PedidoController(PedidoRepository pedidos, PlatoRepository platos) {
        this.pedidos = pedidos; this.platos = platos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido realizar(@Valid @RequestBody PedidoRequest request, Authentication auth) {
        var plato = platos.findById(request.platoId()).orElseThrow();
        return pedidos.save(new Pedido(auth.getName(), plato, request.cantidad()));
    }
}
