package com.projeto.controllers;

import java.util.*;
import com.projeto.entidades.Item;
import com.projeto.entidades.ListaDeCompras;
import com.projeto.entidades.ProdutoNaoIndustrializado;
import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.entidades.ProdutoQuantidadeFixa;
import com.projeto.excecoes.EntradaInvalidaException;
import com.projeto.ordenacao.OrdenaItensPorNome;
import com.projeto.ordenacao.OrdenarItensPorMenorPreco;
import com.projeto.ordenacao.OrdenarListaDeComprasPorDescritor;
import com.projeto.ordenacao.OrdenarPorCategoria;
import com.projeto.validadores.ValidadorItem;
import com.projeto.validadores.ValidadorListaDeCompras;

/**
 * Classe que controla os itens cadastrados no sistema. Cadastrada, exibe,
 * atualiza e deletar itens.
 * 
 * @author Igor Silveira
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Rich Ramalho
 *
 */
public class ControllerItem {

	/**
	 * Atributo que representa os itens cadastrado.
	 */
	private Map<Integer, Item> itens;
	/**
	 * Atributo que ira cuidar dos ids dos itens.
	 */
	private int id;
	/**
	 * Atributo que representa os itens cadastrado no sistema em um dos tipos de
	 * ordenacao.
	 */
	private List<Item> itensOrdenados;

	private Map<String, ListaDeCompras> listasDeCompras;

	/**
	 * Construtor.
	 */
	public ControllerItem() {
		this.itens = new TreeMap<>();
		this.id = 1;
		this.itensOrdenados = new ArrayList<>();
		this.listasDeCompras = new TreeMap<>();
	}

	/**
	 * Metodo que adicionar item por quantidade fixa no sistema.
	 * 
	 * @param nome
	 *            String que representa o nome do item.
	 * @param categoria
	 *            String que representa a categoria do item.
	 * @param quantidade
	 *            Inteiro que representa a quantidade do item.
	 * @param medida
	 *            String que representa a unidade de medida do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado para o item.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 * @return Inteiro que representa o id do item.
	 */
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade, String medida, String supermercado,
			double preco) {
		ValidadorItem.validaItem(nome, categoria, supermercado, preco);
		ValidadorItem.validaProdutoQuantFixa(quantidade, medida);
		// ValidadorItem.validaItemProQuantJaExiste(this.itens, nome, categoria);
		this.itens.put(this.id,
				new ProdutoQuantidadeFixa(this.id, nome, categoria, quantidade, medida, supermercado, preco));
		return this.id++;
	}

	/**
	 * Metodo que adicionar item por quantidade fixa no sistema.
	 * 
	 * @param nome
	 *            String que representa o nome do item.
	 * @param categoria
	 *            String que representa a categoria do item.
	 * @param unidade
	 *            Inteiro que representa a unidade do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado para o item.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 * @return Inteiro que representa o id do item.
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		ValidadorItem.validaItem(nome, categoria, supermercado, preco);
		ValidadorItem.validaUnidade(unidade);
		// ValidadorItem.validaItemProUnidJaExiste(this.itens, nome, categoria);
		this.itens.put(this.id, new ProdutoPorUnidade(this.id, nome, categoria, unidade, supermercado, preco));
		return this.id++;
	}

	/**
	 * Metodo que adicionar item por quantidade fixa no sistema.
	 * 
	 * @param nome
	 *            String que representa o nome do item.
	 * @param categoria
	 *            String que representa a categoria do item.
	 * @param quilos
	 *            Inteiro que representa o peso do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado para o item.
	 * @param preco
	 *            Double que representa o preco do item nesse supermercado.
	 * @return Inteiro que representa o id do item.
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		ValidadorItem.validaItem(nome, categoria, supermercado, preco);
		ValidadorItem.validaPeso(quilos);
		// ValidadorItem.validaItemProNaoIndusJaExiste(this.itens, nome, categoria);
		this.itens.put(this.id, new ProdutoNaoIndustrializado(this.id, nome, categoria, quilos, supermercado, preco));
		return this.id++;
	}

	/**
	 * Metodo que exibe a representacao textual de um item.
	 * 
	 * @param id
	 *            Inteiro que represneta o id do item a ser exibido.
	 * @return String que eh a representacao textual do item.
	 */
	public String exibeItem(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro na listagem de item: id invalido.");
		}
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro na listagem de item: item nao existe.");
		}
		return this.itens.get(id).toString();
	}

	/**
	 * Metodo que atualiza algum detalhe do item.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item.
	 * @param atributo
	 *            String que representa o nome do atributo a ser modificado.
	 * @param novoValor
	 *            String novo valor.
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		if (atributo.trim().equals("")) {
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao pode ser vazio ou nulo.");
		}
		if (novoValor.trim().equals("")) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: novo valor de atributo nao pode ser vazio ou nulo.");
		}
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro na atualizacao de item: item nao existe.");
		}
		this.itens.get(id).AtualizarItem(atributo, novoValor);
	}

	/**
	 * Metodo que adiciona um novo preco ao mapa de precos de um item.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item.
	 * @param supermercado
	 *            String que representa o nome do supermercado.
	 * @param preco
	 *            Double que representa o preco do item.
	 */
	public void adicionaPrecoItem(int id, String supermercado, double preco) {
		ValidadorItem.validaAdicionarPrecoItem(supermercado, preco);
		if (id <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: id de item invalido.");
		}
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro no cadastro de preco: item nao existe.");
		}
		this.itens.get(id).adicionaPrecoItem(supermercado, preco);
	}

	/**
	 * Metodo que deleta um item da lista.
	 * 
	 * @param id
	 *            Inteiro que representa o id do item a ser deletado.
	 */
	public void deletaItem(int id) {
		if (this.itens.containsKey(id)) {
			this.itens.remove(id);
		} else {
			throw new IllegalArgumentException("Erro na deletacao de item: item nao existe.");
		}
	}

	/**
	 * Metodo que retorna uma String.
	 * 
	 * @param id
	 *            Inteiro correspondente ao item que sera retornado.
	 * @return String correspondente a o determinado item.
	 */
	public String getItem(int id) {
		this.ordenarPorNome();
		if (this.itensOrdenados.size() > id) {
			return this.itensOrdenados.get(id).toString();
		} else {
			return "";
		}

	}

	/**
	 * Metodo que retorna a representacao textual do item de menor preco.
	 * 
	 * @param posicao
	 *            Inteiro correspondente a posicao do item na ordem de menores
	 *            precos.
	 * @return String contendo a representacao textual correspondente a este item.
	 */
	public String getItemPorMenorPreco(int posicao) {
		this.ordenarPorMenorPreco();
		if (this.itensOrdenados.size() >= posicao) {
			return this.itensOrdenados.get(posicao).toString();
		} else {
			return "";
		}
	}

	/**
	 * Metodo que retorna a representacao textual de um item dependente de sua
	 * categoria.
	 * 
	 * @param categoria
	 *            String que define de qual categoria se trata o item.
	 * @param id
	 *            Inteiro que representa ao item que sera retornado.
	 * @return String que contem a representacao textual do item dada sua
	 *         determinada categoria.
	 */
	public String getItemPorCategoria(String categoria, int id) {
		if (ValidadorItem.validaCategoria(categoria)) {
			this.ordenarPorNome();
			int contador = -1;

			for (Item i : this.itensOrdenados) {
				if (i.getCategoria().equalsIgnoreCase(categoria)) {
					contador += 1;

					if (contador == id) {
						return i.toString();
					}
				}
			}
		}
		return "";
	}

	/**
	 * Metodo que retorna a representacao textual de um item pesquisado.
	 * 
	 * @param strPesquisada
	 *            String correspondente ao item pesquisado.
	 * @param posicao
	 *            Inteiro correspondente a posicao do item que sera pesquisado.
	 * @return String contendo a representacao textual do item pesquisado.
	 */
	public String getItemPorPesquisa(String strPesquisada, int posicao) {
		this.ordenarPorNome();
		int contador = -1;

		/**
		 * Estrutura de repeticao usada pra percorrer a lista dos itens ordenados.
		 */
		for (Item i : this.itensOrdenados) {
			if (i.toString().toLowerCase().indexOf(strPesquisada.toLowerCase()) >= 0) {
				contador += 1;

				if (contador == posicao) {
					return i.toString();
				}
			}
		}
		return "";
	}

	/**
	 * Metodo responsavel por ordenar os itens atraves do nome.
	 */
	private void ordenarPorNome() {
		this.itensOrdenados = new ArrayList<>(this.itens.values());
		Collections.sort(this.itensOrdenados, new OrdenaItensPorNome());
	}

	/**
	 * Metodo responsavel por ordenar os itens atraves do preco.
	 */
	private void ordenarPorMenorPreco() {
		this.itensOrdenados = new ArrayList<>(this.itens.values());
		Collections.sort(itensOrdenados, new OrdenarItensPorMenorPreco());
	}

	/**
	 * Metodo responsavel por criar a lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @return o descritor referente a lista de compras criada.
	 */
	public String criaListaDeCompra(String descritor) {
		ValidadorListaDeCompras.testeDescritor(descritor);
		this.listasDeCompras.put(descritor, new ListaDeCompras(descritor));
		return descritor;
	}

	/**
	 * Metodo responsavel por adicionar compras a lista de compras criada.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @param qntd
	 *            do tipo Double, referente a quantidade do item.
	 * @param id
	 *            do tipo Inteiro, referente a identificacao do item.
	 */
	public void adicionaCompraALista(String descritor, double qntd, int id) {
		if (!this.itens.containsKey(id)) {
			throw new EntradaInvalidaException("Erro na compra de item: item nao existe no sistema.");
		}
		Item i = this.itens.get(id);
		this.listasDeCompras.get(descritor).adicionaCompraALista(qntd, i);
	}

	/**
	 * Metodo responsavel por pesquisar uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @param id
	 *            do tipo Inteiro, referente a identificacao do item.
	 * @return String contendo a pesquisa do item em determinada lista de compras.
	 */
	public String pesquisaCompraEmLista(String descritor, int id) {
		if (descritor == null) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (!this.itens.containsKey(id)) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: item id invalido.");
		}
		Item i = this.itens.get(id);
		return this.listasDeCompras.get(descritor).pesquisaCompraEmLista(i);
	}

	/**
	 * Metodo responsavel por atualizar a lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @param id
	 *            do tipo Inteiro, referente a identificacao do item.
	 * @param novaQuantidade
	 *            do tipo Double, referente a nova quantidade do item, atualizando
	 *            assim a lista.
	 */
	public void atualizaCompraDeLista(String descritor, int id, double novaQuantidade) {
		Item i = this.itens.get(id);
		this.listasDeCompras.get(descritor).atualizaCompraDeLista(i, novaQuantidade);
	}

	/**
	 * Metodo responsavel por pesquisar uma lista de compra especifica.
	 * 
	 * @param descritor
	 *            do tipo String, referente a descricao da lista de compras.
	 * @return a lista de compras pesquisada, da forma String.
	 */
	public String pesquisaListaDeCompras(String descritor) {
		return this.listasDeCompras.get(descritor).toString();
	}

	/**
	 * Metodo responsavel por finalizar uma lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, responsavel pela descricao da lista de compras.
	 * @param localDeCompra
	 *            do tipo String, referente ao local de compra do item.
	 * @param valorFinal
	 *            do tipo Double, referente ao ultimo valor do item.
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
	 * Metodo que retorna o item da lista.
	 * 
	 * @param descritor
	 *            do tipo String, referente a decricao da lista de compras.
	 * @param id
	 *            do tipo inteiro, referente a identificacao do item.
	 * @return a String contendo a lista de compras e o item.
	 */
	public String getItemLista(String descritor, int id) {
		return this.listasDeCompras.get(descritor).getItemLista(id);
	}

	/**
	 * Metodo que deleta uma lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, responsavel por descrever a lista de compras.
	 * @param id
	 *            do tipo Inteiro, responsavel pela identificacao do item.
	 */
	public void deletaCompraDeLista(String descritor, int id) {
		if (descritor == null) {
			throw new EntradaInvalidaException("Erro na exclusao de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na exclusao de compra: descritor nao pode ser vazio ou nulo.");
		}
		if (!this.itens.containsKey(id)) {
			throw new EntradaInvalidaException("Erro na exclusao de compra: item nao existe no sistema.");
		}
		Item i = this.itens.get(id);
		this.listasDeCompras.get(descritor).deletaCompraDeLista(i);
	}
	
	public String getItemListaPorData(String data, int posicao) {
		List<ListaDeCompras> ordenado = new ArrayList<>(this.listasDeCompras.values());
		Collections.sort(ordenado, new OrdenarListaDeComprasPorDescritor());
		System.out.println(ordenado.toString());
		
		int cont = 0;
		for (ListaDeCompras listaDeCompras : ordenado) {
			if (listaDeCompras.getData().equals(data)){
				if (cont == posicao) {
					return listaDeCompras.getDescritor();
				}
				cont += 1;
			}
		}
		return "";
	}

	public String getItemListaPorItem(int id, int posicao) {
		Item i = this.itens.get(id);
		int cont = 0;
		for (String key : listasDeCompras.keySet()) {
			if (cont == posicao) {
				return this.listasDeCompras.get(key).verificarItemEmLista(i);
			}
			cont += 1;
		}
		return "";
	}
}