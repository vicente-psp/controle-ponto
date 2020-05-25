package com.vicente.controleponto.api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vicente.controleponto.api.configs.EnvioEmailService;
import com.vicente.controleponto.api.configs.EnvioEmailService.Mensagem;
import com.vicente.controleponto.api.generics.GenericsOperationsService;
import com.vicente.controleponto.api.models.Permissao;
import com.vicente.controleponto.api.models.SolicitacaoCadastro;
import com.vicente.controleponto.api.models.Usuario;
import com.vicente.controleponto.api.models.enums.TipoUsuario;
import com.vicente.controleponto.api.repositories.UsuarioRepository;
import com.vicente.controleponto.api.utils.UtilMethods;

@Service
public class UsuarioService implements GenericsOperationsService<Usuario> {

	@Autowired UsuarioRepository repository;
	@Autowired PermissaoService permissaoService;
	@Autowired private EnvioEmailService envioEmailService;
	@Autowired private SolicitacaoCadastroService solicitacaoCadastroService;

	public void solicitarCadastro(Usuario entity) {
		if (entity.getId() != null && entity.getId() > 0) {
			Optional<Usuario> optional = repository.findById(entity.getId());
			if (optional.isPresent()) {
				throw new DataIntegrityViolationException("Usuário já cadastrado");
			}
		}
		if (entity.getTipoUsuario() == TipoUsuario.ADM) {
			throw new DataIntegrityViolationException("Tipo de usuário não permitido");
		}
		entity.setSenha(UtilMethods.passwordEncoder(entity.getSenha()));
		Usuario savedEnity = repository.save(entity);

		String generateRandomPassword = UtilMethods.generateRandomPassword();
		SolicitacaoCadastro solicitacaoCadastro = SolicitacaoCadastro.builder()
																								.usuario(savedEnity)
																								.secretKey(generateRandomPassword)
																							.build();
		solicitacaoCadastroService.insert(solicitacaoCadastro);
		
		Mensagem mensagem = Mensagem.builder()
				.assunto("Meu Controle - confirmar de cadastro")
				.corpo("email-confirmar.html")
				.variavel("secretKey", generateRandomPassword)
				.destinatario(entity.getEmail())
				.build();
		envioEmailService.enviar(mensagem);

	}

	public void confirmarCadastro(Long idUsuario, String secretKey) {
		Usuario usuario = find(idUsuario);
		System.out.println("idUsuario => " + idUsuario);
		System.out.println("secretKey => " + secretKey);
		
		SolicitacaoCadastro solicitacaoCadastro = solicitacaoCadastroService.findByUsuario(usuario);
		if (solicitacaoCadastro.getSecretKey().equals(secretKey)) {
			usuario.setAtivo(true);
			update(usuario, idUsuario);
			
			solicitacaoCadastroService.delete(solicitacaoCadastro.getId());
		} else {
			throw new DataIntegrityViolationException("Chave informada não confere");
		}
	}

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
		entity.setSenha(UtilMethods.passwordEncoder(entity.getSenha()));
		return repository.save(entity);
	}

	@Override
	public void update(Usuario entity, Long id) {
		Usuario usuario = find(id);
		entity.setSenha(UtilMethods.passwordEncoder(entity.getSenha()));
		BeanUtils.copyProperties(entity, usuario, "id");
		usuario.setDataAlteracao(new Date());
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
