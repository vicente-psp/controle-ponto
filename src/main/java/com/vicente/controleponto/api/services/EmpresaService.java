package com.vicente.controleponto.api.services;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.generics.Long;
import com.vicente.controleponto.api.models.Empresa;
import com.vicente.controleponto.api.repositories.EmpresaRepository;
import com.vicente.controleponto.api.utils.UtilMethods;

public class EmpresaService implements GenericsOperationsService<Empresa> {

	@Autowired EmpresaRepository repository;
	
	public List<Empresa> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Empresa find(Long id) {
		Optional<Empresa> optional = Repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public Empresa insert(Empresa entity) {
		// TODO Auto-generated method stub
		//verificar como criar essa parte
		return repository.save(entity);
	}

	@Override
	public void update(Empresa entity, Long id) {
		Empresa empresa = find(id);
		BeanUtils.copyProperties(entity, empresa, "id");
		usuario.setDataAlteracao(new Date());
		repository.save(usuario);
		
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

}
