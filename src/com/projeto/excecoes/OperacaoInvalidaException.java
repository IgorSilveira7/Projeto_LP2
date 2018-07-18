package com.projeto.excecoes;

public class OperacaoInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public OperacaoInvalidaException(String msg) {
		super(msg);
	}

}
