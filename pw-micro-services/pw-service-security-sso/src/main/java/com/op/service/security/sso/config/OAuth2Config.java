package com.op.service.security.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.inMemory()
				.withClient("demo")
				.secret("demosecret")
				.authorizedGrantTypes("authorization_code", "refresh_token", "password")
				.scopes("openid");
	}
}