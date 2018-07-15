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
public abstract class Item implements AtualizarItem, Comparable<Item> {

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
	private Categoria categoria;
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
			this.categoria = escolheCategoria(categoria);
			this.mapaPrecos = new HashMap<>();
			this.mapaPrecos.put(supermercado, preco);
		}
	}
	
	/**
	 * Metodo privado que dado um tipo de produto relaciona-o com uma categoria e a retorna.
	 * 
	 * @param c do tipo String, referente a o tipo do produto em questao.
	 * @return a categoria do item.
	 */
	private Categoria escolheCategoria(String c) {
		switch (c.toLowerCase()) {
			case "higiene pessoal":
				return Categoria.higiene;
			case "limpeza":
				return Categoria.limpeza;
			case "alimento industrializado":
				return Categoria.industrializado;
			case "alimento nao industrializado":
				return Categoria.N_industrializado;
			default:
				return null;
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
			this.categoria = escolheCategoria(novaCategoria);
		}
	}
	
	/**
	 * Metodo que retorna a representacao textual de de um tipo de produto.
	 * 
	 * @return uma String que contem a categoria do item.
	 */
	public String getCategoria() {
		if (this.categoria.equals(Categoria.higiene)) {
			return "higiene pessoal";
		} else  if (this.categoria.equals(Categoria.limpeza)) {
			return "limpeza";
		} else if (this.categoria.equals(Categoria.industrializado)) {
			return "alimento industrializado";
		} else {
			return "alimento nao industrializado";
		}
	}
	
	public Categoria cat() {
		return this.categoria;
	}

	/**
	 * Metodo que dar a representacao textual do item.
	 */
	public String toString() {
		return this.numero + ". " + this.nome + ", " + this.getCategoria() + ",";
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

	/**
	 * Metodo que retorna a representacao textual do nome e categoria de um item;.
	 * 
	 * @return String correspondente a nome e categoria de determinado item.
	 */
	public String getDescricao() {
		return this.nome + this.categoria;
	}
}