package com.projeto.ordenacao;

import java.util.Comparator;

import com.projeto.entidades.Supermercado;

/**
 * Classe responsavel por ordenar dois supermercados de acordo com seu valor total(Crescente).
 * 
 * @author Rich Ramalho
 */
public class OrdenarSupermercadoPeloValor implements Comparator<Supermercado>{

	@Override
	public int compare(Supermercado o1, Supermercado o2) {
		if (o1.getValorTotal() > o2.getValorTotal()) {
			return 1;
		} else {
			return -1;
		}
	}
}