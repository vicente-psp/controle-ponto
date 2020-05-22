package com.vicente.controleponto.api.configs.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("controleponto")
public class AppProterty {

	@Getter @Setter
	private String originAllow = "http://localhost:8080";
	
	@Getter @Setter
	private String googleMapsApiKey;
	
	@Getter
	private final Seguranca seguranca = new Seguranca();

	@Getter @Setter
	public static class Seguranca {
		private boolean enableHttps;
	}
}