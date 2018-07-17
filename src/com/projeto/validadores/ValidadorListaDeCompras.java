package com.projeto.validadores;

import com.projeto.excecoes.EntradaInvalidaException;

public class ValidadorListaDeCompras {
	
	public static boolean testeDescritor(String descritor) {
		if (descritor == null) {
			throw new EntradaInvalidaException("Erro na criacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
		if (descritor.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na criacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
		return true;
	}
	
	public static boolean testeFinalizarCompra(String localCompra, double valor) {
		if (valor <= 0) {
			throw new EntradaInvalidaException("Erro na finalizacao de lista de compras: valor final da lista invalido.");
		}
		if (localCompra == null) {
			throw new EntradaInvalidaException("Erro na finalizacao de lista de compras: local de compra da lista invalido.");
		}
		if (localCompra.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na finalizacao de lista de compras: local nao pode ser vazio ou nulo.");
		}
		return true;
	}
	
	public static boolean validaData(String data) {
		if (data == null) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: data nao pode ser vazia ou nula.");
		}
		if (data.trim().isEmpty()) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: data nao pode ser vazia ou nula.");
		}
		if (!(data.split("/").length == 3)) {
			throw new EntradaInvalidaException("Erro na pesquisa de compra: data em formato invalido, tente dd/MM/yyyy");
		}
		return true;
	}
}
