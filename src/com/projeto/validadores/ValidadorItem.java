package com.projeto.validadores;

import java.util.Map;

import com.projeto.entidades.Item;
import com.projeto.entidades.ProdutoNaoIndustrializado;
import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.entidades.ProdutoQuantidadeFixa;
import com.projeto.excecoes.EntradaInvalidaException;
import com.projeto.excecoes.ItemJaExisteException;
import com.projeto.validadores.*;

public class ValidadorItem {
	
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
	
	public static boolean validaItemProQuantJaExiste(Map<Integer, Item> itens, String nome, String categoria) {
		if (itens.containsValue(new ProdutoQuantidadeFixa(5, nome, categoria, 2, "u.m", "Baratao", 2.58))) {
			throw new ItemJaExisteException("Erro no cadastro do item: item ja existe.");
		}
		return true;
	}
	
	public static boolean validaItemProUnidJaExiste(Map<Integer, Item> itens, String nome, String categoria) {
		if (itens.containsValue(new ProdutoPorUnidade(5, nome, categoria, 1, "Baratao", 2.58))) {
			throw new ItemJaExisteException("Erro no cadastro do item: item ja existe.");
		}
		return true;
	}
	
	public static boolean validaItemProNaoIndusJaExiste(Map<Integer, Item> itens, String nome, String categoria) {
		if (itens.containsValue(new ProdutoNaoIndustrializado(5, nome, categoria, 1, "Baratao", 2.58))) {
			throw new ItemJaExisteException("Erro no cadastro do item: item ja existe.");
		}
		return true;
	}
	
	public static boolean validarSetNome(String novo) {
		if (novo.trim().equals("")) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: nome nao pode ser vazio ou nulo.");
		}
		return true;
	}
	
	public static boolean validaAdicionarPrecoItem(String localDeCompra, double preco) {
		if (localDeCompra.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		}
		if (preco < 0.0) {
			throw new EntradaInvalidaException("Erro no cadastro de preco: preco de item invalido.");
		}
		return true;
	}
	
	public static boolean validaSetCategoria(String novoValor) {
		if (!(novoValor.equals("alimento industrializado") || novoValor.equals("alimento nao industrializado")
				|| novoValor.equals("limpeza") || novoValor.equals("higiene pessoal"))) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: categoria nao existe.");
		}
		return true;
	}
	
	public static boolean validaUnidade(int unidade) {
		if (unidade < 0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: valor de unidade nao pode ser menor que zero.");
		}
		return true;
	}
	
	public static boolean validaSetUnidade(int unidade) {
		if (unidade < 0) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: valor de quantidade nao pode ser menor que zero.");
		}
		return true;
	}
	
	public static boolean validaPeso(double peso) {
		if (peso < 0.0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: valor de quilos nao pode ser menor que zero.");
		}
		return true;
	}
	
	public static boolean validaSetPeso(double peso) {
		if (peso < 0) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: valor de quilos nao pode ser menor que zero.");
		}
		return true;
	}
	
	public static boolean validaProdutoQuantFixa(int quantidade, String medida) {
		if (quantidade < 0) {
			throw new EntradaInvalidaException("Erro no cadastro de item: valor de quantidade nao pode ser menor que zero.");
		}
		if (medida.trim().equals("")) {
			throw new EntradaInvalidaException("Erro no cadastro de item: unidade de medida nao pode ser vazia ou nula.");
		}
		return true;
	}
	
	public static boolean validaSetQuantidade(int quantidade) {
		if (quantidade < 0) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: valor de quantidade nao pode ser menor que zero.");
		}
		return true;
	}
	
	public static boolean validaSetMedida(String medida) {
		if (medida.trim().equals("")) {
			throw new EntradaInvalidaException("Erro na atualizacao de item: unidade de medida nao pode ser vazia ou nula.");
		}
		return true;
	}
}