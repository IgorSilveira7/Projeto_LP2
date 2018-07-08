package com.projeto;

import java.util.*;

public abstract class Item {
	
	private int numero;
	private String nome;
	private String categoria;
	protected Map<String, Double> mapaPrecos;
	
	public Item(int numero, String nome, String categoria, String supermercado, double preco) {
		this.numero = numero;
		this.nome = nome;
		this.categoria = categoria;
		this.mapaPrecos = new HashMap<>();
		this.mapaPrecos.put(supermercado, preco);
		
	}
	
	public void adicionaPrecoItem(String localDeCompra, double preco) {
		this.mapaPrecos.put(localDeCompra, preco);
	}
	
	public void setNome(String novoValor) {
		this.nome = novoValor;
	}
	
	public void setCategoria(String novoValor) {
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
