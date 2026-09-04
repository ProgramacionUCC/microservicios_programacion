package com.cogollo.api;

import com.cogollo.usuario.Usuario;

public record UsuarioResponse(Long id, String correo, String rol) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getCorreo(), usuario.getRol().name());
    }
}