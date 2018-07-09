package com.projeto.entidades;

import com.projeto.validadores.Validador;

/**
 * Classe que representa um item com Produto por quantidade fixa que é filha da classe Item.
 * E possui a quantidade do item(quantidade) e a unidade de medida do item(medida).
 * 
 * @authors Rich, Davi
 *
 */
public class ProdutoQuantidadeFixa extends Item {
	
	/**
	 * Atributo que representa a quantidade de itens.
	 */
	private int quantidade;
	/**
	 * Atributo que representa a unidade de medida para o item.
	 */
	private String medida;
	
	/**
	 * Construtor.
	 * 
	 * @param numero
	 *            Inteiro que representa o id do item.
	 * @param nome
	 *            String que representa o nome do item.
	 * @param categoria
	 *            String que representa a categoria do item.
	 * @param quantidade 
	 * 			  Inteiro que representa a quantidade do item.
	 * @param medida
	 * 			  String que representa a unidade de medida do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado que o item foi
	 *            comprado.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 */
	public ProdutoQuantidadeFixa(int numero, String nome, String categoria, 
								 int quantidade, String medida, String supermercado, double preco) {
		super(numero, nome, categoria, supermercado, preco);
		if (Validador.validaProdutoQuantFixa(quantidade, medida)) {
			this.quantidade = quantidade;
			this.medida = medida;
		}
	}
	
	/**
	 * Metodo sobrescrito da classe pai(Item) que retorna a representacao textual do item.
	 * 
	 * @return String representacao textual com mais detalhes sobre a especificacao do item.
	 */
	@Override
	public String toString() {
		return super.toString() + " " + this.quantidade + " " + this.medida + ", Preco: " + this.toStringPrecos();
	}

	/**
	 * Metodo sobrescrito da classe pai(Item) que ira alterar a unidade de medida do item.
	 */
	@Override
	public void setMedida(String novaMedida) {
		if (Validador.validaSetMedida(novaMedida)) {
			this.medida = novaMedida;
		}
	}
	
	/**
	 * Metodo sobrescrito da classe pai(Item) que ira alterar a quantidade do item.
	 */
	@Override
	public void setQuantidade(int novoValor) {
		if (Validador.validaSetQuantidade(novoValor)) {
			this.quantidade = novoValor;
		}
	}

	@Override
	public void setUnidade(int novaUnidade) {
	}

	@Override
	public void setQuilos(double novoQuilos) {
	}
}
