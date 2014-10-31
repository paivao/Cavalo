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
	    nos.set(i, new ArrayList<Integer>());
	}
    }
    
    public void AdicionarVertice(int vertice1, int vertice2) {
	if (vertice1 >= nos.size() || vertice2 >= nos.size())
	    return;
	if (this.estaConectado(vertice1, vertice2))
	    return;
	nos.get(vertice1).add(vertice2);
	nos.get(vertice2).add(vertice1);
    }
    
    public boolean estaConectado(int vertice1, int vertice2) {
	if (nos.get(vertice2).contains(vertice1) && nos.get(vertice1).contains(vertice2))
	    return true;
	return false;
    }
}
