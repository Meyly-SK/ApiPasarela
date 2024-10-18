package com.practica.dto;

import lombok.Data;

@Data
public class PaymentRequest {

	private String paymentMethodNonce;
	private String amount;
}
