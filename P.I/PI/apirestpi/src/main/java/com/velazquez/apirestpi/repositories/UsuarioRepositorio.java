package com.velazquez.apirestpi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.velazquez.apirestpi.models.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{}