package com.carRental.oAuth2Server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
	@Value("${signing.key}")
	private String signingkey;
	
	public String getJwtSigningKey() {
		return signingkey;
	}
}