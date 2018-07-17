package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.ListaDeCompras;

public class OrdenaListaDeComprasPorData implements Comparator<ListaDeCompras> {

	@Override
	public int compare(ListaDeCompras o1, ListaDeCompras o2) {
		return o1.getData().toLowerCase().compareTo(o2.getData().toLowerCase());
	}
}
