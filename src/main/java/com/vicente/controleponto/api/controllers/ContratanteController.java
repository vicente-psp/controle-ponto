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
import com.vicente.controleponto.api.models.Contratante;
import com.vicente.controleponto.api.services.ContratanteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contratantes")
@Api(value = "Contratantes")
public class ContratanteController implements GenericsOperationsController<Contratante> {

	@Autowired ContratanteService service;
	@Autowired ApplicationEventPublisher publisher;

	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CONTRATANTE') and #oauth2.hasScope('read')")
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de contratantes")
	public ResponseEntity<List<Contratante>> find() throws Exception {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	public ResponseEntity<Contratante> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Contratante> post(Contratante entity, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Contratante entity, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
