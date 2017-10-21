package com.company;

import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;

public class ProblemaFlusso2 {

    public static void main(String[] args) {
        ArrayList<int[]> grafo = new ArrayList<>();
        grafo.add(new int[]{1, 2, 6});
        grafo.add(new int[]{1, 3, 1});
        grafo.add(new int[]{2, 5, 4});
        grafo.add(new int[]{3, 4, 2});
        grafo.add(new int[]{3, 2, 3});
        grafo.add(new int[]{4, 1, 4});
        grafo.add(new int[]{4, 2, 1});
        grafo.add(new int[]{4, 5, 3});

        int[] nodi = new int[]{1, 0, 0, 0, -1};

        double min = flussoCostoMinimo(grafo, nodi);
    }


    public static double flussoCostoMinimo(ArrayList<int[]> grafo, int[] nodi) {

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
            for (int i = 0; i < nodi.length; i++) {
                IloLinearNumExpr v = model.linearNumExpr();

                for (int j = 0; j < grafo.size(); j++) {
                    if(grafo.get(j)[0] == i+1){
                        v.addTerm(variabili[j], 1);
                    }
                    if(grafo.get(j)[1] == i+1){
                        v.addTerm(variabili[j], -1);
                    }
                }

                model.addEq(v, nodi[i], "v");
            }
            model.exportModel("problemaFlusso2.lp");

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

