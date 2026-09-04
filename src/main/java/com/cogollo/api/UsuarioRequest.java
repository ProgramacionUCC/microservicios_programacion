package com.cogollo.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(@Email @NotBlank String correo, @NotBlank String clave) { }
