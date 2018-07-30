package com.projeto.excecoes;

public class ArquivoNaoExiste extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ArquivoNaoExiste(String msg) {
		super(msg);
	}
	
}
