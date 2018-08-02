package com.projeto.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.projeto.entidades.Compra;
import com.projeto.entidades.Item;
import com.projeto.entidades.ListaDeCompras;
import com.projeto.entidades.Supermercado;
import com.projeto.excecoes.ArquivoNaoExiste;
import com.projeto.excecoes.ItemNaoExisteException;
import com.projeto.excecoes.ListaDeCompraNaoExisteException;
import com.projeto.ordenacao.OrdenaListaDeComprasPorData;
import com.projeto.ordenacao.OrdenarListaDeComprasPorDescritor;
import com.projeto.ordenacao.OrdenarSupermercadoPeloValor;
import com.projeto.validadores.ValidadorListaDeCompras;

/**
 * Classe que controla as listas de compras cadastradas no sistema. Cadastrada,
 * exibe, atualiza e deletar listas de compras.
 * 
 * @author Rich Ramalho
 * @author Jose Davi
 * @author Matheus Gusmao
 * @author Igor Silveira
 *
 */
public class ControllerListaDeCompras {

	/**
	 * Atributo referente as listas de compras cadastradas.
	 */
	private Map<String, ListaDeCompras> listasDeCompras;
	/**
	 * Atributo referente ao controlador de itens.
	 */
	private ControllerItem controllerItem;

	/**
	 * Construtor.
	 * 
	 * @param controller
	 *            ControllerItem referente ao controlador de itens.
	 */
	public ControllerListaDeCompras(ControllerItem controller) {
		this.listasDeCompras = new LinkedHashMap<>();
		this.controllerItem = controller;
	}

	/**
	 * Metodo responsavel por criar a lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @return String referente ao descritor referente a lista de compras criada.
	 */
	public String criaListaDeCompra(String descritor) {
		ValidadorListaDeCompras.validaConstrutor(descritor);
		this.listasDeCompras.put(descritor, new ListaDeCompras(descritor));
		return descritor;
	}

	/**
	 * Metodo responsavel por adicionar compras a lista de compras criada.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param qntd
	 *            Double referente a quantidade do item.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 */
	public void adicionaCompraALista(String descritor, int qntd, int id) {
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na compra de item: item nao existe no sistema.");
		}
		this.listasDeCompras.get(descritor).adicionaCompraALista(qntd, this.controllerItem.getItemPeloId(id));
	}

	/**
	 * Metodo responsavel por pesquisar uma compra na lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @return String referente a pesquisa do item em determinada lista de compras.
	 */
	public String pesquisaCompraEmLista(String descritor, int id) {
		ValidadorListaDeCompras.validaDescritor(descritor);
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na pesquisa de compra: item id invalido.");
		}
		return this.listasDeCompras.get(descritor).pesquisaCompraEmLista(this.controllerItem.getItemPeloId(id));
	}

	/**
	 * Metodo responsavel por pesquisar uma lista de compra especifica.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @return String referente a lista de compras pesquisada.
	 */
	public String pesquisaListaDeCompras(String descritor) {
		ValidadorListaDeCompras.validaDescritor(descritor);
		if (!this.listasDeCompras.containsKey(descritor)) {
			throw new ListaDeCompraNaoExisteException("Erro na pesquisa de compra: lista de compras nao existe.");
		}
		return this.listasDeCompras.get(descritor).getDescritor();
	}

	/**
	 * Metodo responsavel por atualizar a lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @param operacao
	 *            String referente ao tipo de operacao que sera realizada.
	 * @param novaQuantidade
	 *            Double referente a nova quantidade do item, atualizando assim a
	 *            lista.
	 */
	public void atualizaCompraDeLista(String descritor, int id, String operacao, int novaQuantidade) {
		ValidadorListaDeCompras.validaOperacao(operacao);
		this.listasDeCompras.get(descritor).atualizaCompraDeLista(this.controllerItem.getItemPeloId(id), operacao,
				novaQuantidade);
	}

	/**
	 * Metodo responsavel por finalizar uma lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param localDeCompra
	 *            String referente ao local de compra do item.
	 * @param valorFinal
	 *            Double referente ao ultimo valor do item.
	 */
	public void finalizarListaDeCompras(String descritor, String localDeCompra, double valorFinal) {
		ValidadorListaDeCompras.testeFinalizarCompra(localDeCompra, valorFinal, descritor);
		this.listasDeCompras.get(descritor).finalizarListaDeCompras(localDeCompra, valorFinal);
	}

	/**
	 * Metodo que deleta uma lista de compras.
	 * 
	 * @param descritor
	 *            String referente a descricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 */
	public void deletaCompraDeLista(String descritor, int id) {
		ValidadorListaDeCompras.validaDeletarLista(descritor);
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na exclusao de compra: item nao existe no sistema.");
		}
		this.listasDeCompras.get(descritor).deletaCompraDeLista(this.controllerItem.getItemPeloId(id));
	}

	/**
	 * Metodo que retorna o item da lista.
	 * 
	 * @param descritor
	 *            String referente a decricao da lista de compras.
	 * @param id
	 *            Inteiro referente a identificacao do item.
	 * @return String referente a lista de compras e o item.
	 */
	public String getItemLista(String descritor, int id) {
		return this.listasDeCompras.get(descritor).getItemLista(id);
	}

	/**
	 * Metodo que retorna o descritor de uma lista de compra criada em tal data em
	 * uma determinada posicao da lista ordenada por ordem alfabetica.
	 * 
	 * @param data
	 *            String referente a data de criacao do item(dd/mm/aaaa).
	 * @param posicao
	 *            Inteiro referente a posicao do item da lista de compra.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemListaPorData(String data, int posicao) {
		ValidadorListaDeCompras.validaData(data);
		List<ListaDeCompras> ordenado = new ArrayList<>(this.listasDeCompras.values());
		Collections.sort(ordenado, new OrdenarListaDeComprasPorDescritor());

		int cont = 0;
		for (ListaDeCompras listaDeCompras : ordenado) {
			if (listaDeCompras.getData().equals(data)) {
				if (cont == posicao) {
					return listaDeCompras.getDescritor();
				}
				cont += 1;
			}
		}
		return "";
	}

	/**
	 * Metodo que retorna uma representacao textual de uma lista de compra em uma
	 * determinada posicao da lista ordenada por ordem alfabetica e pela data.
	 * 
	 * @param id
	 *            Inteiro referente a data de criacao do item(dd/mm/aaaa).
	 * @param posicao
	 *            Inteiro referente a posicao do item da lista de compra.
	 * @return String referente a representacao textual do item.
	 */
	public String getItemListaPorItem(int id, int posicao) {
		List<ListaDeCompras> lista = new ArrayList<>();
		for (String key : this.listasDeCompras.keySet()) {
			ListaDeCompras l = this.listasDeCompras.get(key);
			if (l.verificarItemEmLista(this.controllerItem.getItemPeloId(id))) {
				lista.add(l);
			}
		}
		Collections.sort(lista, new OrdenaListaDeComprasPorData());
		Collections.sort(lista, new OrdenarListaDeComprasPorDescritor());
		return lista.get(posicao).toString();
	}

	/**
	 * Metodo que faz a pesquisa de lista de compras pelo item cadastrado na lista
	 * de compras.
	 * 
	 * @param id
	 *            Inteiro referente ao id do item cadastrado na lista de compras.
	 * @return String referente a representacao textual da lista de compra.
	 */
	public String pesquisaListasDeComprasPorItem(int id) {
		if (!this.controllerItem.itemExiste(id)) {
			throw new ItemNaoExisteException("Erro na pesquisa de compra: compra nao encontrada na lista.");
		}
		List<ListaDeCompras> lista = new ArrayList<>(listasDeCompras.values());
		for (ListaDeCompras listaDeCompras : lista) {
			if (listaDeCompras.verificarItemEmLista(this.controllerItem.getItemPeloId(id))) {
				return listaDeCompras.toString();
			}
		}
		return "";
	}

	/**
	 * Metodo que faz a pesquisa nas listas de compras pela data.
	 * 
	 * @param data
	 *            String referente a data de criacao do item(dd/mm/aaaa).
	 * @return String referente a representacao textual da lista de compras.
	 */
	public String pesquisaListasDeComprasPorData(String data) {
		ValidadorListaDeCompras.validaData(data);

		List<ListaDeCompras> lista = new ArrayList<>(listasDeCompras.values());
		for (ListaDeCompras listaDeCompras : lista) {
			if (listaDeCompras.getData().equals(data)) {
				return listaDeCompras.toString();
			}
		}
		return "";
	}

	/**
	 * Metodo que gera uma lista de compra automatico com os itens da ultima lista
	 * cadastrada no sistema.
	 * 
	 * @return String referente ao descritor da lista automatica.
	 */
	public String geraAutomaticaUltimaLista() {
		int indice = 1;

		for (ListaDeCompras key : this.listasDeCompras.values()) {
			if (indice == this.listasDeCompras.size()) {
				ListaDeCompras l = new ListaDeCompras("Lista automatica 1 " + key.getData());
				this.copiaLista(key, l);
				return "Lista automatica 1 " + this.dataAtual();
			}
			indice++;
		}
		return "";
	}

	/**
	 * Metodo que gera uma lista de compra automatica copiando os itens da ultima
	 * lista que possui o item desejado.
	 * 
	 * @param nomeItem
	 *            String que representa o nome do item cadastrado.
	 * @return String referente ao descritor da lista automatica.
	 */
	public String geraAutomaticaItem(String nomeItem) {
		Item item = this.controllerItem.getItemPeloNome(nomeItem);

		String keyFinal = "";
		for (String key : this.listasDeCompras.keySet()) {
			if (this.listasDeCompras.get(key).verificarItemEmLista(item)) {
				keyFinal = key;
			}
		}
		if ("".equals(keyFinal)) {
			throw new IllegalArgumentException(
					"Erro na geracao de lista automatica por item: nao ha compras cadastradas com o item desejado.");
		}
		ListaDeCompras l = new ListaDeCompras("Lista automatica 2 " + this.dataAtual());
		this.copiaLista(this.listasDeCompras.get(keyFinal), l);
		return "Lista automatica 2 " + l.getData();
	}

	/**
	 * Metodo que gera uma lista de compra automatica copiando os itens que se
	 * repetem em mais da metade das listas de compras cadastradas.
	 * 
	 * @return String referente ao descritor da lista automatica.
	 */
	public String geraAutomaticaItensMaisPresentes() {
		String descritor = "Lista automatica 3 " + this.dataAtual();
		ListaDeCompras lista = new ListaDeCompras(descritor);
		for (Item i : this.controllerItem.getItens()) {
			if (this.verificaItemDeLista(i) > 0) {
				lista.adicionaCompraALista(this.verificaItemDeLista(i), i);
			}
		}
		this.listasDeCompras.put(descritor, lista);
		return descritor;
	}

	/**
	 * Metodo responsavel por sugerir o melhor estabelecimento para realizar a
	 * compra de uma determinada lista de compra.
	 * 
	 * @param descritorLista
	 *            String referente ao descritor da lista que desejar saber qual o
	 *            melhor lugar para realizar a compra.
	 * @param posicaoEstabelecimento
	 *            Inteiro que representa qual estabelimento desejo visualizar(Por
	 *            ordem crescente dos precos)
	 * @param posicaoLista
	 *            Inteiro que representa a posicao do item q desejo visualizar do
	 *            meu estabelecimento.
	 * @return String referente a representacao textual do meu estabelecimento ou
	 *         itens.
	 */
	public String sugereMelhorEstabelecimento(String descritorLista, int posicaoEstabelecimento, int posicaoLista) {
		Map<String, Supermercado> supermercados = new LinkedHashMap<>();
		this.criarSupermercados(supermercados, descritorLista);
		List<Supermercado> s = new ArrayList<>(supermercados.values());
		Collections.sort(s, new OrdenarSupermercadoPeloValor());
		if (posicaoLista == 0) {
			return s.get(posicaoEstabelecimento).toString();
		} else {
			return s.get(posicaoEstabelecimento).getItem(posicaoLista);
		}
	}

	/**
	 * Metodo responsavel por realizar o salvamento dos dados em um arquivo.
	 */
	public void salvarDados() {
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream("listas.txt"));
			os.writeObject(this.listasDeCompras);
		} catch (FileNotFoundException e) {
			throw new ArquivoNaoExiste("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Alguma coisa deu errado...");
		}
	}
	
	/**
	 * Metodo responsavel por realizar o carregamento dos dados.
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public void carregarDados() {
		ObjectInputStream os;
		try {
			os = new ObjectInputStream(new FileInputStream("listas.txt"));
			this.listasDeCompras = (Map<String, ListaDeCompras>) os.readObject();
		} catch (FileNotFoundException e) {
			throw new ArquivoNaoExiste("Arquivo nao existe no sistema.");
		} catch (IOException e) {
			System.out.println("Algum erro ocorre...");
		} catch (ClassNotFoundException e) {
			throw new ArquivoNaoExiste("Alguma coisa no sistema mudou");
		}	
	}
	
	/**
	 * Metodo privado que cria uma colecao de todos os supermercados.
	 * 
	 * @param supermercados
	 *            Map<String, Supermercado> String: referente ao nome do
	 *            supermercado, Supermercado: referente ao supermercado criado.
	 * @param descritorLista
	 *            String referente ao descritor da lista de compra a ser analisada.
	 */
	private void criarSupermercados(Map<String, Supermercado> supermercados, String descritorLista) {
		ListaDeCompras l = this.listasDeCompras.get(descritorLista);
		for (Compra c : l.getCompras().values()) {
			for (String nomeSupermercado : c.getItem().getMapaPrecos().keySet()) {
				if (!supermercados.containsKey(nomeSupermercado)) {
					supermercados.put(nomeSupermercado, new Supermercado(nomeSupermercado));
				}
				supermercados.get(nomeSupermercado).addItem(c);
			}
		}
		if (supermercados.size() == 0) {
			throw new IllegalArgumentException("Faltam dados para informar sobre preços em locais de compras.");
		}
	}

	/**
	 * Metodo privado que verifica se um item esta em uma lista e se sua aparicao eh
	 * mais que na metade das listas de compras e a sua media de quantidade nas
	 * listas que o item aparece.
	 * 
	 * @param item
	 *            Item referente ao item a ser analisado.
	 * @return Inteiro referente a quantidade media das aparicao do item nas listas
	 *         de compras.
	 */
	private int verificaItemDeLista(Item item) {
		int contAparece = 0;
		int contQuant = 0;
		for (ListaDeCompras l : this.listasDeCompras.values()) {
			if (l.getCompras().containsKey(item)) {
				contQuant += l.getCompras().get(item).getQntd();
				contAparece++;
			}
		}
		if (contAparece >= this.listasDeCompras.size() / 2) {
			return (int) Math.floor(contQuant / contAparece);
		} else {
			return -1;
		}
	}

	/**
	 * Metodo privado que copia os itens de uma lista para a outra.
	 * 
	 * @param lista
	 *            ListaDeCompras referente a lista que tem os itens que deveram ser
	 *            copiados.
	 * @param novaLista
	 *            ListaDeCompras referente a lista que recebera esses itens.
	 */
	private void copiaLista(ListaDeCompras lista, ListaDeCompras novaLista) {
		lista.copiaLista(novaLista);
		this.listasDeCompras.put(novaLista.getDescritor(), novaLista);
	}

	/**
	 * Metodo que retorna a representacao textual da data("dd/MM/yyyy").
	 * 
	 * @return String referente a representacao textual da data("dd/MM/yyyy").
	 */
	private String dataAtual() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = LocalDate.now().format(formato);
		return data;
	}
}