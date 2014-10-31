/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavalo;

import java.util.List;

/**
 *
 * @author rafaelpaiva
 */
public class Cavalo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Grafo grafo = new Grafo(64);
	
	Utils.popularGrafo(grafo);
	
	for (int i = 0; i < 64; i++) {
	    System.out.print(Utils.toPonto(i)+" => ");
	    List<Integer> viz = grafo.vizinhos(i);
	    for (Integer j: viz) {
		System.out.print(Utils.toPonto(j) + " ");
	    }
	    System.out.println("");
	}
    }
    
}
