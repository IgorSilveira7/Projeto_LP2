package com.projeto;

import java.util.HashMap;
import java.util.Map;

public class ProdutoQuantidadeFixa extends Item {
	
	private int quantidade;
	private String medida;
	
	public ProdutoQuantidadeFixa(int numero, String nome, String categoria, 
								 int quantidade, String medida, String supermercado, double preco) {
		super(numero, nome, categoria, supermercado, preco);
		this.quantidade = quantidade;
		this.medida = medida;
	}
	
	public void setQuantidade(int novoValor) {
		this.quantidade = novoValor;
	}
	
	public void setMedida(String novoValor) {
		this.medida = novoValor;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.quantidade + " " + this.medida + ", " + this.toStringPrecos();
	}
	
	private String toStringPrecos() {
		String res = "Preco: <";
		for (String key : super.mapaPrecos.keySet()) {
			res += key + ", " + super.mapaPrecos.get(key) + ";";
		}
		res += ">";
		return res;
	}

	@Override
	public void setMedida(int novaMedida) {
	}

	@Override
	public void setUnidade(int novaUnidade) {
	}

	@Override
	public void setQuilos(int novoQuilos) {
	}
}
