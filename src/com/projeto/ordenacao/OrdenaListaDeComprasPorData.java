package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.ListaDeCompras;

/**
 * Classe responsavel por comparar duas listas de compras pela data.
 * 
 * @author Igor Silveira
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Rich Ramalho
 */
public class OrdenaListaDeComprasPorData implements Comparator<ListaDeCompras> {

	@Override
	public int compare(ListaDeCompras o1, ListaDeCompras o2) {
		return o1.getData().toLowerCase().compareTo(o2.getData().toLowerCase());
	}
}
