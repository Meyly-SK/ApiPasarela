package com.practica.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Environment;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.practica.config.BraintreeConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	BraintreeConfig config;
	
	public BraintreeGateway getGateway(){
		return new BraintreeGateway(
		Environment.SANDBOX,
		config.getMerchanId(),
		config.getPublicKey(),
		config.getPrivateKey()
		);
	};
	
	public String getToken(){
		ClientTokenRequest request = new ClientTokenRequest();
		return getGateway().clientToken().generate(request);
	};
	
	public String processPayment(String amount, String paymentMethodNonce) throws Exception{
		TransactionRequest request = new TransactionRequest()
				.amount(new BigDecimal(amount))
				.paymentMethodNonce(paymentMethodNonce)
				.options()
				.submitForSettlement(true)
				.done();
		Result<Transaction> result = getGateway().transaction().sale(request);
		
		if(result.isSuccess()) {
			Transaction transaction = result.getTarget();
			return transaction.getId();
		}else {
			throw new Exception("Error al procesar el pago: " + result.getMessage());
		}
	}
}























