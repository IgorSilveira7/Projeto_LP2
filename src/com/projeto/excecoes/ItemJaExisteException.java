package com.projeto.excecoes;

public class ItemJaExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ItemJaExisteException(String msg) {
		super(msg);
	}
	
}
