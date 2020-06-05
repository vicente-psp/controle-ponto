package com.vicente.controleponto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicente.controleponto.api.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
