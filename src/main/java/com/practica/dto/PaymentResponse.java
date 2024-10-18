package com.practica.dto;

import lombok.Data;

@Data
public class PaymentResponse {

	private String message;
	
	public PaymentResponse(String message) {
        this.message = message;
    }
}
