package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.Compra;

/**
 * Classe que ordena compras primeiro pela categoria, caso dê empate ele compara pelo nome.
 * 
 * @author Rich Ramalho
 *
 */
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
