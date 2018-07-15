package com.projeto.excecoes;

/**
 * Classe referente a um item ja existente na lista de compras.
 * 
 * @author  Igor Silveira
 * 			Jose Davi
 * 			Matheus Gusmao
 * 			Rich Ramalho
 *
 */
public class ItemJaExisteException extends RuntimeException {

	/**
	 * Serializacao da classe.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor de um item ja existente na lista, gerando assim uma excecao.
	 * 
	 * @param msg do tipo String, referente a mensagem que devera ser exibida.
	 */
	public ItemJaExisteException(String msg) {
		super(msg);
	}
	
}
