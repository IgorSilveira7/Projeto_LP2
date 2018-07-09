package com.projeto.entidades;

public class ProdutoPorUnidade extends Item {

	private int unidade;
	
	public ProdutoPorUnidade(int numero, String nome, String categoria, int unidade, String supermercado, double preco) {
		
		super(numero, nome, categoria, supermercado, preco);
		
		if (unidade < 0) {
			throw new IllegalArgumentException("Erro no cadastro de item: valor de unidade nao pode ser menor que zero.");
		}
		
		this.unidade = unidade;
		
	}
	
	@Override
	public void setUnidade(int novaUnidade) {
		if (novaUnidade < 0) {
			throw new IllegalArgumentException("Erro na atualizacao de item: valor de quantidade nao pode ser menor que zero.");
		}
		
		this.unidade = novaUnidade;
		
	}

	@Override
	public void setQuantidade(int novaquant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMedida(String novaMedida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setQuilos(double novoQuilos) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.toStringPrecos();
	}
	
	private String toStringPrecos() {
		String res = "Preco: <";
		for (String key : super.mapaPrecos.keySet()) {
			res += key + ", R$ " + String.format("%.2f", super.mapaPrecos.get(key)) + ";";
		}
		res += ">";
		return res;
	}
	
}
