package com.vicente.controleponto.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.controleponto.api.models.SolicitacaoCadastro;
import com.vicente.controleponto.api.models.Usuario;

public interface SolicitacaoCadastroRepository extends JpaRepository<SolicitacaoCadastro, Long> {
	
	public Optional<SolicitacaoCadastro> findByUsuario(Usuario usuario);
	
}
