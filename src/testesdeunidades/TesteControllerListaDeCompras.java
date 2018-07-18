package testesdeunidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.projeto.controllers.ControllerItem;
import com.projeto.controllers.ControllerListaDeCompras;

public class TesteControllerListaDeCompras {

	private ControllerListaDeCompras cListaDeCompras;
	private ControllerItem cItem;
	
	@Before
	public void test() {
		this.cItem = new ControllerItem();
		this.cItem.adicionaItemPorQtd("Pao", "alimento industrializado", 5, "u.m", "Alfredo +", 2.58);
		this.cListaDeCompras = new ControllerListaDeCompras(cItem);
	}
}
