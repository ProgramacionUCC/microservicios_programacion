package com.cogollo.restaurante;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Optional<Restaurante> findByIdAndPropietarioCorreo(Long id, String correo);
}
