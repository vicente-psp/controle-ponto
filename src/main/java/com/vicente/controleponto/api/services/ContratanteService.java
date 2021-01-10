package com.vicente.controleponto.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.Contratante;
import com.vicente.controleponto.api.repositories.ContratanteRepository;

@Service
public class ContratanteService implements GenericsOperationsService<Contratante> {

	@Autowired ContratanteRepository repository;

	public List<Contratante> findAll() throws Exception {
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
	public Contratante find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contratante insert(Contratante entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Contratante entity, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
