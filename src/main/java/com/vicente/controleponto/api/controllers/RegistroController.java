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
import com.vicente.controleponto.api.models.Registro;
import com.vicente.controleponto.api.services.RegistroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/registros")
@Api(value = "Registros", description = "Registros de ponto")
public class RegistroController implements GenericsOperationsController<Registro> {
	
	@Autowired RegistroService service;
	@Autowired ApplicationEventPublisher publisher;
	
	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	@ApiOperation(value = "Retorna uma lista de registros")
	public ResponseEntity<List<Registro>> find() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um registro")
	@Override
	public ResponseEntity<Registro> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}

	@PostMapping
	@ApiOperation(value = "Insere um registro")
	@Override
	public ResponseEntity<Registro> post(@Valid @RequestBody Registro entity, HttpServletResponse response) {
		Registro savedEntity = service.insert(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um registro")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void put(@Valid @RequestBody Registro entity, @PathVariable Long id) {
		service.update(entity, id);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um registro")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
