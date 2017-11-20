package MinVertexCover;

import Grafo.Arco;
import Grafo.Nodo;

import java.util.ArrayList;

public class Problema1 {

    public static void main(String[] args) {
        Modello modello = new Modello();

        Nodo n1 = new Nodo(1);
        Nodo n2 = new Nodo(2);
        Nodo n3 = new Nodo(3);
        Nodo n4 = new Nodo(4);
        Nodo n5 = new Nodo(5);
        Nodo n6 = new Nodo(6);

        Arco n1n2 = new Arco(n1, n2);
        Arco n2n3 = new Arco(n2, n3);
        Arco n2n4 = new Arco(n2, n4);
        Arco n3n5 = new Arco(n3, n5);
        Arco n4n5 = new Arco(n4, n5);
        Arco n5n6 = new Arco(n5, n6);

        n1.archi.add(n1n2);
        n2.archi.add(n1n2);
        n2.archi.add(n2n4);
        n3.archi.add(n2n3);
        n3.archi.add(n3n5);
        n4.archi.add(n2n4);
        n4.archi.add(n4n5);

        ArrayList<Nodo> nodi = new ArrayList<>();
        nodi.add(n1);
        nodi.add(n2);
        nodi.add(n3);
        nodi.add(n4);
        nodi.add(n5);
        nodi.add(n6);

        ArrayList<Arco> archi = new ArrayList<>();
        archi.add(n1n2);
        archi.add(n2n3);
        archi.add(n2n4);
        archi.add(n3n5);
        archi.add(n4n5);
        archi.add(n5n6);

        modello.run(nodi, archi);

    }
}
