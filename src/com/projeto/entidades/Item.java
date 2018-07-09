package com.projeto.entidades;

import java.util.*;

/**
 * Classe que representa um item no sistema. Cada item possui um numero, nome,
 * categoria e um mapa de precos.
 * 
 * @authores Igor, Rich
 * 
 *
 */
public abstract class Item {

	/**
	 * Atributo que representa o id do item.
	 */
	private int numero;
	/**
	 * Atributo que representa o nome do item.
	 */
	private String nome;
	/**
	 * Atributo que representa a categoria que o item esta classificado.
	 */
	private String categoria;
	/**
	 * Atributo que representa o mapa de precos desse item.
	 */
	protected Map<String, Double> mapaPrecos;

	/**
	 * Construtor.
	 * 
	 * @param numero
	 *            Inteiro que representa o id do item.
	 * @param nome
	 *            String que representa o nome do item.
	 * @param categoria
	 *            String que representa a categoria do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado que o item foi
	 *            comprado.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 */
	public Item(int numero, String nome, String categoria, String supermercado, double preco) {
		if (nome.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de item: nome nao pode ser vazio ou nulo.");
		}
		if (categoria.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de item: categoria nao pode ser vazia ou nula.");
		}
		if (!(categoria.equals("alimento industrializado") || categoria.equals("alimento nao industrializado")
				|| categoria.equals("limpeza") || categoria.equals("higiene pessoal"))) {
			throw new IllegalArgumentException("Erro no cadastro de item: categoria nao existe.");
		}
		if (supermercado.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de item: local de compra nao pode ser vazio ou nulo.");
		}
		if (preco < 0.0) {
			throw new IllegalArgumentException("Erro no cadastro de item: preco de item invalido.");
		}

		this.numero = numero;
		this.nome = nome;
		this.categoria = categoria;
		this.mapaPrecos = new HashMap<>();
		this.mapaPrecos.put(supermercado, preco);

	}

	/**
	 * Metodo responsavel por adicionar um novo supermercado e um novo preco ao
	 * item.
	 * 
	 * @param localDeCompra
	 *            String que representa o nome do supermercado.
	 * @param preco
	 *            Double que representa o preco do item.
	 */
	public void adicionaPrecoItem(String localDeCompra, double preco) {
		if (localDeCompra.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		}
		if (preco < 0.0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: preco de item invalido.");
		}

		this.mapaPrecos.put(localDeCompra, preco);
	}

	/**
	 * Metodo que altera o nome do item.
	 * 
	 * @param novoValor
	 *            String que represnta o novo nome do item.
	 */
	public void setNome(String novoValor) {
		this.nome = novoValor;
	}

	/**
	 * Metodo que altera o tipo da categoria.
	 * 
	 * @param novoValor
	 *            String que representa a nova categoria do item.
	 */
	public void setCategoria(String novoValor) {
		if (!(categoria.equals("alimento industrializado") || categoria.equals("alimento nao industrializado")
				|| categoria.equals("limpeza") || categoria.equals("higiene pessoal"))) {
			throw new IllegalArgumentException("Erro na atualizacao de item: categoria nao existe.");
		}
		this.categoria = novoValor;
	}

	/**
	 * Metodo abstrato que será implementado nas classes filhas(Altera a
	 * quantidadidade de um item Por quantidade).
	 * 
	 * @param novaQuant
	 *            Inteiro que representa a nova quantidade do item.
	 */
	public abstract void setQuantidade(int novaQuant);

	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera a unidade de
	 * medida do item).
	 * 
	 * @param novaMedida
	 *            String que representa a nova unidade de medida do item.
	 */
	public abstract void setMedida(String novaMedida);

	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera a unidade do
	 * item).
	 * 
	 * @param novaMedida
	 *            Inteiro que representa a nova unidade do item.
	 */
	public abstract void setUnidade(int novaUnidade);

	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera o peso do
	 * item).
	 * 
	 * @param novaMedida
	 *            String que representa o novo peso do item.
	 */
	public abstract void setQuilos(double novoQuilos);

	/**
	 * Metodo que dar a representacao textual do item.
	 */
	public String toString() {
		return this.numero + ". " + this.nome + ", " + this.categoria + ",";
	}

	/**
	 * Metodo que gera o hashCode do item.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Metodo que compara esse objeto com outro levando em consideração sua
	 * categoria e nome.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}