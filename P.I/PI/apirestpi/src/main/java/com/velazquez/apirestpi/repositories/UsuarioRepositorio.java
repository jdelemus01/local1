package com.velazquez.apirestpi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Usuario;
import java.util.Optional;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
}
