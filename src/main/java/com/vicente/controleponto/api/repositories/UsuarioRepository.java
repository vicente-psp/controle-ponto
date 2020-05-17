package com.vicente.controleponto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.controleponto.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
