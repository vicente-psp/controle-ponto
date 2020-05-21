package com.vicente.controleponto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.controleponto.api.models.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
