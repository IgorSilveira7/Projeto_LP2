package com.projeto.entidades;

import java.util.*;

public abstract class Item {
	
	private int numero;
	private String nome;
	private String categoria;
	protected Map<String, Double> mapaPrecos;
	
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
	
	public void adicionaPrecoItem(String localDeCompra, double preco) {
		if (localDeCompra.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		}
		if (preco < 0.0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: preco de item invalido.");
		}
		
		this.mapaPrecos.put(localDeCompra, preco);
	}
	
	public void setNome(String novoValor) {
		this.nome = novoValor;
	}
	
	public void setCategoria(String novoValor) {
		if (!(categoria.equals("alimento industrializado") || categoria.equals("alimento nao industrializado")
				|| categoria.equals("limpeza") || categoria.equals("higiene pessoal"))) {
			throw new IllegalArgumentException("Erro na atualizacao de item: categoria nao existe.");
		}
		this.categoria = novoValor;
	}
	
	public abstract void setQuantidade(int novaquant);
	
	public abstract void setMedida(String novaMedida);
	
	public abstract void setUnidade(int novaUnidade);
	
	public abstract void setQuilos(double novoQuilos);
	
	public String toString() {
		return this.numero + ". " + this.nome + ", " + this.categoria + ",";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

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