package com.carRental.oAuth2Server.security;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration

public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService detailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		
		
		//clients.jdbc(dataSource())
		clients.inMemory()
		.withClient("user-microservice")
		.secret(passwordEncoder.encode("thisissecret"))
		.authorizedGrantTypes("refresh_token","password","client_credentials")
		.scopes("webclient","mobileclient");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints.authenticationManager(authenticationManager).userDetailsService(detailsService);
	}
	
/*	 
	  public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl("jdbc:postgresql://localhost:5432/users");
	    dataSource.setUsername("koud");
	    dataSource.setPassword("12345");
	    return dataSource;
	  }*/
	
		  @Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		    oauthServer.allowFormAuthenticationForClients();
		  }
		  
		  
			@Bean
			public PasswordEncoder passwordEncoder() {
			    String idForEncode = "bcrypt";
			    Map<String, PasswordEncoder> encoderMap = new HashMap<>();
			    encoderMap.put(idForEncode, new BCryptPasswordEncoder());
			    return new DelegatingPasswordEncoder(idForEncode, encoderMap);
			}
		

}
