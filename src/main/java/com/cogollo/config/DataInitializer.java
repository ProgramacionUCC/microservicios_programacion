package com.cogollo.config;

import com.cogollo.usuario.Rol;
import com.cogollo.usuario.Usuario;
import com.cogollo.usuario.UsuarioRepository;
import com.cogollo.restaurante.Restaurante;
import com.cogollo.restaurante.RestauranteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initAdmin(UsuarioRepository usuarios, RestauranteRepository restaurantes,
                                PasswordEncoder encoder) {
        return args -> {
            if (usuarios.findByCorreo("admin@cogollo.com").isEmpty()) {
                usuarios.save(new Usuario("admin@cogollo.com", encoder.encode("Admin123!"), Rol.ADMINISTRADOR));
            }
            var propietario = usuarios.findByCorreo("propietario@cogollo.com").orElseGet(() ->
                usuarios.save(new Usuario("propietario@cogollo.com", encoder.encode("Owner123!"), Rol.PROPIETARIO)));
            if (restaurantes.count() == 0) {
                restaurantes.save(new Restaurante("Restaurante Demo", propietario));
            }
        };
    }
}