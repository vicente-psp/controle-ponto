package com.vicente.controleponto.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.Permissao;
import com.vicente.controleponto.api.models.Usuario;
import com.vicente.controleponto.api.repositories.UsuarioRepository;


@Service
public class UsuarioService implements GenericsOperationsService<Usuario> {
	
	@Autowired UsuarioRepository repository;
	@Autowired PermissaoService permissaoService;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Usuario find(Long id) {
		Optional<Usuario> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public Usuario insert(Usuario entity) {
		return repository.save(entity);
	}

	@Override
	public void update(Usuario entity, Long id) {
		Usuario usuario = find(id);
		BeanUtils.copyProperties(entity, usuario, "id");
		repository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public void insertPermission(Long idPermissao, Long idUsuario) {
		List<Permissao> permissoes = new ArrayList<Permissao>();
		Permissao permissao = permissaoService.find(idPermissao);
		permissoes.add(permissao);
		insertPermissions(permissoes, idUsuario);
	}
	
	public void insertPermissions(List<Permissao> permissoes, Long idUsuario) {
		Usuario usuario = find(idUsuario);
		permissoes.forEach(permissao -> {
			usuario.getPermissoes().add(permissaoService.find(permissao.getId()));
		});
		repository.save(usuario);
	}
	
	public void deletePermission(Long idPermissao, Long idUsuario) throws Exception {
		Usuario usuario = find(idUsuario);
		usuario.getPermissoes().removeIf(p -> p.getId() == idPermissao);
		repository.save(usuario);
	}

}
