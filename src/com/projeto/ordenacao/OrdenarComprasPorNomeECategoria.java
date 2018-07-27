package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.Compra;

public class OrdenarComprasPorNomeECategoria implements Comparator<Compra>{

	@Override
	public int compare(Compra o1, Compra o2) {
		if (o1.getItem().getCategoriaEnum().equals(o2.getItem().getCategoriaEnum())) {
			return o1.getItem().getNome().compareTo(o2.getItem().getNome());
		} else {
			return o1.getItem().getCategoriaEnum().compareTo(o2.getItem().getCategoriaEnum());
		}
	}
}
