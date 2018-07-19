package com.projeto.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.projeto.entidades.ListaDeCompras;
import com.projeto.excecoes.EntradaInvalidaException;
import com.projeto.excecoes.ItemNaoExisteException;
import com.projeto.excecoes.ListaDeCompraNaoExisteException;
import com.projeto.excecoes.OperacaoInvalidaException;
import com.projeto.ordenacao.OrdenaListaDeComprasPorData;
import com.projeto.ordenacao.OrdenarListaDeComprasPorDescritor;
import com.projeto.validadores.ValidadorListaDeCompras;

/**
 * Classe que controla as listas de compras cadastradas no sistema. Cadastrada, exibe,
 * atualiza e deletar listas de compras.
 * 
 * @author Rich Ramalho
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Igor Silveira
 *
 */
public class ControllerListaDeCompras {

	/**
	 * Atributo referente as listas de compras cadastradas.
	 */
	private Map<String, ListaDeCompras> listasDeCompras;
	/**
	 * Atributo referente ao controlador de itens.
	 */
	private ControllerItem controllerItem;

	/**
	 * Construtor.
	 * 
	 * @param controller
	 *            ControllerItem referente ao controlador de itens.
	 */
	public ControllerListaDeCompras(ControllerItem controller) {
		this.listasDeCompras = new HashMap<>();
		this.controllerItem = controller;
	}

	/**
	 * Metodo responsavel por criar a lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @return String referente ao descritor referente a lista de compras criada.
	 */
	public String criaListaDeCompra(String descritor) {
		ValidadorListaDeCompras.validaDescritor(descritor);
		this.listasDeCompras.put(descritor, new ListaDeCompras(descritor));
		return descritor;
	}

	/**
	 * Metodo responsavel por adicionar compras a lista de compras criada.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param qntd
	 *            Double referente a quantidade do item.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 */
	public void adicionaCompraALista(String descritor, int qntd, int id) {
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na compra de item: item nao existe no sistema.");
		}
		this.listasDeCompras.get(descritor).adicionaCompraALista(qntd, this.controllerItem.getItemPeloId(id));
	}

	/**
	 * Metodo responsavel por pesquisar uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @return String referente a pesquisa do item em determinada lista de compras.
	 */
	public String pesquisaCompraEmLista(String descritor, int id) {
		if (descritor == null) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na pesquisa de compra: item id invalido.");
		}
		return this.listasDeCompras.get(descritor).pesquisaCompraEmLista(this.controllerItem.getItemPeloId(id));
	}

	/**
	 * Metodo responsavel por pesquisar uma lista de compra especifica.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @return String referente a lista de compras pesquisada.
	 */
	public String pesquisaListaDeCompras(String descritor) {
		if (descritor == null) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (!this.listasDeCompras.containsKey(descritor)) {
			throw new ListaDeCompraNaoExisteException("Erro na pesquisa de compra: lista de compras nao existe.");
		}
		return this.listasDeCompras.get(descritor).getDescritor();
	}

	/**
	 * Metodo responsavel por atualizar a lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @param operacao
	 *            String referente ao tipo de operacao que sera realizada.
	 * @param novaQuantidade
	 *            Double referente a nova quantidade do item, atualizando assim a
	 *            lista.
	 */
	public void atualizaCompraDeLista(String descritor, int id, String operacao, int novaQuantidade) {
		if (!(operacao.equals("diminui") || operacao.equals("adiciona"))) {
			throw new OperacaoInvalidaException("Erro na atualizacao de compra: operacao invalida para atualizacao.");
		}
		this.listasDeCompras.get(descritor).atualizaCompraDeLista(this.controllerItem.getItemPeloId(id), operacao,
				novaQuantidade);
	}

	/**
	 * Metodo responsavel por finalizar uma lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param localDeCompra
	 *            String referente ao local de compra do item.
	 * @param valorFinal
	 *            Double referente ao ultimo valor do item.
	 */
	public void finalizarListaDeCompras(String descritor, String localDeCompra, double valorFinal) {
		if (descritor == null) {
			throw new EntradaInvalidaException(
					"Erro na finalizacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException(
					"Erro na finalizacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
		ValidadorListaDeCompras.testeFinalizarCompra(localDeCompra, valorFinal);
		this.listasDeCompras.get(descritor).finalizarListaDeCompras(localDeCompra, valorFinal);
	}

	/**
	 * Metodo que deleta uma lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 */
	public void deletaCompraDeLista(String descritor, int id) {
		if (descritor == null) {
			throw new EntradaInvalidaException("Erro na exclusao de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na exclusao de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na exclusao de compra: item nao existe no sistema.");
		}
		this.listasDeCompras.get(descritor).deletaCompraDeLista(this.controllerItem.getItemPeloId(id));
	}

	/**
	 * Metodo que retorna o item da lista.
	 * 
	 * @param descritor
	 *            String referente a decricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @return String referente a lista de compras e o item.
	 */
	public String getItemLista(String descritor, int id) {
		return this.listasDeCompras.get(descritor).getItemLista(id);
	}

	/**
	 * Metodo que retorna o descritor de uma lista de compra criada em tal data em
	 * uma determinada posicao da lista ordenada por ordem alfabetica.
	 * 
	 * @param data
	 *            String referente a data de criacao do item(dd/mm/aaaa).
	 * @param posicao
	 *            Inteiro referente a posicao do item da lista de compra.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemListaPorData(String data, int posicao) {
		List<ListaDeCompras> ordenado = new ArrayList<>(this.listasDeCompras.values());
		Collections.sort(ordenado, new OrdenarListaDeComprasPorDescritor());

		int cont = 0;
		for (ListaDeCompras listaDeCompras : ordenado) {
			if (listaDeCompras.getData().equals(data)) {
				if (cont == posicao) {
					return listaDeCompras.getDescritor();
				}
				cont += 1;
			}
		}
		return "";
	}

	/**
	 * Metodo que retorna uma representacao textual de uma lista de compra em uma
	 * determinada posicao da lista ordenada por ordem alfabetica e pela data.
	 * 
	 * @param id
	 *            Inteiro referente a data de criacao do item(dd/mm/aaaa).
	 * @param posicao
	 *            Inteiro referente a posicao do item da lista de compra.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemListaPorItem(int id, int posicao) {
		List<ListaDeCompras> lista = new ArrayList<>();
		for (String key : this.listasDeCompras.keySet()) {
			ListaDeCompras l = this.listasDeCompras.get(key);
			if (l.verificarItemEmLista(this.controllerItem.getItemPeloId(id))) {
				lista.add(l);
			}
		}
		Collections.sort(lista, new OrdenaListaDeComprasPorData());
		Collections.sort(lista, new OrdenarListaDeComprasPorDescritor());
		return lista.get(posicao).toString();
	}

	/**
	 * Metodo que faz a pesquisa de lista de compras pelo item cadastrado na lista
	 * de compras.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item cadastrado na lista de compras.
	 * @return String referente a representacao textual da lista de compra.
	 */
	public String pesquisaListasDeComprasPorItem(int id) {
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na pesquisa de compra: compra nao encontrada na lista.");
		}
		List<ListaDeCompras> lista = new ArrayList<>(listasDeCompras.values());
		for (ListaDeCompras listaDeCompras : lista) {
			if (listaDeCompras.verificarItemEmLista(this.controllerItem.getItemPeloId(id))) {
				return listaDeCompras.toString();
			}
		}
		return "";
	}

	/**
	 * Metodo que faz a pesquisa nas listas de compras pela data.
	 * 
	 * @param data
	 *            String referente a data de criacao do item(dd/mm/aaaa).
	 * @return String referente a representacao textual da lista de compras.
	 */
	public String pesquisaListasDeComprasPorData(String data) {
		ValidadorListaDeCompras.validaData(data);

		List<ListaDeCompras> lista = new ArrayList<>(listasDeCompras.values());
		for (ListaDeCompras listaDeCompras : lista) {
			if (listaDeCompras.getData().equals(data)) {
				return listaDeCompras.toString();
			}
		}
		return "";
	}
}
