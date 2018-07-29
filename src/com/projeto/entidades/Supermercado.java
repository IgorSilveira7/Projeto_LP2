package com.projeto.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.projeto.ordenacao.OrdenarComprasPorNomeECategoria;

/**
 * Classe responsavel pela logica de um supermercado que possui os itens que o
 * usuario deseja comprar naquele momento, ordenar os itens pelo nome e pela
 * categoria(em dois niveis) e calcula quanto o usuario iria gastar com tais
 * itens comprando nesse supermercado.
 * 
 * @author Igor Silveira
 * @author Rich Ramalho
 * @author Jose Davi
 * @author Matheus Gusmao 
 *
 */
public class Supermercado {

	/**
	 * Atributo que representa o nome do supermercado.
	 */
	private String nome;
	/**
	 * Atributo que representa o valor a ser gastos para compras esses itens nesse
	 * supermercado.
	 */
	private double valorTotal;
	/**
	 * Atributo que representa todas as compras que pretendem ser analisadas.
	 */
	private List<Compra> itens;

	/**
	 * Construtor.
	 * 
	 * @param nome
	 *            String que representa o nome do supermercado.
	 */
	public Supermercado(String nome) {
		this.nome = nome;
		this.itens = new ArrayList<>();
	}

	/**
	 * Metodo que adiciona uma compra que deseja ser comprada nesse supermercado.
	 * 
	 * @param compra
	 *            Compra referente a compra.
	 */
	public void addItem(Compra compra) {
		this.itens.add(compra);
		this.valorTotal += (compra.getQntd() * compra.getItem().getMapaPrecos().get(this.nome));
	}

	/**
	 * Metodo que retorna o valor total a ser gasto para realizar as compras nesse
	 * supermercado.
	 * 
	 * @return Double referente ao valor total da compra.
	 */
	public double getValorTotal() {
		return this.valorTotal;
	}

	/**
	 * Metodo que retorna a representacao textual de um item(Que sera ordenado pela
	 * categoria, em caso de empate pelo nome).
	 * 
	 * @param posicaoLista
	 *            Inteiro referente a posicao do item na lista.
	 * @return String referente a representacao textual do item.
	 */
	public String getItem(int posicaoLista) {
		Collections.sort(this.itens, new OrdenarComprasPorNomeECategoria());
		if (this.itens.size() <= posicaoLista - 1) {
			return "";
		}
		return "- " + this.itens.get(posicaoLista - 1).toString();
	}

	/**
	 * Metodo que retorna a representacao textual de um supermercado.
	 */
	public String toString() {
		return this.nome + ": R$ " + String.format("%.2f", this.valorTotal);
	}
}