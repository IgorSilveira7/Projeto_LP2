package com.projeto.entidades;

import com.projeto.validadores.ValidadorItem;

/**
 * Classe que representa um item com Produto por quantidade fixa que é filha da
 * classe Item. E possui a quantidade do item(quantidade) e a unidade de medida
 * do item(medida).
 * 
 * @author Rich Ramalho
 * @author Jose Davi
 * @author Igor Silveira
 * @author Matheus Gusmao
 * 
 */
public class ProdutoQuantidadeFixa extends Item {

	private static final long serialVersionUID = -4780434247548458953L;
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
	 *            Inteiro que representa a quantidade do item.
	 * @param medida
	 *            String que representa a unidade de medida do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado que o item foi
	 *            comprado.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 */
	public ProdutoQuantidadeFixa(int numero, String nome, String categoria, int quantidade, String medida,
			String supermercado, double preco) {
		super(numero, nome, categoria, supermercado, preco);
		if (ValidadorItem.validaProdutoQuantFixa(quantidade, medida)) {
			this.quantidade = quantidade;
			this.medida = medida;
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
		case "quantidade":
			this.setQuantidade(Integer.parseInt(novoValor));
			break;
		case "unidade de medida":
			this.setMedida(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	/**
	 * Metodo sobrescrito da classe pai(Item) que ira alterar a unidade de medida do
	 * item.
	 * 
	 * @param novaMedida
	 *            String que representa a nova medida.
	 */
	public void setMedida(String novaMedida) {
		ValidadorItem.validaSetMedida(novaMedida);
		this.medida = novaMedida;
	}

	/**
	 * Metodo sobrescrito da classe pai(Item) que ira alterar a quantidade do item.
	 * 
	 * @param novoValor
	 *            Inteiro que representa o novo valor.
	 */
	public void setQuantidade(int novoValor) {
		ValidadorItem.validaSetQuantidade(novoValor);
		this.quantidade = novoValor;
	}

	/**
	 * Metodo spbrescrito da classe pai(Item) que ira retornar uma String contendo
	 * informacoes_ _sobre a quantidade, nome, categoria e medida de um item.
	 */
	@Override
	public String exibirEmLista(int quant) {
		return quant + " " + this.getNome() + ", " + this.getCategoria() + ", " + this.quantidade + " " + this.medida;
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
		return super.toString() + " " + this.quantidade + " " + this.medida + ", Preco: " + this.toStringPrecos();
	}

	/**
	 * Metodo de comparacao de dois item, utilizando compareTO.
	 */
	@Override
	public int compareTo(Item o) {
		return super.getNome().compareTo(o.getNome());
	}
}