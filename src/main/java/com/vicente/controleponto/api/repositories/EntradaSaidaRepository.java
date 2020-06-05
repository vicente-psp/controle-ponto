package com.vicente.controleponto.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.controleponto.api.models.EntradaSaida;
import com.vicente.controleponto.api.models.Registro;

public interface EntradaSaidaRepository extends JpaRepository<EntradaSaida, Long> {
	
	public List<EntradaSaida> findByRegistro(Registro registro);

}
