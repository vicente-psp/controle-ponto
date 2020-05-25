package com.vicente.controleponto.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vicente.controleponto.api.event.RecursoCriadoEvent;
import com.vicente.controleponto.api.generics.GenericsOperationsController;
import com.vicente.controleponto.api.models.Permissao;
import com.vicente.controleponto.api.services.PermissaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/permissoes")
@Api(value = "Permissões", description = "Permissões de usuários")
public class PermissaoController implements GenericsOperationsController<Permissao> {

	@Autowired PermissaoService service;
	@Autowired ApplicationEventPublisher publisher;
	
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de permissões")
	public ResponseEntity<List<Permissao>> find() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna uma permissão")
	@Override
	public ResponseEntity<Permissao> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}

	@PostMapping
	@ApiOperation(value = "Insere uma permissão")
	@Override
	public ResponseEntity<Permissao> post(@Valid @RequestBody Permissao entity, HttpServletResponse response) {
		Permissao savedEntity = service.insert(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza uma permissão")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void put(@Valid @RequestBody Permissao entity, @PathVariable Long id) {
		service.update(entity, id);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove uma permissão")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
