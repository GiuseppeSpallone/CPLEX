package com.company.Grafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Controller {

    public HashMap<Integer, Nodo> creaNodi() {
        System.out.println("Numero nodi: ");
        Scanner input = new Scanner(System.in);
        int numNodi = Integer.parseInt(input.nextLine());

        HashMap<Integer, Nodo> nodi = new HashMap<>();

        for (int i = 0; i < numNodi; i++) {
            System.out.println("NODO " + (i + 1));

            System.out.println("    Valore nodo " + (i + 1) + ": ");
            Scanner input2 = new Scanner(System.in);
            int valoreNodo = Integer.parseInt(input2.nextLine());

            Nodo nodo = new Nodo((i + 1), valoreNodo);
            nodi.put(nodo.getId(), nodo);
        }

        return nodi;
    }

    public HashSet<Arco> creaArchi(HashMap<Integer, Nodo> nodi) {
        System.out.println("Numero archi: ");
        Scanner input3 = new Scanner(System.in);
        int numArchi = Integer.parseInt(input3.nextLine());

        HashSet<Arco> archi = new HashSet<>();

        for (int i = 0; i < numArchi; i++) {
            System.out.println("ARCO " + (i + 1));

            System.out.println("    Nodo from: ");
            Scanner input4 = new Scanner(System.in);
            int nodoFrom = Integer.parseInt(input4.nextLine());
            Nodo from = nodi.get(nodoFrom);

            System.out.println("    Nodo to: ");
            Scanner input5 = new Scanner(System.in);
            int nodoTo = Integer.parseInt(input5.nextLine());
            Nodo to = nodi.get(nodoTo);

            System.out.println("    Nodo from: ");
            Scanner input6 = new Scanner(System.in);
            int costo = Integer.parseInt(input6.nextLine());

            Arco arco = new Arco(from, to, costo);
            archi.add(arco);
        }

        return archi;
    }

    public HashMap<Integer, Nodo> creaNodiRandom(int randomNumNodi) {
        HashMap<Integer, Nodo> nodi = new HashMap<>();

        for (int i = 0; i < randomNumNodi; i++) {
            int valore = 0;

            if (i != randomNumNodi - 1) {
                valore = randomNum(0, 25);
            } else {
                int somma = 0;

                for (Iterator<Nodo> it = nodi.values().iterator(); it.hasNext(); ) {
                    Nodo nodo = it.next();
                    somma += nodo.getValore();
                }

                valore = (-1) * somma;
            }

            Nodo nodo = new Nodo(i + 1, valore);
            nodi.put(nodo.getId(), nodo);
        }

        System.out.print(printNodi(nodi));

        return nodi;
    }

    public HashSet<Arco> creaArchi(int randomNumArchi, HashMap<Integer, Nodo> nodi) {
        HashSet<Arco> archi = new HashSet<>();

        for (int i = 0; i < randomNumArchi; i++) {
            Nodo from = null;
            Nodo to = null;
            int costo = 0;

            do {
                from = randomNodo(nodi);
                to = randomNodo(nodi);

            } while (from == to || isArco(archi, from, to));

            costo = randomNum(0, 25);

            Arco arco = new Arco(from, to, costo);
            archi.add(arco);

            //System.out.println("from: " + from.getId() + " to: " + to.getId());

        }
        System.out.print(printArchi(archi));
        return archi;
    }

    public int randomNum(int min, int max) {
        int numRandom = min + (int) (Math.random() * max);

        return numRandom;
    }

    private Nodo randomNodo(HashMap<Integer, Nodo> nodi) {
        Nodo randomNodo = null;
        int randomNum = randomNum(0, nodi.size());

        int i = 0;
        for (Iterator<Nodo> it = nodi.values().iterator(); it.hasNext(); ) {
            Nodo nodo = it.next();

            if (i == randomNum) {
                randomNodo = nodo;
            }
            i++;
        }

        return randomNodo;
    }

    private boolean isArco(HashSet<Arco> archi, Nodo from, Nodo to) {
        boolean isArco = false;
        for (Iterator<Arco> it = archi.iterator(); it.hasNext(); ) {
            Arco arco = it.next();

            if (arco.getNodo_from() == from && arco.getNodo_to() == to) {
                isArco = true;
            }
        }
        return isArco;
    }

    private static String printNodi(HashMap<Integer, Nodo> nodi) {
        String output_nodi = "";
        for (Iterator<Nodo> it = nodi.values().iterator(); it.hasNext(); ) {
            Nodo nodo = it.next();
            output_nodi += "nodo id: " + nodo.getId() + " valore: " + nodo.getValore() + "\n";
        }

        return output_nodi;
    }

    private static String printArchi(HashSet<Arco> archi) {
        String output_archi = "";
        for (Iterator<Arco> it = archi.iterator(); it.hasNext(); ) {
            Arco arco = it.next();
            output_archi += "arco from: " + arco.getNodo_from().getId() + " to: " + arco.getNodo_to().getId() + " costo: " + arco.getCosto() + "\n";
        }

        return output_archi;
    }

}
