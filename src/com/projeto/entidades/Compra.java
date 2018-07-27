package com.projeto.entidades;

public class Compra {
	
	private Item item;
	private int qntd;
	
	public Compra(Item item, int qntd) {
		this.item = item;
		this.qntd = qntd;
	}
	
	public int getQntd() {
		return this.qntd;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public void aumentaQntdItem(int valorAdicionado) {
		this.qntd += valorAdicionado;
	}
	
	public void diminuiQntdItem(int valorSubtraido) {
		this.qntd -= valorSubtraido;
	}
	
	@Override
	public String toString() {
		return this.item.exibirEmLista(this.qntd);
	}
}
