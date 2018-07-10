package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.Item;

public class OrdenaItensPorCategoria implements Comparator<Item> {
	
	/**
	 * Classe responsavel por ordenar dois objetos por meio de sua categoria.
	 * 
	 * @author Matheus Gusmao
	 */
	@Override
	public int compare(Item o1, Item o2) {
		return o1.getCategoria().compareTo(o2.getCategoria());
	}

}
