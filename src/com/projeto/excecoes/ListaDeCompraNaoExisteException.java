package com.projeto.excecoes;

public class ListaDeCompraNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ListaDeCompraNaoExisteException(String msg) {
		super(msg);
	}

}
