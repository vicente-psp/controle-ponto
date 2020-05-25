package com.vicente.controleponto.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.SolicitacaoCadastro;
import com.vicente.controleponto.api.models.Usuario;
import com.vicente.controleponto.api.repositories.SolicitacaoCadastroRepository;

@Service
public class SolicitacaoCadastroService implements GenericsOperationsService<SolicitacaoCadastro> {
	
	@Autowired SolicitacaoCadastroRepository repository;
	
	public List<SolicitacaoCadastro> findAll() {
		return repository.findAll();
	}
	
	public SolicitacaoCadastro findByUsuario(Usuario usuario) {
		Optional<SolicitacaoCadastro> optional = repository.findByUsuario(usuario);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}
	
	@Override
	public SolicitacaoCadastro find(Long id) {
		Optional<SolicitacaoCadastro> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optional.get();
	}

	@Override
	public SolicitacaoCadastro insert(SolicitacaoCadastro entity) {
		return repository.save(entity);
	}

	@Override
	public void update(SolicitacaoCadastro entity, Long idUsuario) {
		SolicitacaoCadastro solicitacaoCadastro = find(idUsuario);
		BeanUtils.copyProperties(entity, solicitacaoCadastro, "id", "usuario");
		repository.save(solicitacaoCadastro);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
