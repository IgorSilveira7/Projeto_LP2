package com.projeto.entidades;

import com.projeto.validadores.*;

/**
 * Classe que representa um item com Produto nao industrializado que é filha da classe Item.
 * E possui peso do item(peso).
 * 
 * @authors Matheus, Rich, Davi
 *
 */
public class ProdutoNaoIndustrializado extends Item {

	/**
	 * Atributo que representa o peso do item.
	 */
	private double peso;
	
	/**
	 * Construtor.
	 * 
	 * @param numero
	 *            Inteiro que representa o id do item.
	 * @param nome
	 *            String que representa o nome do item.
	 * @param categoria
	 *            String que representa a categoria do item.
	 * @param peso 
	 * 			  Double que representa o peso do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado que o item foi
	 *            comprado.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 */
	public ProdutoNaoIndustrializado(int numero, String nome, String categoria, double peso, String supermercado, double preco) {
		super(numero, nome, categoria, supermercado, preco);
		if (Validador.validaPeso(peso)) {
			this.peso = peso;
		}
	}
	
	/**
	 * Metodo sobrescrito da classe pai(Item). Que altera o peso do item.
	 * 
	 * @param novoPeso Double o novo peso do item.
	 */
	@Override
	public void setQuilos(double novoPeso) {
		if (Validador.validaSetPeso(novoPeso)) {
			this.peso = novoPeso;
		}
	}
	
	/**
	 * Metodo sobrescrito da classe pai(Item) que retorna a representacao textual do item.
	 * 
	 * @return String representacao textual com mais detalhes sobre a especificacao do item.
	 */
	@Override
	public String toString() {
		return super.toString() + " Preco por quilo: " + super.toStringPrecos();
	}

	@Override
	public void setQuantidade(int novaquant) {
	}

	@Override
	public void setMedida(String novaMedida) {
	}

	@Override
	public void setUnidade(int novaUnidade) {
	}
}