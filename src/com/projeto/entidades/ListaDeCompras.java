package com.projeto.entidades;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;



public class ListaDeCompras {
	
	private List<Item> itens;
	private TreeMap<Item, Double> qntdItens;
	private String descritor;
	private Date data;
	private double valorTotal;
	private String localDeCompra;
	
	public ListaDeCompras(String descritor) {
		this.descritor = descritor;
		this.itens = new ArrayList<>();
		this.qntdItens = new TreeMap<>();
		this.data = new Date();
	}
	
	public int adicionaCompraALista(double qntd, Item item) {
		this.qntdItens.put(item, qntd);
		this.itens.add(item);
		
		return this.itens.size();
	}
	
	public String pesquisaCompraEmLista(Item item) {
		return item.getToStringEmLista(this.qntdItens.get(item));
	}
	
}