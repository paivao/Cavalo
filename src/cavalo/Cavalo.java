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
	Grafo grafo = new Grafo(36);
	Utils.setModulo(6);

	Utils.popularGrafo(grafo);

	/*for (int i = 0; i < grafo.tamanho(); i++) {
	    System.out.print(Utils.toPosicao(i) + " => ");
	    List<Integer> viz = grafo.vizinhos(i);
	    for (Integer j : viz) {
		System.out.print(Utils.toPosicao(j) + " ");
	    }
	    System.out.println("");
	}*/
	/*
	 List<Integer> ciclo = Utils.cicloEuleriano(grafo, 0);
	
	 for (Integer v: ciclo) {
	 System.out.println(Utils.toPosicao(v));
	 }*/
	System.out.println("Grafo de tamanho: " + grafo.tamanho());

	List<Posicao> caminho = Utils.hamiltonianPath(grafo, new Posicao(2, 2), true);
	if (caminho != null) {
	    for (Posicao p : caminho) {
		System.out.print(p + " - ");
	    }
	}
    }

}
