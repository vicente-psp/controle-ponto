package com.vicente.controleponto.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.Empresa;
import com.vicente.controleponto.api.repositories.EmpresaRepository;

@Service
public class EmpresaService implements GenericsOperationsService<Empresa> {

	@Autowired EmpresaRepository repository;
	
	public List<Empresa> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Empresa find(Long id) {
		Optional<Empresa> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public Empresa insert(Empresa entity) {
		return repository.save(entity);
	}

	@Override
	public void update(Empresa entity, Long id) {
		Empresa empresa = find(id);
		BeanUtils.copyProperties(entity, empresa, "id");
		repository.save(empresa);
		
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

}
