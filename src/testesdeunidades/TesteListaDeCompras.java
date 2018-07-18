package testesdeunidades;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;

import com.projeto.entidades.Item;
import com.projeto.entidades.ListaDeCompras;
import com.projeto.entidades.ProdutoNaoIndustrializado;
import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.entidades.ProdutoQuantidadeFixa;
import com.projeto.excecoes.EntradaInvalidaException;

public class TesteListaDeCompras {

	ListaDeCompras l;

	@Before
	public void Before() {
		l = new ListaDeCompras("feira semanal");
	}

	@Test
	public void ListaDeCompra() {
		ListaDeCompras l1 = new ListaDeCompras("feira mensal");
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeConstrutorDescritorNulo() {
		ListaDeCompras l1 = new ListaDeCompras(null);
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeConstrutorDescritorVazio() {
		ListaDeCompras l1 = new ListaDeCompras("");
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeConstrutorDescritorEspacos() {
		ListaDeCompras l1 = new ListaDeCompras("        ");
	}

	@Test
	public void testeAdicionaCompraALista() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		int compara = l.adicionaCompraALista(2, i);
		assertEquals(compara, 1);
	}

	@Test
	public void testePesquisaCompraEmLista() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.adicionaCompraALista(2, i);
		String compara = "2 biscoito allmight, alimento industrializado";
		assertEquals(l.pesquisaCompraEmLista(i), compara);
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testePesquisaCompraEmListaItemNaoCadastrado() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		Item item2 = new ProdutoPorUnidade(1, "detergente", "limpeza", 1, "PokeMark", 2.50);
		l.adicionaCompraALista(2, i);
		l.pesquisaCompraEmLista(item2);
	}

	@Test
	public void testeVerificarItemEmListaTrue() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.adicionaCompraALista(2, i);
		assertTrue(l.verificarItemEmLista(i));
	}

	@Test
	public void testeVerificarItemEmListaFalse() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		assertFalse(l.verificarItemEmLista(i));
	}

	@Test
	public void testeAtualizaCompraDeListaAdiciona() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.adicionaCompraALista(2, i);
		l.atualizaCompraDeLista(i, "adiciona", 2);
		String compara = "4 biscoito allmight, alimento industrializado";
		assertEquals(l.pesquisaCompraEmLista(i), compara);
	}

	@Test
	public void testeAtualizaCompraDeListaDiminui() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.adicionaCompraALista(2, i);
		l.atualizaCompraDeLista(i, "diminui", 1);
		String compara = "1 biscoito allmight, alimento industrializado";
		assertEquals(l.pesquisaCompraEmLista(i), compara);
	}

	@Test
	public void testeAtualizaCompraDeListaZera() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.adicionaCompraALista(2, i);
		l.atualizaCompraDeLista(i, "diminui", 2);
		assertFalse(l.verificarItemEmLista(i));
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeAtualizaCompraDeListaItemNaoCadastrado() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.atualizaCompraDeLista(i, "adiciona", 3);
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasValorNegativo() {
		l.finalizarListaDeCompras("PokeMark", -1);
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasLocalNulo() {
		l.finalizarListaDeCompras(null, 100);
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasLocalVazio() {
		l.finalizarListaDeCompras("", 100);
	}

	@Test(expected = EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasLocalEspacos() {
		l.finalizarListaDeCompras("       ", 100);
	}

	@Test
	public void testeGetItemLista() {
		Item i1 = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		Item i2 = new ProdutoQuantidadeFixa(2, "Pao ", "alimento industrializado", 5, "u.m", "padaria +", 0.20);
		Item i3 = new ProdutoNaoIndustrializado(3, "Carne", "alimento nao industrializado", 1.5, "walmart", 7.50);

		l.adicionaCompraALista(5, i1);
		l.adicionaCompraALista(1, i2);
		l.adicionaCompraALista(2, i3);

		String compara = "2 Carne, alimento nao industrializado";
		assertEquals(l.getItemLista(2), compara);
	}

	@Test
	public void testeDeletaCompraDeLista() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		l.adicionaCompraALista(2, i);
		l.deletaCompraDeLista(i);
		assertFalse(l.verificarItemEmLista(i));
	}

	@Test
	public void testeToString() {
		Item i = new ProdutoPorUnidade(1, "biscoito allmight", "alimento industrializado", 10, "walmart", 1.50);
		Item i2 = new ProdutoPorUnidade(1, "bolacha Endeavor", "alimento industrializado", 20, "walmart", 2.00);

		l.adicionaCompraALista(5, i);
		l.adicionaCompraALista(1, i2);

		String compara = "17/07/2018 - feira semanal";
		assertEquals(l.toString(), compara);
	}

}
