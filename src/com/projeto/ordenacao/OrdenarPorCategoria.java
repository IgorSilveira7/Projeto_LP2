package com.projeto.ordenacao;

import java.util.Comparator;
import com.projeto.entidades.Item;

/**
 * Classe responsavel por ordenar dois itens de acordo com sua categoria.
 * 
 * @author Igor Silveira
 * @author Matheus Gusmao
 * @author Rich Ramalho
 */
public class OrdenarPorCategoria implements Comparator<Item> {

	@Override
	public int compare(Item i1, Item i2) {
		return i1.getCategoriaEnum().compareTo(i2.getCategoriaEnum());
	}
}
