package com.projeto.controllers;

import java.util.*;
import com.projeto.entidades.Item;
import com.projeto.entidades.ProdutoNaoIndustrializado;
import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.entidades.ProdutoQuantidadeFixa;
import com.projeto.ordenacao.OrdenaItensPorNome;
import com.projeto.ordenacao.OrdenarItensPorMenorPreco;
import com.projeto.validadores.ValidadorItem;

/**
 * Classe que controla os itens cadastrados no sistema. Cadastrada, exibe, atualiza e deletar itens.
 * 
 * @author Rich Elton
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
	 * Atributo que representa os itens cadastrado no sistema em um dos tipos
	 * de ordenacao.
	 */
	private List<Item> itensOrdenados;
	
	/**
	 * Construtor.
	 */
	public ControllerItem() {
		this.itens = new TreeMap<>();
		this.id = 1;
		this.itensOrdenados = new ArrayList<>();
	}
	
	/**
	 * Metodo que adicionar item por quantidade fixa no sistema.
	 * 
	 * @param nome String que representa o nome do item.
	 * @param categoria String que representa a categoria do item.
	 * @param quantidade Inteiro que representa a quantidade do item.
	 * @param medida String que representa a unidade de medida do item.
	 * @param supermercado String que representa o nome do supermercado para o item.
	 * @param preco Double que representa o preco do item nesse supermercado.
	 * @return Inteiro que representa o id do item.
	 */
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade,
								  String medida, String supermercado, double preco) {
		ValidadorItem.validaItem(nome, categoria, supermercado, preco);
		ValidadorItem.validaProdutoQuantFixa(quantidade, medida);
		ValidadorItem.validaItemProQuantJaExiste(this.itens, nome, categoria);
		this.itens.put(this.id, new ProdutoQuantidadeFixa(this.id, nome, categoria, quantidade, medida, supermercado, preco));
		return this.id++;
	}
	
	/**
	 * Metodo que adicionar item por quantidade fixa no sistema.
	 * 
	 * @param nome String que representa o nome do item.
	 * @param categoria String que representa a categoria do item.
	 * @param unidade Inteiro que representa a unidade do item.
	 * @param supermercado String que representa o nome do supermercado para o item.
	 * @param preco Double que representa o preco do item nesse supermercado.
	 * @return Inteiro que representa o id do item.
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		ValidadorItem.validaItem(nome, categoria, supermercado, preco);
		ValidadorItem.validaUnidade(unidade);
		ValidadorItem.validaItemProUnidJaExiste(this.itens, nome, categoria);
		this.itens.put(this.id, new ProdutoPorUnidade(this.id, nome, categoria, unidade, supermercado, preco));
		return this.id++;
	}
	
	/**
	 * Metodo que adicionar item por quantidade fixa no sistema.
	 * 
	 * @param nome String que representa o nome do item.
	 * @param categoria String que representa a categoria do item.
	 * @param quilos Inteiro que representa o peso do item.
	 * @param supermercado String que representa o nome do supermercado para o item.
	 * @param preco Double que representa o preco do item nesse supermercado.
	 * @return Inteiro que representa o id do item.
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		ValidadorItem.validaItem(nome, categoria, supermercado, preco);
		ValidadorItem.validaPeso(quilos);
		ValidadorItem.validaItemProNaoIndusJaExiste(this.itens, nome, categoria);
		this.itens.put(this.id, new ProdutoNaoIndustrializado(this.id, nome, categoria, quilos, supermercado, preco));
		return this.id++;
	}
	
	/**
	 * Metodo que exibe a representacao textual de um item.
	 * 
	 * @param id Inteiro que represneta o id do item a ser exibido.
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
	 * @param id Inteiro que representa o id do item.
	 * @param atributo String que representa o nome do atributo a ser modificado.
	 * @param novoValor String novo valor.
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		if (atributo.trim().equals("")) {
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao pode ser vazio ou nulo.");
		}
		if (novoValor.trim().equals("")) {
			throw new IllegalArgumentException("Erro na atualizacao de item: novo valor de atributo nao pode ser vazio ou nulo.");
		}
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro na atualizacao de item: item nao existe.");
		}
		switch (atributo) {
			case "nome":
				this.itens.get(id).setNome(novoValor);
				break;
			
			case "categoria":
				this.itens.get(id).setCategoria(novoValor);
				break;
			
			case "unidade de medida":
				this.itens.get(id).setMedida(novoValor);
				break;
			case "quantidade":
				this.itens.get(id).setQuantidade(Integer.parseInt(novoValor));
				break;
			case "unidades":
				this.itens.get(id).setUnidade(Integer.parseInt(novoValor));
				break;
			case "kg":
				if (Double.parseDouble(novoValor) < 0) {
					throw new IllegalArgumentException("Erro na atualizacao de item: valor de quilos nao pode ser menor que zero.");
				}
				this.itens.get(id).setQuilos(Double.parseDouble(novoValor));
				break;
			default:
				throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}
	
	/**
	 * Metodo que adiciona um novo preco ao mapa de precos de um item.
	 * 
	 * @param id Inteiro que representa o id do item.
	 * @param supermercado String que representa o nome do supermercado.
	 * @param preco Double que representa o preco do item.
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
	 * @param id Inteiro que representa o id do item a ser deletado.
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
	 * @param id Inteiro correspondente ao item que sera retornado.
	 * @return String correspondente a o determinado item.
	 */
	public String getItem(int id) {
		if (this.itensOrdenados.size() >= id) {
			this.ordenarPorNome();
			return this.itensOrdenados.get(id).toString();
		} else {
			throw new IllegalArgumentException("Erro nao exibicao do item: item nao existe.");
		}
	}
	
	/**
	 * Metodo que retorna a representacao textual do item de menor preco.
	 * 
	 * @param posicao Inteiro correspondente a posicao do item na ordem de menores precos.
	 * @return String contendo a representacao textual correspondente a este item.
	 */
	public String getItemPorMenorPreco(int posicao) {
		if (this.itensOrdenados.size() >= posicao) {
			this.ordenarPorMenorPreco();
			return this.itensOrdenados.get(posicao).toString();
		} else {
			throw new IllegalArgumentException("Erro na exibicao do item: item nao existe.");
		}
	}
	
	/**
	 * Metodo que retorna a representacao textual de um item dependente de sua categoria.
	 * 
	 * @param categoria String que define de qual categoria se trata o item.
	 * @param id Inteiro que representa ao item que sera retornado.
	 * @return String que contem a representacao textual do item dada sua determinada categoria.
	 */
	public String getItemPorCategoria(String categoria, int id) {
		this.ordenarPorNome();
		int contador = -1;
		
		for(Item i: this.itensOrdenados) {
			if(i.getCategoria().equalsIgnoreCase(categoria)) {
				contador += 1;
				
				if(contador == id) {
					return i.toString();
				}
			}
		}
		
		return "";
	}
	
	/**
	 * Metodo que retorna a representacao textual de um item pesquisado.
	 * 
	 * @param strPesquisada String correspondente ao item pesquisado.
	 * @param posicao Inteiro correspondente a posicao do item que sera pesquisado.
	 * @return String contendo a representacao textual do item pesquisado.
	 */
	public String getItemPorPesquisa(String strPesquisada, int posicao) {
		this.ordenarPorNome();
		int contador = -1;
		
		/**
		 *  Estrutura de repeticao usada pra percorrer a lista dos itens ordenados.
		 */
		for (Item i : this.itensOrdenados) {
			if (i.toString().toLowerCase().indexOf(strPesquisada.toLowerCase()) >= 0) {
				contador += 1;
				
				if (contador == posicao) {
					return i.toString();				}
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
}