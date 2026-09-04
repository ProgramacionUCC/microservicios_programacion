package com.cogollo.api;

import jakarta.validation.constraints.*;

public record PlatoRequest(@NotBlank String nombre, @NotNull @Positive Integer precio,
                           @NotBlank String descripcion, Long restauranteId) { }
