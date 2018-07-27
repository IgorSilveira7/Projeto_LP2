package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.Item;

/**
 * Classe responsavel por ordenar itens em favor do seu preco.
 * 
 * @author Jose Davi
 * @author Matheus Gusmao
 */
public class OrdenarItensPorMenorPreco implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getMenorPreco() > o2.getMenorPreco()) {
			return 1;
		} else if (o1.getMenorPreco() == o2.getMenorPreco()) {
			return 0;
		} else {
			return -1;
		}
	}

}
