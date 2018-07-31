package com.projeto.sistema;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Facade f = new Facade();
		
		/*f.adicionaItemPorUnidade("arroz", "alimento industrializado", 5, "Alfredo ++", 1.59);
		f.adicionaItemPorQuilo("feijao", "alimento nao industrializado", 1.57, "Alfreedinho", 4.57);
		f.adicionaListaDeCompras("feira");
		f.adicionaCompraALista("feira", 5, 1);
		f.adicionaCompraALista("feira", 5, 2);
		f.fechaSistema();*/
		
		f.iniciaSistema();
		System.out.println(f.exibeItem(1));
		f.adicionaItemPorUnidade("pao", "alimento industrializado", 5, "Alfredo ++", 1.59);
		System.out.println(f.exibeItem(2));
		System.out.println(f.getItemLista("feira", 0));
		
	}

}
