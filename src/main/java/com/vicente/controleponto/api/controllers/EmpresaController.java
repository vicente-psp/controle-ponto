package com.vicente.controleponto.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.controleponto.api.event.RecursoCriadoEvent;
import com.vicente.controleponto.api.generics.GenericsOperationsController;
import com.vicente.controleponto.api.models.Empresa;
import com.vicente.controleponto.api.services.EmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/empresas")
@Api(value = "Empresas", description = "Empresas")
public class EmpresaController implements GenericsOperationsController<Empresa> {

	@Autowired EmpresaService service;
	@Autowired ApplicationEventPublisher publisher;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de empresas")
	public ResponseEntity<List<Empresa>> find() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	public ResponseEntity<Empresa> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}

	@Override
	public ResponseEntity<Empresa> post(@Valid @RequestBody Empresa entity, HttpServletResponse response) {
		Empresa savedEntity = service.insert(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@Override
	public void put(@Valid @RequestBody Empresa entity, @PathVariable Long id) {
		service.update(entity, id);
	}

	@Override
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
