/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavalo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rafaelpaiva
 */
public class Utils {

    private static int modulo;

    /**
     * Pegar letra do alfabeto.
     *
     * @param n indíce da letra
     * @return a letra
     */
    public static String getLetter(int n) {
        n--;
        if (n < 0) {
            return "";
        }
        final String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getLetter(n / 26) + alfabeto.charAt(n % 26);
    }

    /**
     * Setar o módulo.
     *
     * @param modulo o valor
     */
    static void setModulo(int modulo) {
        Utils.modulo = modulo;
    }

    /**
     * Mudar da posição para o índice
     *
     * @param p a posição
     * @return
     */
    static int toIndice(Posicao p) {
        return modulo * p.getY() + p.getX();
    }

    /**
     * Mudat do índice para a posição.
     *
     * @param indice
     * @return
     */
    static Posicao toPosicao(int indice) {
        return new Posicao(indice % modulo, indice / modulo);
    }

    /**
     * Popular um grafo com seus vizinhos.
     *
     * @param g o grafo a ser populado
     */
    public static void popularGrafo(Grafo g) {
        for (int i = 0; i < g.tamanho(); i++) {
            List<Posicao> viz = possiveisVizinhos(i);
            for (Posicao p : viz) {
                g.adicionarAresta(i, toIndice(p));
            }
        }
    }

    /**
     * Pega os vizinhos de uma posição.
     *
     * @param indice o índice da posição
     * @return
     */
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

    /**
     * Checar se uma posição é válida.
     *
     * @param p a posição
     * @return true se sim, false se não
     */
    static private boolean ePosicaoValido(Posicao p) {
        if (p.getX() < 0 || p.getX() >= modulo) {
            return false;
        }
        if (p.getY() < 0 || p.getY() >= modulo) {
            return false;
        }
        return true;
    }

    /*static private boolean isEligible(Grafo g, List<Integer> caminho, int vertice, int pos) {
     if (!g.estaConectado(vertice, caminho.get(pos - 1))) {
     return false;
     }

     return !caminho.contains(vertice);
     }*/
    /**
     * Checar se é um caminho hamiltoniano.
     *
     * @param g o grafo
     * @param caminho a lista de índices do caminho
     * @param pos a posição inicial
     * @param cycle se deseja verificar um ciclo
     * @return true se é o caminho, false se não
     */
    static private boolean hamiltonianUtil(Grafo g, List<Integer> caminho, int pos, boolean cycle) {
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

    //@Deprecated
    /**
     * Montar uma lista de posições de um caminho Hamiltoniano.
     *
     * @param g o grafo
     * @param inicio a posição inicial
     * @param cycle se deseja um ciclo.
     * @return a lista com as posições
     */
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
    /*
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
     */

    /**
     * Ordena um grafo de a cordo com a quantidade de vizinhos.
     *
     * @param g grafo a ser ordenado
     */
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

    /**
     * Acha um caminho de um grafo.
     *
     * @param g o grafo
     * @param caminho lista dos índices do caminho
     * @param proibidos lista com os índices de posições proibidas
     */
    private static void findAPath(final Grafo g, List<Integer> caminho, List<Integer> proibidos) {
        Integer proximo = null;
        int minimo = g.tamanho(), unvisited;
        for (Integer w : g.vizinhos(caminho.get(caminho.size() - 1))) {
            if (caminho.contains(w)) {
                continue;
            }
            if (proibidos != null) {
                if (proibidos.contains(w)) {
                    continue;
                }
            }

            unvisited = 0;
            for (Integer i : g.vizinhos(w)) {
                if (!caminho.contains(i)) {
                    unvisited++;
                }
            }

            if (unvisited < minimo) {
                minimo = unvisited;
                proximo = w;
            }
        }
        if (proximo != null) {
            caminho.add(proximo);
            findAPath(g, caminho, proibidos);
        }
    }

    /**
     * Ampliar um caminho de um grafo.
     *
     * @param g o grafo
     * @param caminho o caminho a ser ampliado
     */
    private static void extendPath(Grafo g, List<Integer> caminho) {
        Integer next = null;
        for (Integer v : caminho) {
            if (g.estaConectado(v, caminho.get(caminho.size() - 1))) { //Vi
                int index = caminho.indexOf(v); //Vi+1
                int unvisited, maxEta = 0;
                for (Integer w : g.vizinhos(caminho.get(index + 1))) { //Checando se Vi+1 tem vizinhos não visitados
                    if (caminho.contains(w)) {
                        continue;
                    }
                    unvisited = 0;
                    for (Integer m : g.vizinhos(w)) {    //eta de w
                        if (!caminho.contains(m)) {
                            unvisited++;
                        }
                    }
                    if (unvisited > maxEta) {
                        next = w;
                        maxEta = unvisited;
                    }
                }
                if (next != null) {
                    //Reorganizar o caminho
                    Collections.reverse(caminho.subList(index + 1, caminho.size()));
                    caminho.add(next);
                    findAPath(g, caminho, null);
                    extendPath(g, caminho);
                    break;
                }
            }
        }
    }

    /**
     * Ampliar mais o caminho de um grafo.
     *
     * @param g o grafo
     * @param caminho o caminho a ser ampliado
     * @param tenteOutraVez se é para tentar mais de uma vez
     */
    private static void extendFutherPath(Grafo g, List<Integer> caminho, boolean tenteOutraVez) {
        List<Integer> caminho_extra = null;
        Integer inicial = null;
        encontrar_inicial:
        for (Integer v : caminho.subList(0, caminho.size() - 2)) {
            for (Integer w : g.vizinhos(v)) {
                if (!caminho.contains(w)) {
                    inicial = w;
                    break encontrar_inicial;
                }
            }
        }
        if (inicial != null) {
            caminho_extra.add(inicial);
            findAPath(g, caminho_extra, caminho);
            if (caminho_extra.size() > 1) {
                Integer conexao = null;
                trim:
                while (caminho_extra.size() > 1) {
                    for (Integer j : g.vizinhos(caminho_extra.get(caminho.size() - 1))) {
                        if (caminho.subList(inicial + 1, caminho.size()).contains(j)) {
                            if (g.estaConectado(inicial + 1, j + 1)) {
                                conexao = j;
                                break trim;
                            }
                        }
                    }
                    if (conexao == null) {
                        caminho_extra.remove(caminho_extra.size() - 1);
                    }
                }
                if (conexao != null) {
                    Collections.reverse(caminho.subList(inicial + 1, conexao));
                    caminho.addAll(inicial + 1, caminho_extra);
                    extendFutherPath(g, caminho, true);
                }
            }
        }
        if (inicial == null && tenteOutraVez) {
            Collections.reverse(caminho);
            extendFutherPath(g, caminho, false);
        }
    }

    /**
     * Monta um caminho ou um ciclo Hamiltoniano.
     * @param g o grafo a ser usado.
     * @param cycle se deseja que seja um ciclo
     * @return a lista com as posições do caminho ou ciclo
     */
    public static List<Posicao> hamiltonianPathOrCycle(final Grafo g, boolean cycle) {
        List<Integer> caminho = new LinkedList<>();
        if (!cycle) {
            caminho.add(toIndice(new Posicao(g.linha() / 2, g.linha() / 2)));
            findAPath(g, caminho, null);
            extendPath(g, caminho);
            extendFutherPath(g, caminho, false);
        }

        //Se quer ciclo
        if (cycle) {
            List<Integer> nos = new ArrayList<>();
            for (int i = 0; i < g.tamanho(); i++) {
                nos.add(i);
            }

            Collections.sort(nos, new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {
                    return g.vizinhos(o2).size() - g.vizinhos(o1).size();
                }

            });

            System.out.println(nos.get(0));
            System.out.println(nos.get(nos.size() - 1));
            ciclo:
            for (Integer no : nos) {
                caminho.clear();

                caminho.add(no);
                findAPath(g, caminho, null);
                extendPath(g, caminho);
                extendFutherPath(g, caminho, false);

                for (int i = 0; i < g.tamanho(); i++) {
                    if (g.estaConectado(0, i + 1) && g.estaConectado(i, g.tamanho() - 1)) {
                        cycle = false;
                        Collections.reverse(caminho.subList(i + 1, g.tamanho()));
                        break ciclo;
                    }
                }
                //caminho.clear();
            }
        }

        System.out.println(caminho.get(0));

        List<Posicao> caminho_pronto = new LinkedList<>();
        for (Integer i : caminho) {
            caminho_pronto.add(toPosicao(i));
        }

        if (cycle) {
            System.out.println("Não acho ciclo");
        }
        return caminho_pronto;
    }

    /**
     * Desloca as posições de um ciclo.
     * @param ciclo o ciclo a ser deslocado
     * @param inicio a posição inicial desejada
     */
    public static void deslocarCiclo(List<Posicao> ciclo, Posicao inicio) {
        List<Posicao> deslocar = new LinkedList<>();

        while (true) {
            Posicao p = ciclo.get(0);
            if (p.getX() != inicio.getX() || p.getY() != inicio.getY()) {
                deslocar.add(ciclo.remove(0));
            } else {
                break;
            }
        }
        ciclo.addAll(deslocar);
    }
}
