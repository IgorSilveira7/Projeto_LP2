package com.projeto;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerItem controllerItem;
	
	public static void main(String[] args) {
		args = new String[] { "com.projeto.Facade",
							  "acceptance_tests/use_case1.txt",
							  "acceptance_tests/use_case1_exception.txt",
							  "acceptance_tests/use_case2_exception.txt",
							  "acceptance_tests/use_case2.txt",
							  "acceptance_tests/use_case3_exception.txt",
							  "acceptance_tests/use_case3.txt",
							  "acceptance_tests/use_case4_exception.txt",
							  "acceptance_tests/use_case4.txt" };
		
		EasyAccept.main(args);
		
	}
	
	public Facade() {
		this.controllerItem = new ControllerItem();
	}
	
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade, String medida, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorQtd(nome, categoria, quantidade, medida, supermercado, preco);
	}
	
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorUnidade(nome, categoria, unidade, supermercado, preco);
	}
	
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorQuilo(nome, categoria, quilos, supermercado, preco);
	}
	
	public String exibeItem(int id) {
		return this.controllerItem.exibeItem(id);
	}
	
	public void atualizaItem(int id, String atributo, String novoValor) {
		this.controllerItem.atualizaItem(id, atributo, novoValor);
	}
}