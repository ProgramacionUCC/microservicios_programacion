package com.cogollo.auth;

import com.cogollo.usuario.Usuario;
import com.cogollo.usuario.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarios;

    public UsuarioDetailsServiceImpl(UsuarioRepository usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarios.findByCorreo(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return User.withUsername(usuario.getCorreo())
            .password(usuario.getClave())
            .authorities(new SimpleGrantedAuthority(usuario.getRol().name()))
            .build();
    }
}
