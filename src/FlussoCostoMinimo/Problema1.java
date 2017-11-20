package FlussoCostoMinimo;

import java.util.ArrayList;

public class Problema1 {

    public static void main(String[] args) {
        Modello modello = new Modello();

        ArrayList<int[]> grafo = new ArrayList<>();
        grafo.add(new int[]{1, 2, 6});
        grafo.add(new int[]{1, 3, 1});
        grafo.add(new int[]{2, 5, 4});
        grafo.add(new int[]{3, 4, 2});
        grafo.add(new int[]{3, 2, 3});
        grafo.add(new int[]{4, 1, 4});
        grafo.add(new int[]{4, 2, 1});
        grafo.add(new int[]{4, 5, 3});
        int[] nodi = new int[]{5, -4, 0, 2, -3};

        modello.run(grafo, nodi);
    }

}

