package com.projeto;

public class ProdutoNaoIndustrializado extends Item {

	private double quilos;
	
	public ProdutoNaoIndustrializado(int numero, String nome, String categoria, double quilos, String supermercado, double preco) {
		
		super(numero, nome, categoria, supermercado, preco);
		
		this.quilos = quilos;
		
	}
	
	@Override
	public void setQuilos(double novoQuilos) {
		
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
		return super.toString() + ", " + this.toStringPrecos();
	}
	
	private String toStringPrecos() {
		String res = "Preco por quilo: <";
		for (String key : super.mapaPrecos.keySet()) {
			res += key + ", " + super.mapaPrecos.get(key) + ";";
		}
		res += ">";
		return res;
	}

}
