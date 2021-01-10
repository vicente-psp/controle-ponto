package com.vicente.controleponto.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.controleponto.api.generics.GenericsOperationsController;
import com.vicente.controleponto.api.models.Pessoa;
import com.vicente.controleponto.api.services.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pessoas")
@Api(value = "Pessoas")
public class PessoaController implements GenericsOperationsController<Pessoa> {

	@Autowired PessoaService service;
	@Autowired ApplicationEventPublisher publisher;

	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de pessoas")
	public ResponseEntity<List<Pessoa>> find() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	public ResponseEntity<Pessoa> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Pessoa> post(Pessoa entity, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Pessoa entity, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
