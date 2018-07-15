package com.projeto.sistema;

import com.projeto.controllers.ControllerItem;
import com.projeto.entidades.Item;

import easyaccept.EasyAccept;

/**
 * Classe que ira se comunicar com os controllers(No caso apenas com um).
 * E assim facilitando as implementações com a interface.
 * 
 * @author Rich
 *
 */
public class Facade {
	
	/**
	 * Atributo que representa o controlador de item.
	 */
	private ControllerItem controllerItem;
	
	/**
	 * Metodo que ira verificar os testes de aceitacao.
	 * 
	 * @param args Array de String que guardara o caminho dos testes de aceitacao
	 * e o caminho da Facade.
	 */
	public static void main(String[] args) {
		args = new String[] { "com.projeto.sistema.Facade",
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
	
	/**
	 * Construtor.
	 */
	public Facade() {
		this.controllerItem = new ControllerItem();
	}
	
	/**
	 * Metodo que cadastra um item por quantidade fixa.
	 * 
	 * @param nome String Nome do item.
	 * @param categoria String Categoria do item.
	 * @param quantidade Inteiro Quantidade do item.
	 * @param medida String unidade de medida do item.
	 * @param supermercado String Nome do supermercado.
	 * @param preco Double Preco do item.
	 * 
	 * @return Inteiro Id do item para ser recuperado..
	 */
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade, String medida, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorQtd(nome, categoria, quantidade, medida, supermercado, preco);
	}
	
	/**
	 * Metodo que cadastra um item por unidade.
	 * 
	 * @param nome String Nome do item.
	 * @param categoria String Categoria do item.
	 * @param unidade Inteiro unidade do item.
	 * @param supermercado String Nome do supermercado.
	 * @param preco Double Preco do item.
	 * 
	 * @return Inteiro Id do item para ser recuperado..
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorUnidade(nome, categoria, unidade, supermercado, preco);
	}
	
	/**
	 * Metodo que cadastra um item por peso.
	 * 
	 * @param nome String Nome do item.
	 * @param categoria String Categoria do item.
	 * @param quilos Double Peso do item.
	 * @param supermercado String Nome do supermercado.
	 * @param preco Double Preco do item.
	 * 
	 * @return Inteiro Id do item para ser recuperado..
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorQuilo(nome, categoria, quilos, supermercado, preco);
	}
	
	/**
	 * Metodo que exibe a representacao textual de um item.
	 * 
	 * @param id Inteiro que eh o id do item cadastrado.
	 * 
	 * @return String a representacao textual de um item.
	 */
	public String exibeItem(int id) {
		return this.controllerItem.exibeItem(id);
	}
	
	/**
	 * Metodo que atualiza um item cadastro no sistema.
	 * 
	 * @param id Inteiro que representa o id do item.
	 * @param atributo String que representa o nome do atributo a ser alterado.
	 * @param novoValor String que representa o novo valor.
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		this.controllerItem.atualizaItem(id, atributo, novoValor);
	}
	
	/**
	 * Metodo que adiciona um preco em um item ja cadastrado.
	 * 
	 * @param id Inteiro que representa o id do item.
	 * @param supermercado String que representa o nome do supermercado.
	 * @param preco Double que representa o preco do item no supermercado.
	 */
	public void adicionaPrecoItem(int id, String supermercado, double preco) {
		this.controllerItem.adicionaPrecoItem(id, supermercado, preco);
	}
	
	/**
	 * Metodo que deleta um item ja cadastrado do sistema.
	 * 
	 * @param id Inteiro que representa o id do item.
	 */
	public void deletaItem(int id) {
		this.controllerItem.deletaItem(id);
	}
	
	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado
	 * por ordem alfabetica.
	 * 
	 * @param id Inteiro que representa o id do item a ser buscado.
	 * @return String a representacao textual do item.
	 */
	public String getItem(int id) {
		return this.controllerItem.getItem(id);
	}
	
	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado
	 * por ordem alfabetica de uma dada categoria.
	 * 
	 * @param categoria String que representa a categoria a ser buscada.
	 * @param id Inteiro que representa o id do item.
	 * @return String a representacao textual do item. 
	 */
	public String getItemPorCategoria(String categoria, int id) {
		return this.controllerItem.getItemPorCategoria(categoria, id);
	}
	
	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado
	 * por de menor preco.
	 * 
	 * @param id Inteiro que representa o id do item a ser buscado.
	 * @return String a representacao textual do item.
	 */
	public String getItemPorMenorPreco(int id) {
		return this.controllerItem.getItemPorMenorPreco(id);
	}
	
	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado
	 * por ordem alfabetica a partir de uma pesquisa.
	 * 
	 * @param strPesquisada String que representa o nome a ser buscada.
	 * @param id Inteiro que representa o id do item.
	 * @return String a representacao textual do item. 
	 */
	public String getItemPorPesquisa(String strPesquisada, int id) {
		return this.controllerItem.getItemPorPesquisa(strPesquisada, id);
	}
	
	public String adicionaListaDeCompras(String descritor) {
		return this.controllerItem.criaListaDeCompra(descritor);
	}
	
	public void adicionaCompraALista(String descritor, double qntd, int id) {
		this.controllerItem.adicionaCompraALista(descritor, qntd, id);
	}
	
	public String pesquisaCompraEmLista(String descritor, int id) {
		return this.controllerItem.pesquisaCompraEmLista(descritor, id);
	}
	
	public void atualizaCompraDeLista(String descritor, int id, double novaQuantidade) {
		this.controllerItem.atualizaCompraDeLista(descritor, id, novaQuantidade);
	}
	
	public String pesquisaListaDeCompras(String descritor) {
		return this.controllerItem.pesquisaListaDeCompras(descritor);
	}
	
	public void finalizarListaDeCompras(String descritor, String localDeCompra, double valorFinal) {
		this.controllerItem.finalizarListaDeCompras(descritor, localDeCompra, valorFinal);
	}
}