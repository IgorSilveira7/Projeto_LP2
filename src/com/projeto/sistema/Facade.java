package com.projeto.sistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import easyaccept.EasyAccept;

import com.projeto.controllers.ControllerItem;
import com.projeto.controllers.ControllerListaDeCompras;

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
	 * Atributo referente ao controlador de item.
	 */
	private ControllerItem controllerItem;
	/**
	 * Atributo referente ao controlador de lista de compras.
	 */
	private ControllerListaDeCompras controllerListaDeCompras;

	/**
	 * Metodo que ira verificar os testes de aceitacao.
	 * 
	 * @param args
	 *            Array de String que guardara o caminho dos testes de aceitacao e o
	 *            caminho da Facade.
	 */
	public static void main(String[] args) {
		args = new String[] { "com.projeto.sistema.Facade", "acceptance_tests/use_case1.txt",
				"acceptance_tests/use_case1_exception.txt", "acceptance_tests/use_case2.txt",
				"acceptance_tests/use_case2_exception.txt", "acceptance_tests/use_case3.txt",
				"acceptance_tests/use_case3_exception.txt", "acceptance_tests/use_case4.txt",
				"acceptance_tests/use_case4_exception.txt", "acceptance_tests/use_case5.txt",
				"acceptance_tests/use_case6_exception.txt", "acceptance_tests/use_case6.txt",
				"acceptance_tests/use_case7.txt" };

		EasyAccept.main(args);
	}

	/**
	 * Construtor.
	 */
	public Facade() {
		this.controllerItem = new ControllerItem();
		this.controllerListaDeCompras = new ControllerListaDeCompras(controllerItem);
	}

	/**
	 * Metodo responsavel por realizar o carregamento dos dados.
	 */
	public void iniciaSistema() {
		this.controllerItem.carregarDados();
		this.controllerListaDeCompras.carregarDados();
	}

	/**
	 * Metodo responsavel por realizar o salvamento dos dados em um arquivo.
	 */
	public void fechaSistema() {
		this.controllerItem.salvarDados();
		this.controllerListaDeCompras.salvarDados();
	}

	/**
	 * Metodo que cadastra um item por quantidade fixa.
	 * 
	 * @param nome
	 *            String referente ao nome do item.
	 * @param categoria
	 *            String referente a categoria do item.
	 * @param quantidade
	 *            Inteiro referente quantidade do item.
	 * @param medida
	 *            String referente a unidade de medida do item.
	 * @param supermercado
	 *            String referente ao nome do supermercado.
	 * @param preco
	 *            Double referente ao preco do item.
	 * 
	 * @return Inteiro referente ao id do item cadastrado.
	 */
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade, String medida, String supermercado,
			double preco) {
		return this.controllerItem.adicionaItemPorQtd(nome, categoria, quantidade, medida, supermercado, preco);
	}

	/**
	 * Metodo que cadastra um item por unidade.
	 * 
	 * @param nome
	 *            String referente ao nome do item.
	 * @param categoria
	 *            String referente a categoria do item.
	 * @param unidade
	 *            Inteiro referente a unidade do item.
	 * @param supermercado
	 *            String referente ao nome do supermercado.
	 * @param preco
	 *            Double referente ao preco do item.
	 * 
	 * @return Inteiro referente ao id do item cadastrado.
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorUnidade(nome, categoria, unidade, supermercado, preco);
	}

	/**
	 * Metodo que cadastra um item por peso.
	 * 
	 * @param nome
	 *            String referente ao nome do item.
	 * @param categoria
	 *            String referente a categoria do item.
	 * @param quilos
	 *            Double referente ao peso do item.
	 * @param supermercado
	 *            String referente ao nome do supermercado.
	 * @param preco
	 *            Double referente ao preco do item.
	 * 
	 * @return Inteiro referente ao id do item cadastrado.
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		return this.controllerItem.adicionaItemPorQuilo(nome, categoria, quilos, supermercado, preco);
	}

	/**
	 * Metodo que exibe a representacao textual de um item.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item cadastrado.
	 * 
	 * @return String referente a representacao textual de um item.
	 */
	public String exibeItem(int id) {
		return this.controllerItem.exibeItem(id);
	}

	/**
	 * Metodo que atualiza um item cadastro no sistema.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item.
	 * @param atributo
	 *            String referente ao nome do atributo a ser alterado.
	 * @param novoValor
	 *            String referente ao novo valor.
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		this.controllerItem.atualizaItem(id, atributo, novoValor);
	}

	/**
	 * Metodo que adiciona um preco em um item ja cadastrado.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item.
	 * @param supermercado
	 *            String referente ao nome do supermercado.
	 * @param preco
	 *            Double referente ao preco do item no supermercado.
	 */
	public void adicionaPrecoItem(int id, String supermercado, double preco) {
		this.controllerItem.adicionaPrecoItem(id, supermercado, preco);
	}

	/**
	 * Metodo que deleta um item ja cadastrado do sistema.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item.
	 */
	public void deletaItem(int id) {
		this.controllerItem.deletaItem(id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * ordem alfabetica.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item a ser buscado.
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
	 *            String referente a categoria a ser buscada.
	 * @param id
	 *            Inteiro referente ao id do item.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemPorCategoria(String categoria, int id) {
		return this.controllerItem.getItemPorCategoria(categoria, id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * de menor preco.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item a ser buscado.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemPorMenorPreco(int id) {
		return this.controllerItem.getItemPorMenorPreco(id);
	}

	/**
	 * Metodo que retorna a representacao textual de um Item que esta ordenado por
	 * ordem alfabetica a partir de uma pesquisa.
	 * 
	 * @param strPesquisada
	 *            String referente ao nome a ser buscada.
	 * @param id
	 *            Inteiro referente ao id do item.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemPorPesquisa(String strPesquisada, int id) {
		return this.controllerItem.getItemPorPesquisa(strPesquisada, id);
	}

	/**
	 * Metodo que retorna a descricao da lista de compras recem criada.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @return String referente a descricao de uma lista de compras.
	 */
	public String adicionaListaDeCompras(String descritor) {
		return this.controllerListaDeCompras.criaListaDeCompra(descritor);
	}

	/**
	 * Metodo sem retorno que adiciona uma compra a lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param qntd
	 *            Double referente a quantidade de itens.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 */
	public void adicionaCompraALista(String descritor, int qntd, int id) {
		this.controllerListaDeCompras.adicionaCompraALista(descritor, qntd, id);
	}

	/**
	 * Metodo responsavel por pesquisar uma determinada compra na lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            inteiro referente a identificacao do item.
	 * @return String referente a pesquisa feita na lista de compras.
	 */
	public String pesquisaCompraEmLista(String descritor, int id) {
		return this.controllerListaDeCompras.pesquisaCompraEmLista(descritor, id);
	}

	/**
	 * Metodo que atualiza uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao de lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao de um item.
	 * @param operacao
	 *            String que representa a operacao a ser realizada.
	 * @param novaQuantidade
	 *            Double referente a nova quantidade do item que sera adicionado ou
	 *            diminuida na lista de compras.
	 */
	public void atualizaCompraDeLista(String descritor, int id, String operacao, int novaQuantidade) {
		this.controllerListaDeCompras.atualizaCompraDeLista(descritor, id, operacao, novaQuantidade);
	}

	/**
	 * Metodo que permite a pesquisa de uma lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao uma lista de compras.
	 * @return String referente a pesquisa referente a lista de compras.
	 */
	public String pesquisaListaDeCompras(String descritor) {
		return this.controllerListaDeCompras.pesquisaListaDeCompras(descritor);
	}

	/**
	 * Metodo que tem funcao finalizar uma lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao de uma lista de compras.
	 * @param localDeCompra
	 *            String referente ao local da compra.
	 * @param valorFinal
	 *            Double referente ao valor do custo total da lista.
	 */
	public void finalizarListaDeCompras(String descritor, String localDeCompra, double valorFinal) {
		this.controllerListaDeCompras.finalizarListaDeCompras(descritor, localDeCompra, valorFinal);
	}

	/**
	 * Metodo que retorna o item na lista.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @return String referente a representacao textual do item em determinada lista
	 *         de compras.
	 */
	public String getItemLista(String descritor, int id) {
		return this.controllerListaDeCompras.getItemLista(descritor, id);
	}

	/**
	 * Metodo referente a delecao de uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao de uma lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item na lista de compras.
	 */
	public void deletaCompraDeLista(String descritor, int id) {
		this.controllerListaDeCompras.deletaCompraDeLista(descritor, id);
	}

	/**
	 * Metodo que retorna a representacao textual de um item em uma lista de compras
	 * ordenado por datas
	 * 
	 * @param data
	 *            String referente a data(dd/mm/aaaa).
	 * @param posicao
	 *            Inteiro referente a posicao do item.
	 * @return String referente a descricao da lista de compra.
	 */
	public String getItemListaPorData(String data, int posicao) {
		return this.controllerListaDeCompras.getItemListaPorData(data, posicao);
	}

	/**
	 * Metodo que retorna a representacao textual de um item em uma lista de compras
	 * ordenado por datas.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item a ser procurado.
	 * @param posicao
	 *            Inteiro referente a posicao da lista de compras.
	 * @return String referente a representacao textual da lista de compra.
	 */
	public String getItemListaPorItem(int id, int posicao) {
		return this.controllerListaDeCompras.getItemListaPorItem(id, posicao);
	}

	/**
	 * Metodo que pesquisa uma lista de compra pela data.
	 * 
	 * @param data
	 *            String referente a data de cadastrado da lista de
	 *            compras("dd/mm/aaaa").
	 * @return String referente a representacao textual da lista de compra.
	 */
	public String pesquisaListasDeComprasPorData(String data) {
		return this.controllerListaDeCompras.pesquisaListasDeComprasPorData(data);
	}

	/**
	 * Metodo que pesquisa uma lista de compra pelo id do item.
	 * 
	 * @param id
	 *            Inteiro referente ao id de cadastrado do item.
	 * @return String referente a representacao textual da lista de compra.
	 */
	public String pesquisaListasDeComprasPorItem(int id) {
		return this.controllerListaDeCompras.pesquisaListasDeComprasPorItem(id);
	}

	/**
	 * Metodo que retorna a data atual do sistema.
	 * 
	 * @return String referente a data do sistema("dd/mm/aaaa").
	 */
	public String dataAtual() {
		LocalDate l = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return l.format(formato);
	}

	/**
	 * Metodo que gera uma lista de compra automatico com os itens da ultima lista
	 * cadastrada no sistema.
	 * 
	 * @return String referente ao descritor da lista automatica.
	 */
	public String geraAutomaticaUltimaLista() {
		return this.controllerListaDeCompras.geraAutomaticaUltimaLista();
	}

	/**
	 * Metodo que gera uma lista de compra automatica copiando os itens da ultima
	 * lista que possui o item desejado.
	 * 
	 * @param nomeItem
	 *            String que representa o nome do item cadastrado.
	 * @return String referente ao descritor da lista automatica.
	 */
	public String geraAutomaticaItem(String nomeItem) {
		return this.controllerListaDeCompras.geraAutomaticaItem(nomeItem);
	}

	/**
	 * Metodo que gera uma lista de compra automatica copiando os itens que se
	 * repetem em mais da metade das listas de compras cadastradas.
	 * 
	 * @return String referente ao descritor da lista automatica.
	 */
	public String geraAutomaticaItensMaisPresentes() {
		return this.controllerListaDeCompras.geraAutomaticaItensMaisPresentes();
	}

	/**
	 * Metodo responsavel por sugerir o melhor estabelecimento para realizar a
	 * compra de uma determinada lista de compra.
	 * 
	 * @param descritorLista
	 *            String referente ao descritor da lista que desejar saber qual o
	 *            melhor lugar para realizar a compra.
	 * @param posicaoEstabelecimento
	 *            Inteiro que representa qual estabelimento desejo visualizar(Por
	 *            ordem crescente dos precos)
	 * @param posicaoLista
	 *            Inteiro que representa a posicao do item q desejo visualizar do
	 *            meu estabelecimento.
	 * @return String referente a representacao textual do meu estabelecimento ou
	 *         itens.
	 */
	public String sugereMelhorEstabelecimento(String descritorLista, int posicaoEstabelecimento, int posicaoLista) {
		return this.controllerListaDeCompras.sugereMelhorEstabelecimento(descritorLista, posicaoEstabelecimento,
				posicaoLista);
	}
}