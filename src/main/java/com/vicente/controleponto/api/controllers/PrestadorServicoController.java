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
import com.vicente.controleponto.api.models.PrestadorServico;
import com.vicente.controleponto.api.services.PrestadorServicoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prestador-servicos")
@Api(value = "Prestador de serviços")
public class PrestadorServicoController implements GenericsOperationsController<PrestadorServico> {

	@Autowired PrestadorServicoService service;
	@Autowired ApplicationEventPublisher publisher;

	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRESTADOR_SERVICO') and #oauth2.hasScope('read')")
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de prestadores de serviço")
	public ResponseEntity<List<PrestadorServico>> find() throws Exception {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	public ResponseEntity<PrestadorServico> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PrestadorServico> post(PrestadorServico entity, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(PrestadorServico entity, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
