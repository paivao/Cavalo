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
public class Utils {
    
    private static final int modulo = 8;
    
    static int toIndice(Ponto p) {
	return modulo*p.getY() + p.getX();
    }
    
    static Ponto toPonto(int indice) {
	return new Ponto(indice%modulo, indice/modulo);
    }
    
    static void popularGrafo(Grafo g) {
	for (int i = 0; i < 64; i++) {
	    List<Ponto> viz = pontosVizinhos(i);
	    for (Ponto p: viz) {
		g.adicionarAresta(i, toIndice(p));
	    }
	}
    }
    
    static private List<Ponto> pontosVizinhos(int indice) {
	List<Ponto> vizinhos = new ArrayList<>(8);
	Ponto pos = toPonto(indice);
	for (int i = -1; i < 2; i+=2) {
	    for (int j = -1; j < 2; j+=2) {
		for (int k = 1; k < 3; k++) {
		    Ponto viz = new Ponto(pos.getX()+i*k, pos.getY()+j*(3-k));
		    if (ePontoValido(viz)) vizinhos.add(viz);
		}
	    }
	}
	return vizinhos;
    }
    
    static private boolean ePontoValido(Ponto p) {
	if (p.getX() < 0 || p.getX() >= modulo)
	    return false;
	if (p.getY() < 0 || p.getY() >= modulo)
	    return false;
	return true;
    }
}
