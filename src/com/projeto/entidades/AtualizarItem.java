package com.projeto.entidades;

import com.projeto.validadores.ValidadorItem;

/**
 * Criacao de uma interface, relacionada a Item.
 * 
 * @author  Igor Silveira
 * 			Jose Davi
 * 			Matheus Gusmao
 * 			Rich Ramalho
 */
public interface AtualizarItem {
	
	/**
	 * Metodo que será implementado nas classes que implementar a interface.
	 * 
	 * @param quant Double que representa a quantidade do item em uma lista.
	 * @return String que representa a representacao textual do item.
	 */
	public String getToStringEmLista(double quant);

	/**
	 * Metodo default que será implementado nas classes filhas(Altera a
	 * quantidadidade de um item Por quantidade).
	 * 
	 * @param quantidade
	 *            Inteiro que representa a nova quantidade do item.
	 */
	public default void setQuantidade(int quantidade) {
		ValidadorItem.validaSetQuantidade(quantidade);
	}
	/**
	 * Metodo default que seŕa implementado nas classes filhas(Altera a unidade de
	 * medida do item).
	 * 
	 * @param novaMedida
	 *            String que representa a nova unidade de medida do item.
	 */
	public default void setMedida(String novaMedida) {
		ValidadorItem.validaSetMedida(novaMedida);
	}
	/**
	 * Metodo default que seŕa implementado nas classes filhas(Altera a unidade do
	 * item).
	 * 
	 * @param novaUnidade
	 *            Inteiro que representa a nova unidade do item.
	 */
	public default void setUnidade(int novaUnidade) {
		ValidadorItem.validaSetUnidade(novaUnidade);
	}
	/**
	 * Metodo default que seŕa implementado nas classes filhas(Altera o peso do
	 * item).
	 * 
	 * @param novoQuilo
	 *            String que representa o novo peso do item.
	 */
	public default void setQuilos(double novoQuilo) {
		ValidadorItem.validaSetQuilos(novoQuilo);
	}
	
}
