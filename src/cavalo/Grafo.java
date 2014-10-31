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
    
    public Grafo (int nronos) {
	nos = new ArrayList<>(nronos);
	for(int i = 0; i < nronos; i++) {
	    nos.add(new ArrayList<Integer>());
	}
    }
    
    public void adicionarAresta(int vertice1, int vertice2) {
	if (vertice1 < 0 || vertice1 >= 64)
	    return;
	if (vertice2 < 0 || vertice2 >= 64)
	    return;
	if (this.estaConectado(vertice1, vertice2))
	    return;
	nos.get(vertice1).add(vertice2);
    }
    
    public List<Integer> vizinhos(int vertice) {
	return nos.get(vertice);
    }
    
    public boolean estaConectado(int vertice1, int vertice2) {
	return nos.get(vertice1).contains(vertice2);
    }
}
