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

    public Grafo(int linha) {
	nos = new ArrayList<>(linha*linha);
	for (int i = 0; i < (linha*linha); i++) {
	    nos.add(new ArrayList<Integer>());
	}
	Utils.setModulo(linha);
    }

    public void adicionarAresta(int vertice1, int vertice2) {
	if (verticeValido(vertice1) && verticeValido(vertice2))  {

	    if (this.estaConectado(vertice1, vertice2)) {
		return;
	    }
	    nos.get(vertice1).add(vertice2);
	}
    }

    public List<Integer> vizinhos(int vertice) {
	return nos.get(vertice);
    }

    public boolean estaConectado(int vertice1, int vertice2) {
	if (!(verticeValido(vertice1) && verticeValido(vertice2))) {
	    return false;
	}
	return nos.get(vertice1).contains(vertice2);
    }

    private boolean verticeValido(int vertice) {
	return ((vertice >= 0) && (vertice < this.tamanho()));
    }

    public int tamanho() {
	return nos.size();
    }

}
