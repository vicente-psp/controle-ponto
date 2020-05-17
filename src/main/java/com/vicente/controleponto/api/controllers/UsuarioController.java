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
import com.vicente.controleponto.api.models.Usuario;
import com.vicente.controleponto.api.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(value = "Usuários")
public class UsuarioController implements GenericsOperationsController<Usuario> {

	@Autowired UsuarioService service;
	@Autowired ApplicationEventPublisher publisher;
	
	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	@ApiOperation(value = "Retorna uma lista de usuários")
	public ResponseEntity<List<Usuario>> find() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um usuário")
	@Override
	public ResponseEntity<Usuario> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}

	@PostMapping
	@ApiOperation(value = "Insere um usuário")
	@Override
	public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario entity, HttpServletResponse response) {
		Usuario savedEntity = service.insert(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um usuário")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void put(@Valid @RequestBody Usuario entity, @PathVariable Long id) {
		service.update(entity, id);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um usuário")
	@Override
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PutMapping("/adicionar-permissao/{idUsuario}/{idPermissao}")
	@ApiOperation(value = "Adiciona uma permissão para um usuário")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addPermission(@PathVariable Long idUsuario, @PathVariable Long idPermissao) {
		service.insertPermission(idPermissao, idUsuario);
	}
	
	@DeleteMapping("/remove-permissao/{idUsuario}/{idPermissao}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Exclui uma permissão de um usuário")
	public void deletePermission(@PathVariable Long idUsuario, @PathVariable Long idPermissao) throws Exception {
		service.deletePermission(idPermissao, idUsuario);
	}
	
	@PutMapping("/adicionar-permissoes/{id}")
	@ApiOperation(value = "Adiciona uma lista de permissões para um usuário")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addPermissions(@RequestBody List<Permissao> entities, @PathVariable Long id) {
		service.insertPermissions(entities, id);
	}
	
}
