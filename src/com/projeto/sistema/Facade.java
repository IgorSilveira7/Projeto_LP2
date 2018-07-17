package com.projeto.sistema;

import com.projeto.controllers.ControllerItem;

import easyaccept.EasyAccept;

/**
 * Classe que ira se comunicar com os controllers(No caso apenas com um). E
 * assim facilitando as implementações com a interface.
 * 
 * @author Igor Silveira
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Rich Ramalho
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
	 * @param args
	 *            Array de String que guardara o caminho dos testes de aceitacao e o
	 *            caminho da Facade.
	 */
	public static void main(String[] args) {
		args = new String[] { "com.projeto.sistema.Facade", "acceptance_tests/use_case1.txt",
				"acceptance_tests/use_case1_exception.txt", "acceptance_tests/use_case2_exception.txt",
				"acceptance_tests/use_case2.txt", "acceptance_tests/use_case3_exception.txt",
				"acceptance_tests/use_case3.txt", "acceptance_tests/use_case4_exception.txt",
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
	 * @param nome
	 *            String Nome do item.
	 * @param categoria
	 *            String Categoria do item.
	 * @param quantidade
	 *            Inteiro Quantidade do item.
	 * @param medida
	 *            String unidade de medida do item.
	 * @param supermercado
	 *            String Nome do supermercado.
	 * @param preco
	 *            Double Preco do item.
	 * 
	 * @return Inteiro Id do item para ser recuperado..
	 */
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade, String medida, String supermercado,
			double preco) {
		return this.controllerItem.adicionaItemPorQtd(nome, categoria, quantidade, medida, supermercado, preco);
	}

	/**
	 * Metodo que cadastra um item por unidade.
	 * 
	 * @param nome
	 *            String Nome do item.
	 * @param categoria
	 *            String Categoria do item.
	 * @param unidade
	 *            Inteiro unidade do item.
	 * @param supermercado
	 *            String Nome do supermercado.
	 * @param preco
	 *            Double Preco do item.
	 * 
	 * @return Inteiro Id do item para ser recuperado..
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorUnidade(nome, categoria, unidade, supermercado, preco);
	}

	/**
	 * Metodo que cadastra um item por peso.
	 * 
	 * @param nome
	 *            String Nome do item.
	 * @param categoria
	 *            String Categoria do item.
	 * @param quilos
	 *            Double Peso do item.
	 * @param supermercado
	 *            String Nome do supermercado.
	 * @param preco
	 *            Double Preco do item.
	 * 
	 * @return Inteiro Id do item para ser recuperado..
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorQuilo(nome, categoria, quilos, supermercado, preco);
	}

	/**
	 * Metodo que exibe a representacao textual de um item.
	 * 
	 * @param id
	 *            Inteiro que eh o id do item cadastrado.
	 * 
	 * @return String a representacao textual de um item.
	 */
	public String exibeItem(int id) {
		return this.controllerItem.exibeItem(id);
	}

	/**
	 * Metodo que atualiza um item cadastro no sistema.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item.
	 * @param atributo
	 *            String que representa o nome do atributo a ser alterado.
	 * @param novoValor
	 *            String que representa o novo valor.
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		this.controllerItem.atualizaItem(id, atributo, novoValor);
	}

	/**
	 * Metodo que adiciona um preco em um item ja cadastrado.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado.
	 * @param preco
	 *            Double que representa o preco do item no supermercado.
	 */
	public void adicionaPrecoItem(int id, String supermercado, double preco) {
		this.controllerItem.adicionaPrecoItem(id, supermercado, preco);
	}

	/**
	 * Metodo que deleta um item ja cadastrado do sistema.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item.
	 */
	public void deletaItem(int id) {
		this.controllerItem.deletaItem(id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * ordem alfabetica.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item a ser buscado.
	 * @return String a representacao textual do item.
	 */
	public String getItem(int id) {
		return this.controllerItem.getItem(id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * ordem alfabetica de uma dada categoria.
	 * 
	 * @param categoria
	 *            String que representa a categoria a ser buscada.
	 * @param id
	 *            Inteiro que representa o id do item.
	 * @return String a representacao textual do item.
	 */
	public String getItemPorCategoria(String categoria, int id) {
		return this.controllerItem.getItemPorCategoria(categoria, id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * de menor preco.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item a ser buscado.
	 * @return String a representacao textual do item.
	 */
	public String getItemPorMenorPreco(int id) {
		return this.controllerItem.getItemPorMenorPreco(id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * ordem alfabetica a partir de uma pesquisa.
	 * 
	 * @param strPesquisada
	 *            String que representa o nome a ser buscada.
	 * @param id
	 *            Inteiro que representa o id do item.
	 * @return String a representacao textual do item.
	 */
	public String getItemPorPesquisa(String strPesquisada, int id) {
		return this.controllerItem.getItemPorPesquisa(strPesquisada, id);
	}

	/**
	 * Metodo que retorna a descricao da lista de compras recem criada.
	 * 
	 * @param descritor
	 *            do tipo String, que descreve a lista de compras.
	 * @return a criacao de uma lista de compras.
	 */
	public String adicionaListaDeCompras(String descritor) {
		return this.controllerItem.criaListaDeCompra(descritor);
	}

	/**
	 * Metodo sem retorno que adiciona uma compra a lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, que descreve uma lista de compras.
	 * @param qntd
	 *            do tipo Double, que e referente a quantidade de itens.
	 * @param id
	 *            do tipo Inteiro, referente a identificacao do item.
	 */
	public void adicionaCompraALista(String descritor, double qntd, int id) {
		this.controllerItem.adicionaCompraALista(descritor, qntd, id);
	}

	/**
	 * Metodo responsavel por pesquisar uma determinada compra na lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @param id
	 *            do tipo inteiro, referente a identificacao do item.
	 * @return uma String contendo a pesquisa feita na lista.
	 */
	public String pesquisaCompraEmLista(String descritor, int id) {
		return this.controllerItem.pesquisaCompraEmLista(descritor, id);
	}

	/**
	 * Metodo que atualiza uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, responsavel por descrever uma lista de compras.
	 * @param id
	 *            do tipo Inteiro, responsavel pela identificacao de um item.
	 * @param novaQuantidade
	 *            do tipo Double, responsavel por dar a nova quantidade do item_
	 *            _que sera atualizado na lista de compras.
	 */
	public void atualizaCompraDeLista(String descritor, int id, double novaQuantidade) {
		this.controllerItem.atualizaCompraDeLista(descritor, id, novaQuantidade);
	}

	/**
	 * Metodo que permite a pesquisa de uma lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, que descreve uma lista de compras.
	 * @return a String contendo a pesquisa referente a lista de compras.
	 */
	public String pesquisaListaDeCompras(String descritor) {
		return this.controllerItem.pesquisaListaDeCompras(descritor);
	}

	/**
	 * Metodo que tem funcao finalizar uma lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao de uma lista de compras.
	 * @param localDeCompra
	 *            do tipo String, referente ao local da compra.
	 * @param valorFinal
	 *            do tipo Double, referente ao ultimo valor do item a ser comprado.
	 */
	public void finalizarListaDeCompras(String descritor, String localDeCompra, double valorFinal) {
		this.controllerItem.finalizarListaDeCompras(descritor, localDeCompra, valorFinal);
	}

	/**
	 * Metodo que retorna o item na lista.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @param id
	 *            do tipo Inteiro, referente a identificacao do item.
	 * @return uma String contendo a representacao textual do item em determinada
	 *         lista de compras.
	 */
	public String getItemLista(String descritor, int id) {
		return this.controllerItem.getItemLista(descritor, id);
	}

	/**
	 * Metodo referente a delecao de uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao de uma lista de compras.
	 * @param id
	 *            do tipo Inteiro, referente a identificacao do item na lista de
	 *            compras.
	 */
	public void deletaCompraDeLista(String descritor, int id) {
		this.controllerItem.deletaCompraDeLista(descritor, id);
	}
	
	public String getItemListaPorData(String data, int posicao) {
		return this.controllerItem.getItemListaPorData(data, posicao);
	}
	
	public String getItemListaPorItem(int id, int posicao) {
		return this.controllerItem.getItemListaPorItem(id, posicao);
	}
}