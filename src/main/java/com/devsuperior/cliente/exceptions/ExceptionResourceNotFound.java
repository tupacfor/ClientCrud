package com.devsuperior.cliente.exceptions;

@SuppressWarnings("serial")
public class ExceptionResourceNotFound extends RuntimeException {
	public ExceptionResourceNotFound(String msg) {
		super(msg);
	}
}
