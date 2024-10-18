package com.practica.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.dto.ErrorResponse;
import com.practica.dto.PaymentRequest;
import com.practica.dto.PaymentResponse;
import com.practica.service.PaymentService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/token")
	public ResponseEntity<Map<String, String>> getToken() {
	    String token = paymentService.getToken();
	    Map<String, String> response = new HashMap<>();
	    response.put("token", token);
	    return ResponseEntity.ok(response);
	}	
	@PostMapping("/procesar-pago")
	public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
	    try {
	        String transactionId = paymentService.processPayment(paymentRequest.getAmount(), paymentRequest.getPaymentMethodNonce());
	        
	        return ResponseEntity.ok(new PaymentResponse("Pago exitoso. ID de la transacción: " + transactionId));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(new ErrorResponse("Error al procesar: " + e.getMessage()));
	    }
	}
}
















