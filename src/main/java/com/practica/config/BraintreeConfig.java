package com.practica.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BraintreeConfig {

	@Value("${braintree.merchant-id}")
	private String merchanId;
	
	@Value("${braintree.public-key}")
	private String publicKey;
	
	@Value("${braintree.private-key}")
	private String privateKey;
}
