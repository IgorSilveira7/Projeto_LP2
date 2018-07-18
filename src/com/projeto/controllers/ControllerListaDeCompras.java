package com.projeto.controllers;

import java.util.Map;
import com.projeto.entidades.ListaDeCompras;

public class ControllerListaDeCompras {
	
	private ControllerItem controllerItem;
	private Map<String, ListaDeCompras> listasDeCompras;
	
	public ControllerListaDeCompras(ControllerItem controller) {
		this.controllerItem = controller;
	}

}
