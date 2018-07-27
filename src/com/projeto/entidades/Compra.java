package com.projeto.entidades;

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
public class Compra {

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
