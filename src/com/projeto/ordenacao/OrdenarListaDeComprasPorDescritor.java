package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.ListaDeCompras;

public class OrdenarListaDeComprasPorDescritor implements Comparator<ListaDeCompras> {

	@Override
	public int compare(ListaDeCompras l1, ListaDeCompras l2) {
		return l1.getDescritor().toLowerCase().compareTo(l2.getDescritor().toLowerCase());
	}

}
