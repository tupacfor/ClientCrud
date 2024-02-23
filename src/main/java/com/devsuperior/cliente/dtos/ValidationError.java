package com.devsuperior.cliente.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErros() {
		return erros;
	}
	
	public void addErros(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName, message));
	}

	
}
