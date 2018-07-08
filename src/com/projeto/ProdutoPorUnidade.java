package com.projeto;

public class ProdutoPorUnidade extends Item {

	private int unidade;
	
	public ProdutoPorUnidade(int numero, String nome, String categoria, int unidade, String supermercado, double preco) {
		
		super(numero, nome, categoria, supermercado, preco);
		
		this.unidade = unidade;
		
	}
	
	@Override
	public void setUnidade(int novaUnidade) {
		
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
