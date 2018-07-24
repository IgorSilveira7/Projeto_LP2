package testesdeunidades;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import com.projeto.controllers.ControllerItem;
import com.projeto.controllers.ControllerListaDeCompras;
import com.projeto.excecoes.*;

public class TesteControllerListaDeCompras {

	private ControllerListaDeCompras cListaDeCompras;
	private ControllerItem cItem;
	private LocalDate data;
	private DateTimeFormatter formato;
	
	@Before
	public void teste() {
		this.cItem = new ControllerItem();
		this.cItem.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.cItem.adicionaItemPorUnidade("Biscoite", "alimento industrializado", 5, "Alfredo plus plus", 1.19);
		this.cItem.adicionaItemPorQuilo("Arroz", "alimento industrializado", 2, "Alfredo ++", 1.50);
		this.cListaDeCompras = new ControllerListaDeCompras(cItem);
		this.cListaDeCompras.criaListaDeCompra("feira");
		this.formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = LocalDate.now();
	}
	
	@Test
	public void testeCriaListaDeCompras() {
		assertEquals("feira semanal", this.cListaDeCompras.criaListaDeCompra("feira semanal"));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeCriaListaDeComprasDescritorNulo() {
		this.cListaDeCompras.criaListaDeCompra(null);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeCriaListaDeComprasDescritorVazio() {
		this.cListaDeCompras.criaListaDeCompra("  ");
	}
	
	@Test
	public void testeAdicionaCompraAListaDeCompra() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		assertEquals("4 Pao, alimento industrializado, 5 u.m", this.cListaDeCompras.getItemLista("feira", 0));
	}
	
	@Test(expected=ItemNaoExisteException.class)
	public void testeAdicionaCompraAListaDeCompraItemNaoExiste() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 5);
	}
	
	@Test
	public void testePesquisaCompraEmLista() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		assertEquals("4 Pao, alimento industrializado, 5 u.m", this.cListaDeCompras.pesquisaCompraEmLista("feira", 1));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaCompraEmListaDescritorNulo() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.pesquisaCompraEmLista(null, 1);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaCompraEmListaDescritorVazio() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.pesquisaCompraEmLista("  ", 1);
	}
	
	@Test(expected=ItemNaoExisteException.class)
	public void testePesquisaCompraEmListaItemNaoExiste() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.pesquisaCompraEmLista("feira", 0);
	}
	
	@Test
	public void testePesquisaListaDeCompras() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		assertEquals("feira", this.cListaDeCompras.pesquisaListaDeCompras("feira"));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaListaDeComprasDescritorNulo() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.pesquisaListaDeCompras(null);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaListaDeComprasDescritorVazio() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.pesquisaListaDeCompras("   ");
	}
	
	@Test(expected=ListaDeCompraNaoExisteException.class)
	public void testePesquisaListaDeComprasListaNaoExiste() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.pesquisaListaDeCompras("ao vivo");
	}
	
	@Test
	public void testeAtualizaCompraDeListaAdiciona() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.atualizaCompraDeLista("feira", 1, "adiciona", 2);
		assertEquals("6 Pao, alimento industrializado, 5 u.m", this.cListaDeCompras.getItemLista("feira", 0));
	}
	
	@Test
	public void testeAtualizaCompraDeListaDiminui() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.atualizaCompraDeLista("feira", 1, "diminui", 2);
		assertEquals("2 Pao, alimento industrializado, 5 u.m", this.cListaDeCompras.getItemLista("feira", 0));
	}
	
	@Test(expected=OperacaoInvalidaException.class)
	public void testeAtualizaCompraDeListaOperacaoInvalida() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.atualizaCompraDeLista("feira", 1, "aumenta", 2);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeAtualizaCompraDeListaIdInvalido() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.atualizaCompraDeLista("feira", 3, "adiciona", 2);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeRemoverItemComAtualizaCompraDeLista() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.atualizaCompraDeLista("feira", 1, "diminui", 4);
		this.cListaDeCompras.pesquisaCompraEmLista("feira", 1);
	}
	
	@Test
	public void testeFinalizarListaDeCompras() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.finalizarListaDeCompras("feira", "Alfredo +", 1500);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasDescritorNulo() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.finalizarListaDeCompras(null, "Alfredo +", 1500);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasDescritorVazio() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.finalizarListaDeCompras("  ", "Alfredo +", 1500);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasValorInvalido() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.finalizarListaDeCompras("feira", "Alfredo +", 0);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasLocalDeCompraVazio() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.finalizarListaDeCompras("feira", "  ", 0);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeFinalizarListaDeComprasLocalDeCompraNulo() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.finalizarListaDeCompras("feira", null, 0);
	}
	
	@Test
	public void testeDeletaCompraDeLista() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.deletaCompraDeLista("feira", 1);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeDeletaCompraDeListaDescritorNulo() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.deletaCompraDeLista(null, 1);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeDeletaCompraDeListaDescritorVazio() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.deletaCompraDeLista("  ", 1);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeDeletaCompraDeListaIdNaoExiste() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.deletaCompraDeLista("feira", 2);
	}
	
	@Test
	public void testeGetItemLista() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.adicionaCompraALista("feira", 1, 2);
		this.cListaDeCompras.adicionaCompraALista("feira", 2, 3);
		assertEquals("2 Arroz, alimento industrializado", this.cListaDeCompras.getItemLista("feira", 0));
	}
	
	@Test
	public void testeGetItemListaIdNaoExiste() {
		this.cListaDeCompras.adicionaCompraALista("feira", 4, 1);
		this.cListaDeCompras.adicionaCompraALista("feira", 1, 2);
		this.cListaDeCompras.adicionaCompraALista("feira", 2, 3);
		assertEquals("", this.cListaDeCompras.getItemLista("feira", -1));
	}
	
	@Test
	public void testeGetItemListaPorData() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("cafe");
		assertEquals("almoco", this.cListaDeCompras.getItemListaPorData(this.data.format(formato), 0));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeGetItemListaPorDataVazia() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("cafe");
		this.cListaDeCompras.getItemListaPorData("  ", 0);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeGetItemListaPorDataNula() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("cafe");
		this.cListaDeCompras.getItemListaPorData(null, 0);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testeGetItemListaPorDataIdInvalido() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("cafe");
		this.cListaDeCompras.getItemListaPorData("  ", 3);
	}
	
	@Test
	public void testeGetItemListaPorItem() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.adicionaCompraALista("almoco", 2, 1);
		this.cListaDeCompras.adicionaCompraALista("almoco", 2, 2);
		this.cListaDeCompras.adicionaCompraALista("almoco", 2, 3);
		assertEquals("almoco", this.cListaDeCompras.getItemListaPorData(this.data.format(formato), 0));
	}
	
	@Test
	public void testePesquisaListasDeComprasPorItem() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.adicionaCompraALista("almoco", 2, 1);
		this.cListaDeCompras.adicionaCompraALista("almoco", 2, 2);
		this.cListaDeCompras.adicionaCompraALista("almoco", 2, 3);
		assertEquals(this.data.format(this.formato) + " - almoco", this.cListaDeCompras.pesquisaListasDeComprasPorItem(1));
	}
	
	@Test(expected=ItemNaoExisteException.class)
	public void testePesquisaListasDeComprasPorItemIdNaoExiste() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("lanche da tarde");
		assertEquals(this.data.format(this.formato) + " - almoco", this.cListaDeCompras.pesquisaListasDeComprasPorItem(0));
	}
	
	@Test
	public void testePesquisaListasDeComprasPorData() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("lanche da tarde");
		assertEquals(this.data.format(this.formato) + " - almoco", this.cListaDeCompras.pesquisaListasDeComprasPorData(this.data.format(this.formato)));
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaListasDeComprasPorDataNula() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("lanche da tarde");
		this.cListaDeCompras.pesquisaListasDeComprasPorData(null);
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaListasDeComprasPorDataVazia() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("lanche da tarde");
		this.cListaDeCompras.pesquisaListasDeComprasPorData("  ");
	}
	
	@Test(expected=EntradaInvalidaException.class)
	public void testePesquisaListasDeComprasPorDataErro() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.criaListaDeCompra("lanche da tarde");
		this.cListaDeCompras.pesquisaListasDeComprasPorData("10/2018");
	}
	
	@Test
	public void testeO() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.adicionaCompraALista("janta", 5, 1);
		this.cListaDeCompras.adicionaCompraALista("janta", 13, 2);
		String a = this.cListaDeCompras.geraAutomaticaUltimaLista();
		assertEquals("Lista automatica 1 24/07/2018", this.cListaDeCompras.getItemLista(a, 1));
	}
	
	@Test
	public void testeX() {
		this.cListaDeCompras.criaListaDeCompra("almoco");
		this.cListaDeCompras.criaListaDeCompra("janta");
		this.cListaDeCompras.adicionaCompraALista("almoco", 1, 1);
		//String a = this.cListaDeCompras.geraAutomaticaItem(1);
		//assertEquals("Lista automatica 2 24/07/2018", this.cListaDeCompras.getItemLista(a, 0));
	}
}