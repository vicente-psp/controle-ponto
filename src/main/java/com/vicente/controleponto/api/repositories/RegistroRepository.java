package com.vicente.controleponto.api.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.controleponto.api.models.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

	public Optional<Registro> findByData(LocalDate data);
	
}
