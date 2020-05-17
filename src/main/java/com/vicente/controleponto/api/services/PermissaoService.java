package com.vicente.controleponto.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.Permissao;
import com.vicente.controleponto.api.repositories.PermissaoRepository;

@Service
public class PermissaoService implements GenericsOperationsService<Permissao> {

	@Autowired PermissaoRepository repository;
	
	public List<Permissao> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Permissao find(Long id) {
		Optional<Permissao> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public Permissao insert(Permissao entity) {
		entity.setDescricao(entity.getDescricao().toUpperCase());
		if (!entity.getDescricao().startsWith("ROLE_")) {
			entity.setDescricao("ROLE_" + entity.getDescricao());
		}
		return repository.save(entity);
	}

	@Override
	public void update(Permissao entity, Long id) {
		entity.setDescricao(entity.getDescricao().toUpperCase());
		if (!entity.getDescricao().startsWith("ROLE_")) {
			entity.setDescricao("ROLE_" + entity.getDescricao());
		}
		Permissao usuario = find(id);
		BeanUtils.copyProperties(entity, usuario, "id");
		repository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
