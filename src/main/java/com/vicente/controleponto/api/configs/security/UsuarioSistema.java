package com.vicente.controleponto.api.configs.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.vicente.controleponto.api.models.Usuario;

import lombok.Getter;

public class UsuarioSistema extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

}
