package com.projeto.entidades;

import java.util.*;

import com.projeto.validadores.ValidadorItem;

/**
 * Classe abstrata que representa um item no sistema. Cada item possui um numero, nome,
 * categoria e um mapa de precos.
 * 
 * @authors Igor Silveira
 * 			Jose Davi
 * 			Matheus Gusmao
 * 			Rich Ramalho
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
		if (ValidadorItem.validaItem(nome, categoria, supermercado, preco)) {
			this.numero = numero;
			this.nome = nome;
			this.categoria = categoria;
			this.mapaPrecos = new HashMap<>();
			this.mapaPrecos.put(supermercado, preco);
		}
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
		if (ValidadorItem.validaAdicionarPrecoItem(localDeCompra, preco)) {
			this.mapaPrecos.put(localDeCompra, preco);
		}
	}

	/**
	 * Metodo que altera o nome do item.
	 * 
	 * @param novoNome
	 *            String que represnta o novo nome do item.
	 */
	public void setNome(String novoNome) {
		if (ValidadorItem.validaSetNome(novoNome)) {
			this.nome = novoNome;
		}
	}
	
	/**
	 * Metodo que retona o nome do item.
	 * 
	 * @return nome do item.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Metodo que altera o tipo da categoria.
	 * 
	 * @param novaCategoria
	 *            String que representa a nova categoria do item.
	 */
	public void setCategoria(String novaCategoria) {
		if (ValidadorItem.validaSetCategoria(novaCategoria)) {
			this.categoria = novaCategoria;
		}
	}
	
	public String getCategoria() {
		return this.categoria;
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
	 * @param novaUnidade
	 *            Inteiro que representa a nova unidade do item.
	 */
	public abstract void setUnidade(int novaUnidade);

	/**
	 * Metodo abstrato que seŕa implementado nas classes filhas(Altera o peso do
	 * item).
	 * 
	 * @param novoQuilo
	 *            String que representa o novo peso do item.
	 */
	public abstract void setQuilos(double novoQuilo);

	/**
	 * Metodo que dar a representacao textual do item.
	 */
	public String toString() {
		return this.numero + ". " + this.nome + ", " + this.categoria + ",";
	}
	
	/**
	 * Método protegido que retorna a representacao textual do mapa de precos
	 * cadastrado para esse item.
	 * 
	 * @return String representacao textual do mapa de precos.
	 */
	protected String toStringPrecos() {
		String res = "<";
		for (String key : mapaPrecos.keySet()) {
			res += key + ", R$ " + String.format("%.2f", mapaPrecos.get(key)) + ";";
		}
		res += ">";
		return res;
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
	
	/**
	 * Metodo que retorna o menor preco do item.
	 * 
	 * @return valor minimo do mapa de precos correspondente ao menor preco do item.
	 */
	public double getMenorPreco() {		
		return Collections.min(this.mapaPrecos.values());
	}

	public String getDescricao() {
		return this.nome + this.categoria;
	}
}