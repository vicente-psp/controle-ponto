package com.vicente.controleponto.api.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.controleponto.api.generics.GenericsOperationsController;
import com.vicente.controleponto.api.generics.Long;
import com.vicente.controleponto.api.models.Empresa;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/empresas")
@Api(value = "Empresas" , description = "Empresas")
public class EmpresaController implements GenericsOperationsController<Empresa> {

	@Autowired EmpresaService service;
	@Autowired ApplicationEventPublisher publisher;
	
	@Override
	public ResponseEntity<Empresa> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Empresa> post(Empresa entity, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Empresa entity, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
