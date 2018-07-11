package testesdeunidades;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.projeto.entidades.ProdutoQuantidadeFixa;
import com.projeto.excecoes.EntradaInvalidaException;

public class TesteProdutoQuantFixa {

	private ProdutoQuantidadeFixa produto;
	
	@Before
	public void testProdutoQuantidadeFixa() {
		this.produto = new ProdutoQuantidadeFixa(1, "Pao", "alimento industrializado", 10, "u.m", "Alfredo +", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorNomeVazio() {
		new ProdutoQuantidadeFixa(1, "  ", "alimento industrializado", 10, "u.m", "Alfredo +", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorCategoriaVazia() {
		new ProdutoQuantidadeFixa(1, "Pao", "   ", 10, "u.m", "Alfredo +", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorCategoriaInvalida() {
		new ProdutoQuantidadeFixa(1, "  ", "alimento", 10, "u.m", "Alfredo +", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorUnidadeVazia() {
		new ProdutoQuantidadeFixa(1, "Pao", "alimento industrializado", 10, "  ", "Alfredo +", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorQuantidadeInvalida() {
		new ProdutoQuantidadeFixa(1, "Pao", "alimento industrializado", -1, "u.m", "Alfredo +", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorSupermercadoVazio() {
		new ProdutoQuantidadeFixa(1, "Pao", "alimento industrializado", 10, "u.m", "  ", 0.70);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorPrecoInvalido() {
		new ProdutoQuantidadeFixa(1, "Pao", "alimento industrializado", 10, "u.m", "Alfredo +", -1);
	}
	
	@Test
	public void testSetQuantidade() {
		this.produto.setQuantidade(11);
		String res = "1. Pao, alimento industrializado, 11 u.m, Preco: <Alfredo +, R$ 0,70;>";
		assertEquals(res, this.produto.toString());
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetQuantidadeInvalida() {
		this.produto.setQuantidade(-1);
	}

	@Test
	public void testSetMedida() {
		this.produto.setMedida("U.M");
		String res = "1. Pao, alimento industrializado, 10 U.M, Preco: <Alfredo +, R$ 0,70;>";
		assertEquals(res, this.produto.toString());
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetMedidaErro() {
		this.produto.setMedida("  ");
	}
	
	@Test
	public void testSetNome() {
		this.produto.setNome("Paozinho");
		String res = "1. Paozinho, alimento industrializado, 10 u.m, Preco: <Alfredo +, R$ 0,70;>";
		assertEquals(res, this.produto.toString());
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetNomeInvalida() {
		this.produto.setNome("  ");
	}

	@Test
	public void testSetCategoria() {
		this.produto.setCategoria("alimento nao industrializado");
		String res = "1. Pao, alimento nao industrializado, 10 u.m, Preco: <Alfredo +, R$ 0,70;>";
		assertEquals(res, this.produto.toString());
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetCategoriaInvalida() {
		this.produto.setCategoria("alimento");
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetCategoriaVazia() {
		this.produto.setCategoria("  ");
	}

	@Test
	public void testToString() {
		String res = "1. Pao, alimento industrializado, 10 u.m, Preco: <Alfredo +, R$ 0,70;>";
		assertEquals(res, this.produto.toString());
	}

	@Test
	public void testAdicionaPrecoItem() {
		this.produto.adicionaPrecoItem("Alfredo 2", 0.60);
		String res = "1. Pao, alimento industrializado, 10 u.m, Preco: <Alfredo +, R$ 0,70;Alfredo 2, R$ 0,60;>";
		assertEquals(res, this.produto.toString());
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaPrecoItemSupermercadoInvalido() {
		this.produto.adicionaPrecoItem("  ", 0.60);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testAdicionaPrecoItemPrecoInvalido() {
		this.produto.adicionaPrecoItem("Alfredo 2", -1);
	}

	@Test
	public void testEqualsObject() {
		assertTrue(this.produto.equals(new ProdutoQuantidadeFixa(2, "Pao", "alimento industrializado", 14, "um", "Paozinhos", 0.55)));
	}
	
	@Test
	public void testEqualsObjectNomeDiferente() {
		assertFalse(this.produto.equals(new ProdutoQuantidadeFixa(2, "Pao1", "alimento industrializado", 14, "um", "Paozinhos", 0.55)));
	}
	
	@Test
	public void testEqualsObjectCategoriaDiferente() {
		assertFalse(this.produto.equals(new ProdutoQuantidadeFixa(2, "Pao", "alimento nao industrializado", 14, "um", "Paozinhos", 0.55)));
	}
}