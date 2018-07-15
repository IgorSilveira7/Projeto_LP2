package com.projeto.entidades;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import com.projeto.ordenacao.OrdenaItensPorNome;
import com.projeto.ordenacao.OrdenarPorCategoria;

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
	
	public void atualizaCompraDeLista(Item item, double novaquantidade) {
		if (this.qntdItens.containsKey(item)) {
			this.qntdItens.replace(item, novaquantidade);
		}
	}
	
	@Override
	public String toString() {
		return this.descritor;
	}

	public void finalizarListaDeCompras(String localDeCompra, double valorFinal) {
		this.localDeCompra = localDeCompra;
		this.valorTotal = valorFinal;
	}
	
	public String getItemLista(int id) {
		if (this.itens.size() <= id) {
			return "";
		}
		this.ordenarPorNomeCategoria();
		Item i = this.itens.get(id);
		double quant = this.qntdItens.get(i);
		return i.getToStringEmLista(quant);
	}
	
	private void ordenarPorNomeCategoria() {
		Collections.sort(this.itens, new OrdenaItensPorNome());
		Collections.sort(this.itens, new OrdenarPorCategoria());
	}

	public void deletaCompraDeLista(Item i) {
		this.itens.remove(i);
		this.qntdItens.remove(i);
	}
}