package com.projeto.validadores;

import java.util.Map;

import com.projeto.entidades.Item;
import com.projeto.entidades.ProdutoNaoIndustrializado;
import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.entidades.ProdutoQuantidadeFixa;
import com.projeto.excecoes.EntradaInvalidaException;
import com.projeto.excecoes.ItemJaExisteException;

/**
 * Classe referente a validar um item.
 * 
 * @author  Jose Davi
 * 			Matheus Gusmao
 * 			Rich Ramalho
 *
 */
public class ValidadorItem {
	
	/**
	 * Metodo com retorno booleano na validacao de um item.
	 * 
	 * @param nome do tipo String, referente a o nome do item.
	 * @param categoria do tipo String, referente a categoria do item.
	 * @param supermercado do tipo String, referente ao supermercado onde o item esta localizado.
	 * @param preco do tipo Double, referente ao preco do item.
	 * @return Boolean True e lanca a excecao se algum erro no cadastro for localizado.
	 */
	public static boolean validaItem(String nome, String categoria, String supermercado, double preco) {
		if (nome.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de item: nome nao pode ser vazio ou nulo.");
		}
		if (categoria.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de item: categoria nao pode ser vazia ou nula.");
		}
		if (!(categoria.equals("alimento industrializado") || categoria.equals("alimento nao industrializado")
				|| categoria.equals("limpeza") || categoria.equals("higiene pessoal"))) {
			throw new EntradaInvalidaException("Erro no cadastro de item: categoria nao existe.");
		}
		if (supermercado.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de item: local de compra nao pode ser vazio ou nulo.");
		}
		if (preco < 0.0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: preco de item invalido.");
		}
		return true;
	}
	
	/**
	 * Metodo com retorno booleano na validacao de um item que ja existe na lista (por quantidade).
	 * 
	 * @param itens referente ao mapa entre inteiro e nome do item.
	 * @param nome do tipo String, referente ao nome do item.
	 * @param categoria do tipo String, referente a categoria do item.
	 * @return Boolean true, mas lanca a excecao se o item ja estiver cadastrado na lista.
	 */
	public static boolean validaItemProQuantJaExiste(Map<Integer, Item> itens, String nome, String categoria) {
		if (itens.containsValue(new ProdutoQuantidadeFixa(5, nome, categoria, 2, "u.m", "Baratao", 2.58))) {
			throw new ItemJaExisteException("Erro no cadastro do item: item ja existe.");
		}
		return true;
	}
	
	/**
	 * Metodo com retorno booleano na validacao de um item ja existente (por unidade).
	 * 
	 * @param itens referente ao mapa entre inteiro e nome do item.
	 * @param nome do tipo String, referente ao nome do item.
	 * @param categoria do tipo String, referente a categoria do item.
	 * @return Boolean true, e lanca a excecao caso o item ja esteja cadastrado na lista.
	 */
	public static boolean validaItemProUnidJaExiste(Map<Integer, Item> itens, String nome, String categoria) {
		if (itens.containsValue(new ProdutoPorUnidade(5, nome, categoria, 1, "Baratao", 2.58))) {
			throw new ItemJaExisteException("Erro no cadastro do item: item ja existe.");
		}
		return true;
	}
	
	/**
	 * Metodo com retorno booleano na validacao de um item ja existente (nao industrializado).
	 * 
	 * @param itens referente ao mapa entre inteiro e nome do item.
	 * @param nome do tipo String, referente ao nome do item.
	 * @param categoria do tipo String, referente a categoria do item.
	 * @return Boolean true, e lanca a excecao caso o item ja esteja cadastrado na lista.
	 */
	public static boolean validaItemProNaoIndusJaExiste(Map<Integer, Item> itens, String nome, String categoria) {
		if (itens.containsValue(new ProdutoNaoIndustrializado(5, nome, categoria, 1, "Baratao", 2.58))) {
			throw new ItemJaExisteException("Erro no cadastro do item: item ja existe.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a alteracao do nome de um item na lista.
	 * 
	 * @param novo do tipo String, referente ao novo nome do item, a ser atualizado.
	 * @return Boolean true, e lanca a excecao caso haja erro na atualizacao do nome do item.
	 */
	public static boolean validaSetNome(String novo) {
		if (novo.trim().equals("")) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: nome nao pode ser vazio ou nulo.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida o incremento de preco em um item.
	 * 
	 * @param localDeCompra do tipo String, referente ao local de compra do produto.
	 * @param preco do tipo Double, referente ao preco de um item.
	 * @return Boolean true, e lanca excecao caso o nome do local de compra seja vazio ou nulo ou se o preco do item for invalido.
	 */
	public static boolean validaAdicionarPrecoItem(String localDeCompra, double preco) {
		if (localDeCompra.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		}
		if (preco < 0.0) {
			throw new EntradaInvalidaException("Erro no cadastro de preco: preco de item invalido.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a categoria de um item.
	 * 
	 * @param categoria do tipo String, referente a categoria de determinado produto.
	 * @return Boolean true, lanca a excecao caso haja erro na categoria do item, categoria inexistente.
	 */
	public static boolean validaCategoria(String categoria) {
		if (!(categoria.equals("alimento industrializado") || categoria.equals("alimento nao industrializado")
				|| categoria.equals("limpeza") || categoria.equals("higiene pessoal"))) {
			throw new EntradaInvalidaException("Erro na listagem de item: categoria nao existe.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a alteracao na categoria de um item.
	 * 
	 * @param novoValor do tipo String, referente a nova categoria do item.
	 * @return Boolean true, lanca a excecao caso a categoria a ser alterada nao exista.
	 */
	public static boolean validaSetCategoria(String novoValor) {
		if (!(novoValor.equals("alimento industrializado") || novoValor.equals("alimento nao industrializado")
				|| novoValor.equals("limpeza") || novoValor.equals("higiene pessoal"))) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: categoria nao existe.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a unidade do item.
	 * 
	 * @param unidade do tipo Inteiro, referente a unidade do item.
	 * @return Boolean true, lanca a excecao caso unidade seja um valor menor que 0 por ser invalido.
	 */
	public static boolean validaUnidade(int unidade) {
		if (unidade < 0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: valor de unidade nao pode ser menor que zero.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a mudanca na unidade de um item.
	 * 
	 * @param unidade do tipo Inteiro, referente a unidade do item.
	 * @return Boolean true, e lanca excecao caso a alteracao se der em um valor unitario menor que 0.
	 */
	public static boolean validaSetUnidade(int unidade) {
		if (unidade < 0) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: valor de unidade nao pode ser menor que zero.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida peso de um determinado item.
	 * 
	 * @param peso do tipo Double, referente ao peso de determinado produto.
	 * @return Boolean true, lanca excecao caso o valor em quilos do produto seja menor que 0.
	 */
	public static boolean validaPeso(double peso) {
		if (peso < 0.0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: valor de quilos nao pode ser menor que zero.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a quantidade de um produto do tipo fixo.
	 * 
	 * @param quantidade do tipo Inteiro, referente a quantidade de determinado produto.
	 * @param medida do tipo String, referente a unidade de medida referente a um item.
	 * @return Boolean true, lanca excecao caso a quantidade ou a unidade de medida sejam vazias ou nulas.
	 */
	public static boolean validaProdutoQuantFixa(int quantidade, String medida) {
		if (quantidade < 0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: valor de quantidade nao pode ser menor que zero.");
		}
		if (medida.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de item: unidade de medida nao pode ser vazia ou nula.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a alteracao na quantidade de um item.
	 * 
	 * @param quantidade do tipo Inteiro, referente a nova quantidade do item.
	 * @return Boolean true, caso a quantidade seja menor que 0 lanca uma excecao.
	 */
	public static boolean validaSetQuantidade(int quantidade) {
		if (quantidade < 0) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: valor de quantidade nao pode ser menor que zero.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida a alteracao na unidade de medida de um item.
	 * 
	 * @param medida do tipo String, referente a unidade de medida do produto determinado.
	 * @return Boolean true, caso a medida seja vazia ou nula retorna tambem uma excecao.
	 */
	public static boolean validaSetMedida(String medida) {
		if (medida.trim().equals("")) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: unidade de medida nao pode ser vazia ou nula.");
		}
		return true;
	}
	
	/**
	 * Metodo que valida uma alteracao no peso do produto.
	 * 
	 * @param novoValor do tipo Double, referente ao novo valor em quilos do item.
	 * @return Boolean true, mas, caso o valor em quilos seja menor que 0 lanca uma excecao.
	 */
	public static boolean validaSetQuilos(double novoValor) {
		if (novoValor < 0) {
			throw new IllegalArgumentException("Erro na atualizacao de item: valor de quilos nao pode ser menor que zero.");
		}
		return true;
	}
}