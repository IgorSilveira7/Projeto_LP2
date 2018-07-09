package com.projeto.controllers;

import java.util.*;
import java.util.Map;

import com.projeto.entidades.Item;
import com.projeto.entidades.ProdutoNaoIndustrializado;
import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.entidades.ProdutoQuantidadeFixa;

public class ControllerItem {
	
	private Map<Integer, Item> itens;
	private int id;
	
	public ControllerItem() {
		this.itens = new TreeMap<>();
		this.id = 1;
	}
	
	public int adicionaItemPorQtd(String nome, String categoria, int quantidade, String medida, String supermercado, double preco) {
		this.itens.put(this.id, new ProdutoQuantidadeFixa(this.id, nome, categoria, quantidade, medida, supermercado, preco));
		return this.id++;
	}
	
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String supermercado, double preco) {
		this.itens.put(this.id, new ProdutoPorUnidade(this.id, nome, categoria, unidade, supermercado, preco));
		return this.id++;
	}
	
	public int adicionaItemPorQuilo(String nome, String categoria, double quilos, String supermercado, double preco) {
		this.itens.put(this.id, new ProdutoNaoIndustrializado(this.id, nome, categoria, quilos, supermercado, preco));
		return this.id++;
	}
	
	public String exibeItem(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro na listagem de item: id invalido.");
		}
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro na listagem de item: item nao existe.");
		}
		return this.itens.get(id).toString();
	}
	
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
			if (novoValor.equals("alimento industrializado") || novoValor.equals("alimento nao industrializado")
			|| novoValor.equals("limpeza") || novoValor.equals("higiene pessoal")) {
				this.itens.get(id).setCategoria(novoValor);
			}
			else {
				throw new IllegalArgumentException("Erro na atualizacao de item: categoria nao existe.");
			}
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
	
	public void adicionaPrecoItem(int id, String supermercado, double preco) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: id de item invalido.");
		}
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro no cadastro de preco: item nao existe.");
		}
		this.itens.get(id).adicionaPrecoItem(supermercado, preco);
	}
	
	public void deletaItem(int id) {
		this.itens.remove(id);
	}
	
}
