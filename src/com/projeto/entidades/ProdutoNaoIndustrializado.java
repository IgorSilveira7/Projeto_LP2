package com.projeto.entidades;

public class ProdutoNaoIndustrializado extends Item {

	private double quilos;
	
	public ProdutoNaoIndustrializado(int numero, String nome, String categoria, double quilos, String supermercado, double preco) {
		
		super(numero, nome, categoria, supermercado, preco);
		
		if (quilos < 0.0) {
			throw new IllegalArgumentException("Erro no cadastro de item: valor de quilos nao pode ser menor que zero.");
		}
		
		this.quilos = quilos;
		
	}
	
	@Override
	public void setQuilos(double novoQuilos) {
		if (novoQuilos < 0.0) {
			throw new IllegalArgumentException("Erro na atualizacao de item: valor de quilos nao pode ser menor que zero.");
		}
		this.quilos = novoQuilos;
		
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
	public void setUnidade(int novaUnidade) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.toStringPrecos();
	}
	
	private String toStringPrecos() {
		String res = "Preco por quilo: <";
		for (String key : super.mapaPrecos.keySet()) {
			res += key + ", R$ " + String.format("%.2f", super.mapaPrecos.get(key)) + ";";
		}
		res += ">";
		return res;
	}

}
