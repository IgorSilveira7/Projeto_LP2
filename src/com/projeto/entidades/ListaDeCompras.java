package com.projeto.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.projeto.excecoes.EntradaInvalidaException;
import com.projeto.ordenacao.OrdenaItensPorNome;
import com.projeto.ordenacao.OrdenarPorCategoria;
import com.projeto.validadores.ValidadorListaDeCompras;

/**
 * Classe reposavel por uma lista de compras.
 * 
 * @author Igor Silveira
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Rich Ramalho
 *
 */
public class ListaDeCompras implements Serializable{
	
	private static final long serialVersionUID = -2414139878369344656L;
	
	private Map<Item, Compra> listaDeCompras;
	/**
	 * Atributo do tipo String referente a descricao de uma lista de compras.
	 */
	private String descritor;
	/**
	 * Atributo do tipo Date, referente a data da lista de compras.
	 */
	private LocalDate data;
	/**
	 * Atributo do tipo Double referente ao Valor total da lista de compras.
	 */
	private double valorTotal;
	/**
	 * Atributo do tipo String referente ao Local de compra.
	 */
	private String localDeCompra;
	/**
	 * Atributo do tipo Boolean referente se a lista de compras foi finalizada.
	 */
	private boolean finalizado;

	/**
	 * Construtor de uma Lista de compras.
	 * 
	 * @param descritor
	 *            do tipo String, que e referente a descricao de uma lista de
	 *            compras.
	 */
	public ListaDeCompras(String descritor) {
		if (ValidadorListaDeCompras.validaDescritor(descritor)) {
			this.descritor = descritor;
			this.listaDeCompras = new TreeMap<>();
			this.data = LocalDate.now();
			this.finalizado = false;
		}
	}

	/**
	 * Metodo responsavel por adicionr uma compra a lista de compras.
	 * 
	 * @param qntd
	 *            Double referente a quantidade de determinada compra.
	 * @param item
	 *            Item referente ao Item que esta sendo adquirido.
	 * @return Inteiro que representa o tamanho da lista itens, apos adicionar a o
	 *         item comprado.
	 */
	public int adicionaCompraALista(int qntd, Item item) {
		this.listaDeCompras.put(item, new Compra(item, qntd));
		return this.listaDeCompras.size();
	}

	/**
	 * Metodo responsavel por pesquisar determinado item comprado em uma lista de
	 * compras.
	 * 
	 * @param item
	 *            Item referente ao Item que foi comprado.
	 * @return String que é a representacao textual do item comprado que sera
	 *         pesquisado.
	 */
	public String pesquisaCompraEmLista(Item item) {
		if (!this.listaDeCompras.containsKey(item)) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: compra nao encontrada na lista.");
		}
		return listaDeCompras.get(item).toString();
	}

	/**
	 * Metodo que atualiza uma lista de compra com os itens comprados.
	 * 
	 * @param item
	 *            Item referente ao item que devera ser atualizado na lista.
	 * @param novaquantidade
	 *            Double referente a nova quantidade daquele item, que_ devera ser
	 *            atualizado.
	 */
	public void atualizaCompraDeLista(Item item, String operacao, int novaquantidade) {
		ValidadorListaDeCompras.validaOperacao(operacao);
		if (!this.listaDeCompras.containsKey(item)) {
			throw new EntradaInvalidaException("Erro na atualizacao de compra: compra nao encontrada na lista.");
		}
		int novoValor = 0;
		if (operacao.equals("diminui")) {
			this.listaDeCompras.get(item).diminuiQntdItem(novaquantidade);
		} else if (operacao.equals("adiciona")) {
			this.listaDeCompras.get(item).aumentaQntdItem(novaquantidade);
		}
		novoValor = this.listaDeCompras.get(item).getQntd();
		
		if (novoValor <= 0) {
			this.listaDeCompras.remove(item);
		}
	}

	/**
	 * Metodo referente a finalizar uma lista de compras.
	 * 
	 * @param localDeCompra
	 *            String referente ao local de compra da lista.
	 * @param valorFinal
	 *            Double referente ao valor final da lista em questao.
	 */
	public void finalizarListaDeCompras(String localDeCompra, double valorFinal) {
		ValidadorListaDeCompras.testeFinalizarCompra(localDeCompra, valorFinal, this.descritor);
		this.localDeCompra = localDeCompra;
		this.valorTotal = valorFinal;
		this.finalizado = true;
	}

	/**
	 * Metodo responsavel por emitir a representacao textual do item na lista.
	 * 
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @return String que é a representacao textual do item em determinada lista de
	 *         compras.
	 */
	public String getItemLista(int id) {
		if (id < 0 || this.listaDeCompras.size() <= id) {
			return "";
		}
		List<Item> itensOrdenados = new ArrayList<>(this.listaDeCompras.keySet());
		Collections.sort(itensOrdenados, new OrdenaItensPorNome());
		Collections.sort(itensOrdenados, new OrdenarPorCategoria());
		Item i = itensOrdenados.get(id);
		return this.listaDeCompras.get(i).toString();
	}

	/**
	 * Metodo que deleta uma compra de uma lista de compras.
	 * 
	 * @param item
	 *            Item referente ao item a ser removido da lista.
	 */
	public void deletaCompraDeLista(Item item) {
		if (!this.listaDeCompras.containsKey(item)) {
			throw new EntradaInvalidaException("Erro na exclusao de compra: compra nao encontrada na lista.");
		}
		this.listaDeCompras.remove(item);
		this.listaDeCompras.remove(item);
	}

	/**
	 * Metodo que retorna a descricao textual de uma lista de compras.
	 * 
	 * @return String que representa a representacao textual da lista de compra.
	 */
	@Override
	public String toString() {
		return this.getData() + " - " + this.descritor;
	}
	
	/**
	 * Metodo que retorna uma colecao das compras desta lista de compras.
	 * @return Map<Item, Compra> Item: referente ao item, Compra: referente a compra.
	 */
	public Map<Item, Compra> getCompras() {
		return this.listaDeCompras;
	}

	/**
	 * Metodo que retorna se a lista de compra foi finalizada ou nao.
	 * 
	 * @return Boolean que representa o status da lista de compra.
	 */
	public boolean getFinalizado() {
		return this.finalizado;
	}

	/**
	 * Metodo que retorna o descritor da lista de compra.
	 * 
	 * @return String que representa o descritor.
	 */
	public String getDescritor() {
		return this.descritor;
	}

	/**
	 * Metodo que retorna a data no formato "dd/mm/aaaa".
	 * 
	 * @return String que representa a data no formato "dd/mm/aaaa".
	 */
	public String getData() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data.format(formato);
	}

	/**
	 * Metodo que retorna se um determinado item esta cadastrado na minha lista de
	 * compra.
	 * 
	 * @param item
	 *            Item que representa o item a ser verificado se esta cadastrado na
	 *            lista.
	 * @return Boolean True se o item estiver na lista, ou false caso nao esteja.
	 */
	public boolean verificarItemEmLista(Item item) {
		if (this.listaDeCompras.containsKey(item)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que copia os itens desta lista para a recebida como parametro.
	 * @param lista ListaDeCompras referente a lista de compras que recebera os itens dessa lista.
	 */
	public void copiaLista(ListaDeCompras lista) {
		for (Item i : this.listaDeCompras.keySet()) {
			lista.adicionaCompraALista(this.listaDeCompras.get(i).getQntd(), i);
		}
	}
}