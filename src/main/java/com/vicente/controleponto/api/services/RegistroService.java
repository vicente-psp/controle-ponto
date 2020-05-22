package com.vicente.controleponto.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.Registro;
import com.vicente.controleponto.api.repositories.RegistroRepository;

@Service
public class RegistroService implements GenericsOperationsService<Registro> {
	
	@Autowired RegistroRepository repository;
	
	public List<Registro> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Registro find(Long id) {
		Optional<Registro> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public Registro insert(Registro entity) {
		return repository.save(entity);
	}

	@Override
	public void update(Registro entity, Long id) {
		Registro usuario = find(id);
		BeanUtils.copyProperties(entity, usuario, "id");
		repository.save(usuario);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
