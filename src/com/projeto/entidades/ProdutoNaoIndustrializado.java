package com.projeto.entidades;

import com.projeto.validadores.*;

/**
 * Classe que representa um item com Produto nao industrializado que é filha da
 * classe Item. E possui peso do item(peso).
 * 
 * @author Matheus Gusmao
 * @author Igor Silveira
 * @author Rich Ramalho
 * @author Jose Davi
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
	 *            Double que representa o peso do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado que o item foi
	 *            comprado.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 */
	public ProdutoNaoIndustrializado(int numero, String nome, String categoria, double peso, String supermercado,
			double preco) {
		super(numero, nome, categoria, supermercado, preco);
		if (ValidadorItem.validaPeso(peso)) {
			this.peso = peso;
		}
	}

	/**
	 * Metodo que atualiza os atributos do item.
	 * 
	 * @param atributo
	 *            String que representa o nome do atributo a ser alterado.
	 * @param novoValor
	 *            String que representa o novo valor.
	 */
	@Override
	public void AtualizarItem(String atributo, String novoValor) {
		switch (atributo.toLowerCase()) {
		case "nome":
			this.setNome(novoValor);
			break;
		case "categoria":
			this.setCategoria(novoValor);
			break;
		case "kg":
			this.setQuilos(Double.parseDouble(novoValor));
			break;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	/**
	 * Metodo que altera o peso do item.
	 * 
	 * @param novoPeso
	 *            Double o novo peso do item.
	 */
	public void setQuilos(double novoPeso) {
		ValidadorItem.validaSetQuilos(novoPeso);
		this.peso = novoPeso;
	}

	/**
	 * Metodo sobrescrito da classe pai(Item) que retorna a representacao textual do
	 * item.
	 * 
	 * @return String representacao textual com mais detalhes sobre a especificacao
	 *         do item.
	 */
	@Override
	public String toString() {
		return super.toString() + " Preco por quilo: " + super.toStringPrecos();
	}

	/**
	 * Metodo da classe pai(Item) que retorna a representacao textual do item.
	 * 
	 * @param quant
	 *            Double que representa a quantidade dele na lista.
	 * 
	 * @return String contendo a quantidade em kg do item, seu nome e sua categoria.
	 */
	@Override
	public String exibirEmLista(double quant) {
		return ((int) quant) + " " + this.getNome() + ", " + this.getCategoria();
	}

	/**
	 * Metodo de comparacao de dois itens, usando compareTo.
	 * 
	 * @param i
	 *            Item que representa o item que será comparado com o que chama o
	 *            metodo.
	 * @return inteiro referente a o uso do compareTo.
	 */
	@Override
	public int compareTo(Item i) {
		return super.getNome().compareTo(i.getNome());
	}
}