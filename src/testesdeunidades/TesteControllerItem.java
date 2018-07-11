package testesdeunidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.projeto.controllers.ControllerItem;
import com.projeto.excecoes.EntradaInvalidaException;

public class TesteControllerItem {
	
	private ControllerItem c;
	
	@Before
	public void before() {
		this.c = new ControllerItem();
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQtdNomeInvalido() {
		this.c.adicionaItemPorQtd("", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQtdCategoriaInvalido() {
		this.c.adicionaItemPorQtd("Pao", "", 5, "u.m", "Alfredo +", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQtdQuantInvalido() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", -1, "u.m", "Alfredo +", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQtdMedidaInvalido() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", -1, "", "Alfredo +", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQtdSuperInvalido() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", -1, "u.m", "", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQtdPrecoInvalido() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", -1, "u.m", "Alfredo +", -1);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorUnidadeNomeInvalido() {
		this.c.adicionaItemPorUnidade("", "alimento nao industrializado", 2, "Alfredo +", 2.47);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorUnidadeCategoriaInvalido() {
		this.c.adicionaItemPorUnidade("Pao", "", 2, "Alfredo +", 2.47);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorUnidadeUnidadeInvalido() {
		this.c.adicionaItemPorUnidade("Pao", "alimento industrializado", -1, "Alfredo +", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorUnidadeSuperInvalido() {
		this.c.adicionaItemPorUnidade("Pao", "alimento industrializado", 2, " ", 2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorUnidadePrecoInvalido() {
		this.c.adicionaItemPorUnidade("Pao", "alimento industrializado", 2, "Alfredo +", -2.58);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQuiloNomeInvalido() {
		this.c.adicionaItemPorQuilo("", "alimento nao industrializado", 2.4, "Alfredo +", 2.47);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQuiloCategoriaInvalido() {
		this.c.adicionaItemPorQuilo("Paozinho", "alimentos nao industrializados", 2.4, "Alfredo +", 2.47);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQuiloInvalido() {
		this.c.adicionaItemPorQuilo("Paozinho", "alimento nao industrializado", -1, "Alfredo +", 2.47);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQuiloSuperInvalido() {
		this.c.adicionaItemPorQuilo("Paozinho", "alimento nao industrializado", 2.4, " ", 2.47);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaItemPorQuiloPrecoInvalido() {
		this.c.adicionaItemPorQuilo("Paozinho", "alimento nao industrializado", 2.4, "Alfredo +", -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExibeItemNaoExiste() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.exibeItem(2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExibeItemIdNaoExiste() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.exibeItem(0);
	}
	
	@Test
	public void testExibeItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		String res = "1. Pao, alimento industrializado, 5 u.m, Preco: <Alfredo +, R$ 2,58;>";
		assertEquals(res, this.c.exibeItem(1));
	}
	
	@Test
	public void testAtualizaNomeItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.atualizaItem(1, "nome", "Pao Doce");
		String res = "1. Pao Doce, alimento industrializado, 5 u.m, Preco: <Alfredo +, R$ 2,58;>";
		assertEquals(res, this.c.exibeItem(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAtualizaNomeInvalidoItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.atualizaItem(1, "nome", " ");
	}
	
	@Test
	public void testAtualizaCategoriaItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.atualizaItem(1, "categoria", "alimento nao industrializado");
		String res = "1. Pao, alimento nao industrializado, 5 u.m, Preco: <Alfredo +, R$ 2,58;>";
		assertEquals(res, this.c.exibeItem(1));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAtualizaCategoriaInvalidaItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.atualizaItem(1, "categoria", "alimentos nao industrializados");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAtualizaItemNaoExiste() {
		this.c.atualizaItem(1, "categoria", "alimentos nao industrializados");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAtualizaItemAtributoNaoExiste() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.atualizaItem(1, "pao", "alimentos nao industrializados");
	}
	
	@Test
	public void testAdicionaPrecoItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaPrecoItem(1, "Baratao", 1.25);
		String res = "1. Pao, alimento industrializado, 5 u.m, Preco: <Baratao, R$ 1,25;Alfredo +, R$ 2,58;>";
		assertEquals(res, this.c.exibeItem(1));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaPrecoInvalido() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaPrecoItem(1, "Baratao", -1);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaPrecoSupermercadoInvalido() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaPrecoItem(1, " ", 1.14);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAdicionaPrecoItemNaoExiste() {
		this.c.adicionaPrecoItem(1, "Baratao", 1.14);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeletarItemNaoExiste() {
		this.c.deletaItem(1);
	}
	
	@Test
	public void testDeletarItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.deletaItem(1);
	}
	
	@Test
	public void testGetItem() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaItemPorQuilo("Arroz", "alimento industrializado", 5, "Baratao", 2.10);
		String res = "2. Arroz, alimento industrializado, Preco por quilo: <Baratao, R$ 2,10;>";
		assertEquals(res, this.c.getItem(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetItemNaoExiste() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaItemPorQuilo("Arroz", "alimento industrializado", 5, "Baratao", 2.10);
		String res = "2. Arroz, alimento industrializado, Preco por quilo: <Baratao, R$ 2,10;>";
		assertEquals(res, this.c.getItem(2));
	}
	
	@Test
	public void testGetItemMenorPreco() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaItemPorQuilo("Arroz", "alimento industrializado", 5, "Baratao", 2.10);
		this.c.adicionaItemPorUnidade("Bolacha", "alimento nao industrializado", 2, "Alfredo plus", 2.4);
		String res = "2. Arroz, alimento industrializado, Preco por quilo: <Baratao, R$ 2,10;>";
		assertEquals(res, this.c.getItemPorMenorPreco(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetItemMenorPrecoNaoExiste() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaItemPorQuilo("Arroz", "alimento industrializado", 5, "Baratao", 2.10);
		this.c.getItemPorMenorPreco(4);
	}
	
	@Test
	public void testGetItemPorCategoria() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaItemPorQuilo("Arroz", "alimento industrializado", 5, "Baratao", 2.10);
		this.c.adicionaItemPorUnidade("Bolacha", "alimento nao industrializado", 2, "Alfredo plus", 2.4);
		String res = "2. Arroz, alimento industrializado, Preco por quilo: <Baratao, R$ 2,10;>";
		assertEquals(res, this.c.getItemPorCategoria("alimento industrializado", 0));
	}
	
	@Test
	public void testGetItemPorPesquisa() {
		this.c.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.c.adicionaItemPorQuilo("Arroz gostoso", "alimento industrializado", 5, "Baratao", 2.10);
		this.c.adicionaItemPorUnidade("Arroz bom", "alimento nao industrializado", 2, "Alfredo plus", 2.4);
		String res = "3. Arroz bom, alimento nao industrializado, Preco: <Alfredo plus, R$ 2,40;>";
		assertEquals(res, this.c.getItemPorPesquisa("arroz", 0));
	}
}
