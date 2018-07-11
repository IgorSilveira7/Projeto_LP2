package com.projeto.testedeunidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.projeto.entidades.ProdutoPorUnidade;
import com.projeto.excecoes.EntradaInvalidaException;



public class TesteProdutoPorUnidade {
	
	private ProdutoPorUnidade p;
	
	@Before
	public void testProdutoPorUnidade() {
		this.p = new ProdutoPorUnidade(1, "Semente dos deuses", "alimento nao industrializado", 1, "Torre Karim",  100.99);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorNomeVazio() {
		new ProdutoPorUnidade(1, "", "alimento nao industrializado", 1, "Torre Karim", 100.99);	
		}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testContrutorCategoriaVazia() {
		new ProdutoPorUnidade(1, "Semente dos deuses", "", 1, "Torre Karim", 100.99);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testContrutorCategoriaInvalida() {
		new ProdutoPorUnidade(1, "Semente dos deuses", "produtos DBZ", 1, "Torre Karim", 100.99);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorQuantidadeInvalida() {
		new ProdutoPorUnidade(1, "Semente dos deuses", "alimento nao industrializado", -1, "Torre Karim", 100.99);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorSupermercadoVazio() {
		new ProdutoPorUnidade(1, "Semente dos deuses", "alimento nao industrializado", 1, "", 100.99);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testConstrutorPrecoInvalido() {
		new ProdutoPorUnidade(1, "Semente dos deuses", "alimento nao industrializado", 1, "Torre Karim", -10.01);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetUnidadeInvalida() {
		this.p.setUnidade(-1);
	}


	@Test
	public void testToString() {
		String comp = "1. Semente dos deuses, alimento nao industrializado, Preco: <Torre Karim, R$ 100,99;>";
		assertEquals(comp, this.p.toString());
	}

	@Test
	public void testAdicionaPrecoItem() {
		this.p.adicionaPrecoItem("Torre Dende", 99.99);
		String comp = "1. Semente dos deuses, alimento nao industrializado, Preco: <Torre Dende, R$ 99,99;Torre Karim, R$ 100,99;>";
		assertEquals(comp, this.p.toString());
		
	}

	@Test
	public void testSetNome() {
		this.p.setNome("So sementes");
		String comp = "1. So sementes, alimento nao industrializado, Preco: <Torre Karim, R$ 100,99;>";
		assertEquals(comp, this.p.toString());
	}

	@Test
	public void testGetNome() {
		assertEquals("Semente dos deuses", this.p.getNome());
	}

	@Test
	public void testSetCategoria() {
		this.p.setCategoria("alimento industrializado");
		String comp = "1. Semente dos deuses, alimento industrializado, Preco: <Torre Karim, R$ 100,99;>";
		assertEquals(comp, this.p.toString());
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetCategoriaInvalida() {
		this.p.setCategoria("alimento");
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testSetCategoriaVazia() {
		this.p.setCategoria("  ");
	}

	@Test
	public void testGetCategoria() {
		assertEquals("alimento nao industrializado", this.p.getCategoria());
	}


	@Test
	public void testEqualsObject() {
		ProdutoPorUnidade pOutro = new ProdutoPorUnidade(2, "Semente dos deuses", "alimento nao industrializado", 1, "Torre Karim",  100.99);
		assertTrue(this.p.equals(pOutro));
	}
	
	@Test
	public void testEqualsNomeDiferente() {
		ProdutoPorUnidade pOutro = new ProdutoPorUnidade(1, "Agua Saiyan", "alimento nao industrializado", 1, "Torre Karim",  100.99);
		assertFalse(this.p.equals(pOutro));
	}
	
	@Test
	public void testEqualsCategoriaDiferente() {
		ProdutoPorUnidade pOutro = new ProdutoPorUnidade(1, "Semente dos deuses", "alimento industrializado", 1, "Torre Karim",  100.99);
		assertFalse(this.p.equals(pOutro));
	}
	
	
	


}
