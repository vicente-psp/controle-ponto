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
	
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de empresas")
	public ResponseEntity<List<Empresa>> find() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@Override
	public ResponseEntity<Empresa> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
		return null;
	}

	@Override
	public ResponseEntity<Empresa> post(@Valid @RequestBody Empresa entity, HttpServletResponse response) {
		Empresa savedEntity = service.insert(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@Override
	public void put(@Valid @RequestBody Empresa entity,@PathVariable Long id) {
		service.update(entity, id);
		
	}

	@Override
	public void delete(@PathVariable Long id) {
		service.delete(id);
		
	}

}
