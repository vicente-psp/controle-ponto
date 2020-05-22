package com.vicente.controleponto.api.configs.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
@Profile("oauth-security")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired private AuthenticationManager authenticationManager;
	
	@Autowired private UserDetailsService userDetailsService;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
						.withClient("app-controle-ponto-web")
						.secret("$2a$10$cfxtlSoRn5PUSjjen0MxIeyUvdBOgPeRV.yD9.J2kFnfjK0YPQ4UG")
						.scopes("read", "write")
						.authorizedGrantTypes("password", "refresh_token")
						.accessTokenValiditySeconds(1800)
						.refreshTokenValiditySeconds(3600 * 24)
					.and()
					.withClient("app-controle-ponto-mobile")
					.secret("$2a$10$sgLEXMQAdcd8aWRZc80SV.prd1iaGfZy0jE2iod1pa3chAjBco89W")
					.scopes("read")
					.authorizedGrantTypes("password", "refresh_token")
					.accessTokenValiditySeconds(1800)
					.refreshTokenValiditySeconds(3600 * 24);
		
		super.configure(clients);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints
		.tokenStore(tokenStore())
		.tokenEnhancer(tokenEnhancerChain)
		.reuseRefreshTokens(false)
    .userDetailsService(userDetailsService)
		.authenticationManager(authenticationManager);
		
		super.configure(endpoints);
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("appentrega");
		return accessTokenConverter;
	}

	private TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
	 
}