package com.vicente.controleponto.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vicente.controleponto.api.models.Contratante;
import com.vicente.controleponto.api.models.Usuario;

public interface ContratanteRepository extends JpaRepository<Contratante, Long> {

	/*
	@Query("SELECT p FROM Pessoa p"
			+ " JOIN FETCH p.usuario"
			+ " LEFT JOIN FETCH p.contratante")
	List<Contratante> findAll();
	
	@Query("SELECT p FROM Pessoa p"
			+ " JOIN FETCH p.usuario"
			+ " LEFT JOIN FETCH p.contratante"
			+ " WHERE p.usuario = :usuario")
	List<Contratante> findAllByUsuario(@Param("usuario") Usuario usuario);
	*/

}
