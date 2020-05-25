package com.vicente.controleponto.api.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@Api(value = "Usuários", description = "Usuários")
public class UsuarioController implements GenericsOperationsController<Usuario> {

	@Autowired UsuarioService service;
	@Autowired ApplicationEventPublisher publisher;

	@PostMapping("/solicitar-cadastro")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Solicita um cadastro de um usuário enviando uma chave secreta ao email do usuário sem precisar estar autenticado")
	public void solicitarCadastro(@Valid @RequestBody Usuario entity) {
		service.solicitarCadastro(entity);
	}
	
	@PostMapping("/confirmar-cadastro/{idUsuario}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Confirma um cadastro de um usuário passando uma chave secreta que foi enviado ao email do usuário sem precisar estar autenticado")
	public void confirmarCadastro(@PathVariable Long idUsuario, @RequestParam String secretKey) {
		service.confirmarCadastro(idUsuario, secretKey);
	}
	
	@PostMapping("/reenviar-confirmacao-cadastro")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Confirma um cadastro de um usuário passando uma chave secreta que foi enviado ao email do usuário sem precisar estar autenticado")
	public void reenviarConfirmacaoCadastro(@RequestParam String email) {
		service.reenviarSolicitacaoCadastro(email);
	}
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de usuários")
	public ResponseEntity<List<Usuario>> find() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um usuário")
	@Override
	public ResponseEntity<Usuario> get(@PathVariable Long id) {
		return ResponseEntity.ok(service.find(id));
	}

	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	@PostMapping
	@ApiOperation(value = "Insere um usuário")
	@Override
	public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario entity, HttpServletResponse response) {
		Usuario savedEntity = service.insert(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um usuário")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Override
	public void put(@Valid @RequestBody Usuario entity, @PathVariable Long id) {
		service.update(entity, id);
	}

	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um usuário")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Override
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO_PERMISSAO') and #oauth2.hasScope('write')")
	@PutMapping("/adicionar-permissao/{idUsuario}/{idPermissao}")
	@ApiOperation(value = "Adiciona permissão para um usuário")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addPermission(@PathVariable Long idUsuario, @PathVariable Long idPermissao) {
		service.insertPermission(idPermissao, idUsuario);
	}
	
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO_PERMISSAO') and #oauth2.hasScope('write')")
	@DeleteMapping("/remove-permissao/{idUsuario}/{idPermissao}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Exclui permissão de um usuário")
	public void deletePermission(@PathVariable Long idUsuario, @PathVariable Long idPermissao) throws Exception {
		service.deletePermission(idPermissao, idUsuario);
	}
	
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO_PERMISSAO') and #oauth2.hasScope('write')")
	@PutMapping("/adicionar-permissoes/{id}")
	@ApiOperation(value = "Adiciona uma lista de permissões para um usuário")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addPermissions(@RequestBody List<Permissao> entities, @PathVariable Long id) {
		service.insertPermissions(entities, id);
	}
	
}
