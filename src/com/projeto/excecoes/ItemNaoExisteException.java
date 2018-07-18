package com.projeto.excecoes;

public class ItemNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ItemNaoExisteException(String msg) {
		super(msg);
	}

}
