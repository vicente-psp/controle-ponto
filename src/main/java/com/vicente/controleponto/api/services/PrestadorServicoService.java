package com.vicente.controleponto.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.PrestadorServico;
import com.vicente.controleponto.api.repositories.PrestadorServicoRepository;

@Service
public class PrestadorServicoService implements GenericsOperationsService<PrestadorServico> {

	@Autowired PrestadorServicoRepository repository;

	public List<PrestadorServico> findAll() throws Exception {
		/*
		Usuario usuario = UtilMethods.getUsuarioLogado().orElseThrow();
		if (Objects.equals(usuario.getTipoUsuario(), TipoUsuario.ADM)
				|| Objects.equals(usuario.getTipoUsuario(), TipoUsuario.COL)) {			
			return repository.findAll()
					.stream()
					.map(obj -> {
						obj.setUsuario(null);
						return obj;
					})
					.collect(Collectors.toList());
		}
		if (Objects.equals(usuario.getTipoUsuario(), TipoUsuario.COM)) {
			return repository.findAllByUsuario(usuario)
					.stream()
					.map(obj -> {
						obj.setUsuario(null);
						return obj;
					})
					.collect(Collectors.toList());
		}
		throw new Exception("Tipo de usuário inválido");
		*/
		return repository.findAll();
	}

	@Override
	public PrestadorServico find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrestadorServico insert(PrestadorServico entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(PrestadorServico entity, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
