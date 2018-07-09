package com.projeto.excecoes;

public class EntradaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntradaInvalidaException(String msg) {
		super(msg);
	}
}
