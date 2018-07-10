package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.Item;

public class OrdenaItensPorNome implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		return o1.toString().compareTo(o2.toString());
	}

}
