package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.ListaDeCompras;

/**
 * Classe responsavel por comparar duas listas de compras pelo descritor.
 * 
 * @author Rich Ramalho
 * @author Igor Silveira
 *
 */
public class OrdenarListaDeComprasPorDescritor implements Comparator<ListaDeCompras> {

	@Override
	public int compare(ListaDeCompras l1, ListaDeCompras l2) {
		return l1.getDescritor().toLowerCase().compareTo(l2.getDescritor().toLowerCase());
	}

}
