package com.projeto.entidades;

import java.util.HashMap;
import java.util.Map;

public class ProdutoQuantidadeFixa extends Item {
	
	private int quantidade;
	private String medida;
	
	public ProdutoQuantidadeFixa(int numero, String nome, String categoria, 
								 int quantidade, String medida, String supermercado, double preco) {
		super(numero, nome, categoria, supermercado, preco);
		
		if (quantidade < 0) {
			throw new IllegalArgumentException("Erro no cadastro de item: valor de quantidade nao pode ser menor que zero.");
		}
		if (medida.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de item: unidade de medida nao pode ser vazia ou nula.");
		}
		
		this.quantidade = quantidade;
		this.medida = medida;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.quantidade + " " + this.medida + ", Preco: " + this.toStringPrecos();
	}

	@Override
	public void setMedida(String novaMedida) {
		this.medida = novaMedida;
	}
	
	@Override
	public void setQuantidade(int novoValor) {
		this.quantidade = novoValor;
	}

	@Override
	public void setUnidade(int novaUnidade) {
	}

	@Override
	public void setQuilos(double novoQuilos) {
	}
}
