/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavalo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafaelpaiva
 */
public class Grafo {

    private final List<List<Integer>> nos;
    private final int linha;

    /**
     * Construtor do grafo.
     * @param linha quantidade de linhas
     */
    public Grafo(int linha) {
	nos = new ArrayList<>(linha*linha);
	this.linha = linha;
	for (int i = 0; i < (linha*linha); i++) {
	    nos.add(new ArrayList<Integer>());
	}
	Utils.setModulo(linha);
    }

    /**
     * Adiciona uma aresta ao grafo.
     * @param vertice1 índice do vértice 1
     * @param vertice2 índice do vértice 2
     */
    public void adicionarAresta(int vertice1, int vertice2) {
	if (verticeValido(vertice1) && verticeValido(vertice2))  {

	    if (this.estaConectado(vertice1, vertice2)) {
		return;
	    }
	    nos.get(vertice1).add(vertice2);
	}
    }

    /**
     * A lista com as posições dos vizinhos de um vértice desejado.
     * @param vertice índice do vertice desejado
     * @return a lista com os vizinhos
     */
    public List<Integer> vizinhos(int vertice) {
	return nos.get(vertice);
    }

    /**
     * Verificar se dois vértices estão conectados.
     * @param vertice1 índice do primeiro vértice
     * @param vertice2 índice do segundo vértice
     * @return true se sim, false se não
     */
    public boolean estaConectado(int vertice1, int vertice2) {
	if (!(verticeValido(vertice1) && verticeValido(vertice2))) {
	    return false;
	}
	return nos.get(vertice1).contains(vertice2);
    }

    /**
     * Verifica se um vértice pertence ao grafo.
     * @param vertice índice do vértice
     * @return true se pertence, false se não
     */
    private boolean verticeValido(int vertice) {
	return ((vertice >= 0) && (vertice < this.tamanho()));
    }

    /**
     * O tamanho do grafo.
     * @return a quantidade de nós
     */
    public int tamanho() {
	return nos.size();
    }
    
    /**
     * A quantidade de linhas.
     * @return quantidade de linhas
     */
    public int linha() {
	return linha;
    }
}
