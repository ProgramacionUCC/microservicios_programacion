package com.cogollo.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoRequest(@NotNull Long platoId, @NotNull @Positive Integer cantidad) { }
