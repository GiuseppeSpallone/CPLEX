package com.company;

import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.HashSet;

public class ProblemaFlusso3 {

    public static void main(String[] args) {
        int numNodi = 2 + (int) (Math.random() * 100);
        int numArchi = 1 + (int) (Math.random() * ((numNodi * (numNodi - 1)) / 2));
        int from = 0;
        int to = 0;
        int costo = 0;
        int from_grafo = 0;
        int to_grafo = 0;

        System.out.println("NUMERO NODI: " + numNodi);
        System.out.println("NUMERO ARCHI: " + numArchi);

        ArrayList<int[]> grafo = new ArrayList<>();
        for (int i = 0; i < numArchi; i++) {

            from = 1 + (int) (Math.random() * numNodi);
            to = 1 + (int) (Math.random() * numNodi);
            costo = 1 + (int) (Math.random() * 25);

            grafo.add(new int[]{from, to, costo});

            do {

                for (int j = 0; j < grafo.size(); j++) {
                    from_grafo = grafo.get(j)[0];
                    to_grafo = grafo.get(j)[1];

                }
            } while (from != from_grafo && to != to_grafo);


        }

        ArrayList<Integer> nodi = new ArrayList<>();
        for (int i = 0; i < numNodi; i++) {
            //controllo somma uguale a 0
            int valore = -25 + (int) (Math.random() * 25);
            nodi.add(valore);
        }

        for (int i = 0; i < grafo.size(); i++) {
            System.out.println("Arco num " + (i + 1) + ": " + grafo.get(i)[0] + " " + grafo.get(i)[1] + " " + grafo.get(i)[2]);
        }

        for (int i = 0; i < nodi.size(); i++) {
            System.out.println("Nodo num " + (i + 1) + ": " + nodi.get(i));
        }

        double min = flussoCostoMinimo(grafo, nodi);
    }

    public static double flussoCostoMinimo(ArrayList<int[]> grafo, ArrayList<Integer> nodi) {

        try {

            IloCplex model = new IloCplex();

            /**insieme di variabili**/
            IloNumVar[] variabili = model.numVarArray(grafo.size(), 0, Double.MAX_VALUE);


            /**funzione obiettivo**/
            IloLinearNumExpr function = model.linearNumExpr();

            for (int i = 0; i < variabili.length; i++) {
                function.addTerm(variabili[i], grafo.get(i)[2]);
            }
            model.addMinimize(function);


            /**vincoli**/
            for (int i = 0; i < nodi.size(); i++) {
                IloLinearNumExpr v = model.linearNumExpr();

                for (int j = 0; j < grafo.size(); j++) {
                    if (grafo.get(j)[0] == i + 1) {
                        v.addTerm(variabili[j], 1);
                    }
                    if (grafo.get(j)[1] == i + 1) {
                        v.addTerm(variabili[j], -1);
                    }
                }

                model.addEq(v, nodi.get(i), "v");
            }
            model.exportModel("problemaFlusso3.lp");

            /**soluzione**/
            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());
            } else {
                System.out.println("Solution status: " + model.getStatus());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}

