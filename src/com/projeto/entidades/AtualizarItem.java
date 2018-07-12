package com.projeto.entidades;

public interface AtualizarItem {

	/**
	 * Metodo abstrato que será implementado nas classes filhas(Altera a
	 * quantidadidade de um item Por quantidade).
	 * 
	 * @param quantidade
	 *            Inteiro que representa a nova quantidade do item.
	 */
	public default void setQuantidade(int quantidade) {
	}
	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera a unidade de
	 * medida do item).
	 * 
	 * @param novaMedida
	 *            String que representa a nova unidade de medida do item.
	 */
	public default void setMedida(String novaMedida) {
	}
	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera a unidade do
	 * item).
	 * 
	 * @param novaUnidade
	 *            Inteiro que representa a nova unidade do item.
	 */
	public default void setUnidade(int novaUnidade) {
	}
	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera o peso do
	 * item).
	 * 
	 * @param novoQuilo
	 *            String que representa o novo peso do item.
	 */
	public default void setQuilos(double novoQuilo) {
	}
	
}
