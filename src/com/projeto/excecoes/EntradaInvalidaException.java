package com.projeto.excecoes;

/**
 * Classe referente a uma entrada invalida.
 * 
 * @author Igor Silveira
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Rich Ramalho
 *
 */
public class EntradaInvalidaException extends RuntimeException {

	/**
	 * Serializacao da classe.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constutor de uma Excecao do tipo entrada invalida.
	 * 
	 * @param msg
	 *            do tipo String, referente a mensagem que devera ser exibida.
	 */
	public EntradaInvalidaException(String msg) {
		super(msg);
	}
}
