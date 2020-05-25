package com.vicente.controleponto.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.EntradaSaida;
import com.vicente.controleponto.api.repositories.EntradaSaidaRepository;

@Service
public class EntradaSaidaService implements GenericsOperationsService<EntradaSaida> {
	
	@Autowired EntradaSaidaRepository repository;
	@Autowired RegistroService registroService;
	
	public List<EntradaSaida> findAll() {
		return repository.findAll();
	}
	
	@Override
	public EntradaSaida find(Long id) {
		Optional<EntradaSaida> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public EntradaSaida insert(EntradaSaida entity) {
		if (entity.getRegistro().getId() == null || entity.getRegistro().getId() <= 0) {
			throw new EmptyResultDataAccessException(1);
		}
		registroService.find(entity.getRegistro().getId());
		return repository.save(entity);
	}

	@Override
	public void update(EntradaSaida entity, Long id) {
		if (entity.getRegistro().getId() == null || entity.getRegistro().getId() <= 0) {
			throw new EmptyResultDataAccessException(1);
		}
		registroService.find(entity.getRegistro().getId());
		EntradaSaida usuario = find(id);
		BeanUtils.copyProperties(entity, usuario, "id");
		repository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
