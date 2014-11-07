/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavalo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author rafaelpaiva
 */
public class Utils {

    private static int modulo;
    
    public static String getLetter (int n) {
	n--;
	if (n < 0) return "";
	final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	return getLetter(n/26)+alfabeto.charAt(n%26);
    }
    
    static void setModulo(int modulo) {
	Utils.modulo = modulo;
    }

    static int toIndice(Posicao p) {
	return modulo * p.getY() + p.getX();
    }

    static Posicao toPosicao(int indice) {
	return new Posicao(indice % modulo, indice / modulo);
    }

    public static void popularGrafo(Grafo g) {
	for (int i = 0; i < g.tamanho(); i++) {
	    List<Posicao> viz = possiveisVizinhos(i);
	    for (Posicao p : viz) {
		g.adicionarAresta(i, toIndice(p));
	    }
	}
    }

    static private List<Posicao> possiveisVizinhos(int indice) {
	List<Posicao> vizinhos = new ArrayList<>(8);
	Posicao pos = toPosicao(indice);
	for (int i = -1; i < 2; i += 2) {
	    for (int j = -1; j < 2; j += 2) {
		for (int k = 1; k < 3; k++) {
		    Posicao viz = new Posicao(pos.getX() + i * k, pos.getY() + j * (3 - k));
		    if (ePosicaoValido(viz)) {
			vizinhos.add(viz);
		    }
		}
	    }
	}
	return vizinhos;
    }

    static private boolean ePosicaoValido(Posicao p) {
	if (p.getX() < 0 || p.getX() >= modulo) {
	    return false;
	}
	if (p.getY() < 0 || p.getY() >= modulo) {
	    return false;
	}
	return true;
    }

    static private boolean isEligible(Grafo g, List<Integer> caminho, int vertice, int pos) {
	if (!g.estaConectado(vertice, caminho.get(pos - 1))) {
	    return false;
	}

	return !caminho.contains(vertice);
    }

    static private boolean hamiltonianUtil(Grafo g, List<Integer> caminho, int pos, boolean cycle) {
	//System.out.println("AAA");
	if (pos == g.tamanho()) {
	    //System.out.println(Utils.toPosicao(caminho.get(pos-1)) + "A");
	    if (!cycle) {
		return true;
	    }
	    return (g.estaConectado(caminho.get(pos - 1), caminho.get(0)));
	}

	for (int v : g.vizinhos(caminho.get(pos - 1))) {
	    if (caminho.contains(v)) {
		continue;
	    }
	    caminho.set(pos, v);

		//System.out.println(pos);
	    if (hamiltonianUtil(g, caminho, pos + 1, cycle)) {
		//System.out.println(Utils.toPosicao(caminho.get(pos-1)) + "B");
		return true;
	    }
	    caminho.set(pos, -1);
	}
	caminho.set(pos, -1);
	return false;
    }

    public static List<Posicao> hamiltonianPath(Grafo g, Posicao inicio, boolean cycle) {
	//Utils.ordenarGrafo(g);
	List<Integer> caminho = new ArrayList<>();
	caminho.addAll(Collections.nCopies(g.tamanho(), -1));

	caminho.set(0, Utils.toIndice(inicio));

	if (!hamiltonianUtil(g, caminho, 1, cycle)) {
	    System.out.println("Caminho não existe");
	    return null;
	}

	List<Posicao> caminhoPronto = new ArrayList<>();
	for (Integer i : caminho) {
	    caminhoPronto.add(Utils.toPosicao(i));
	}

	return caminhoPronto;
    }

    @SuppressWarnings("empty-statement")
    @Deprecated
    static public synchronized List<Integer> cicloEuleriano(Grafo g, Integer inicial) {
	List<Integer> ciclo = new ArrayList<>();
	List<Integer> vizinhos;
	int k, pos = inicial, p2;
	ciclo.add(pos);
	boolean[][] arestas = new boolean[64][8];
	while (true) {
	    System.out.println(toPosicao(pos));
	    vizinhos = g.vizinhos(pos);
	    for (k = 0; k < vizinhos.size(); k++) {
		if (!arestas[pos][k]) {
		    break;
		}
	    }
	    if (k == vizinhos.size()) {
ciclo:
		for (p2 = 0; p2 < ciclo.size(); p2++) {
		    pos = ciclo.get(p2);
		    vizinhos = g.vizinhos(pos);
		    for (k = 0; k < vizinhos.size(); k++) {
			if (!arestas[pos][k]) {
			    break ciclo;
			}
		    }
		}
		if (pos == ciclo.size()) {
		    break;
		}
	    }
	    arestas[pos][k] = true;
	    pos = g.vizinhos(pos).get(k);
	    ciclo.add(pos);
	}
	return ciclo;
    }

    public static void ordenarGrafo(final Grafo g) {
	for (int i = 0; i < g.tamanho(); i++) {
	    Collections.sort(g.vizinhos(i), new Comparator<Integer>() {
		@Override
		public int compare(Integer i1, Integer i2) {
		    return g.vizinhos(i1).size() - g.vizinhos(i2).size();
		}
	    }
	    );
	}
    }
}
