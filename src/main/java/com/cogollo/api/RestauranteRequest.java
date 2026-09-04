package com.cogollo.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteRequest(@NotBlank String nombre, @NotNull Long propietarioId) { }
