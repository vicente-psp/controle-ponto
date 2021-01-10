package com.vicente.controleponto.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vicente.controleponto.api.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("SELECT p FROM Pessoa p"
			+ " JOIN FETCH p.usuario"
			+ " LEFT JOIN FETCH p.contratante")
	List<Pessoa> findAll();

}
