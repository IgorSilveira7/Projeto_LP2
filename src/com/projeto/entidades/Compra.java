package com.projeto.entidades;

import java.io.Serializable;

/**
 * Classe que representa uma compra no sistema. Cada compra possui um item e a
 * quantidade dele a ser comprada.
 * 
 * @author Igor Silveira
 * @author Rich Ramalho
 * @author Matheus Gusmao
 * @author Jose Davi
 * 
 */
public class Compra implements Serializable{

	private static final long serialVersionUID = 6147362482159025197L;
	/**
	 * Atributo referente ao item.
	 */
	private Item item;
	/**
	 * Atributo referente a quantidade desse item.
	 */
	private int qntd;

	/**
	 * Construtor.
	 * 
	 * @param item
	 *            Item a ser adicionado.
	 * @param qntd
	 *            Inteiro referente a quantidade deste item.
	 */
	public Compra(Item item, int qntd) {
		this.item = item;
		this.qntd = qntd;
	}

	/**
	 * Metodo que retorna a quantidade desse item.
	 * 
	 * @return Inteiro referente a quantidade desse item.
	 */
	public int getQntd() {
		return this.qntd;
	}

	/**
	 * Metodo que retorna o item.
	 * 
	 * @return Item referente ao item.
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Metodo que aumenta a quantidade do item.
	 * 
	 * @param valorAdicionado
	 *            Inteiro que representa a quantidade a ser adicionada.
	 */
	public void aumentaQntdItem(int valorAdicionado) {
		this.qntd += valorAdicionado;
	}
	
	public void atualizaQuantItem(String operacao, int valor) {
		if (operacao.equals("diminui")) {
			this.qntd -= valor;
		} else if (operacao.equals("adiciona")) {
			this.qntd += valor;
		}
	}

	/**
	 * Metodo que diminui a quantidade do item.
	 * 
	 * @param valorSubtraido
	 *            Inteiro que representa a quantidade a ser subtraida.
	 */
	public void diminuiQntdItem(int valorSubtraido) {
		this.qntd -= valorSubtraido;
	}

	/**
	 * Metodo que retorna a representacao textual da compra.
	 */
	@Override
	public String toString() {
		return this.item.exibirEmLista(this.qntd);
	}
}
