package com.projeto.ordenacao;

import java.util.Comparator;
import com.projeto.entidades.Item;

public class OrdenarPorCategoria implements Comparator<Item> {

	@Override
	public int compare(Item i1, Item i2) {
		return i1.cat().compareTo(i2.cat());
	}
}
