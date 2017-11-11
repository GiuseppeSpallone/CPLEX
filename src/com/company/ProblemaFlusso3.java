package com.company;

import com.company.Grafo.Arco;
import com.company.Grafo.Controller;
import com.company.Grafo.Nodo;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.*;

public class ProblemaFlusso3 {

    public static void main(String[] args) {
        Controller controller = new Controller();

       /** RANDOM **/
        int numNodi = controller.randomNum(2, 15);
        int max = ((numNodi * (numNodi - 1)) / 2);
        int numArchi = controller.randomNum(1, max);
        HashMap<Integer, Nodo> nodi = controller.creaNodiRandom(numNodi);
        HashSet<Arco> archi = controller.creaArchiRandom(numArchi, nodi);

        /** SCANNER **/
        /*HashMap<Integer, Nodo> nodi = controller.creaNodi();
        HashSet<Arco> archi = controller.creaArchi(nodi);*/

        /*HashMap<Integer, Nodo> nodi = new HashMap<>();
        Nodo nodo1 = new Nodo(1, 5);
        Nodo nodo2 = new Nodo(2, -4);
        Nodo nodo3 = new Nodo(3, 0);
        Nodo nodo4 = new Nodo(4, 2);
        Nodo nodo5 = new Nodo(5, -3);
        nodi.put(nodo1.getId(), nodo1);
        nodi.put(nodo2.getId(), nodo2);
        nodi.put(nodo3.getId(), nodo3);
        nodi.put(nodo4.getId(), nodo4);
        nodi.put(nodo5.getId(), nodo5);

        HashSet<Arco> archi = new HashSet<>();
        Arco arco1 = new Arco(nodo1, nodo2, 6);
        Arco arco2 = new Arco(nodo1, nodo3, 1);
        Arco arco3 = new Arco(nodo2, nodo5, 4);
        Arco arco4 = new Arco(nodo3, nodo4, 2);
        Arco arco5 = new Arco(nodo3, nodo2, 3);
        Arco arco6 = new Arco(nodo4, nodo1, 4);
        Arco arco7 = new Arco(nodo4, nodo2, 1);
        Arco arco8 = new Arco(nodo4, nodo5, 3);
        archi.add(arco1);
        archi.add(arco2);
        archi.add(arco3);
        archi.add(arco4);
        archi.add(arco5);
        archi.add(arco6);
        archi.add(arco7);
        archi.add(arco8);*/

        flussoCostoMinimo(nodi, archi);

    }


    public static double flussoCostoMinimo(HashMap<Integer, Nodo> nodi, HashSet<Arco> archi) {

        try {

            IloCplex model = new IloCplex();

            /**insieme di variabili**/
            IloNumVar[] variabili = model.numVarArray(archi.size(), 0, Double.MAX_VALUE);

            /**funzione obiettivo**/
            IloLinearNumExpr function = model.linearNumExpr();

            int i = 0;
            for (Iterator<Arco> it = archi.iterator(); it.hasNext(); ) {
                Arco arco = it.next();
                function.addTerm(variabili[i], arco.getCosto());
                i++;
            }
            model.addMinimize(function);

            int j = 0;
            for (Iterator<Nodo> it = nodi.values().iterator(); it.hasNext(); ) {
                Nodo nodo = it.next();
                IloLinearNumExpr v = model.linearNumExpr();

                int k = 0;
                for (Iterator<Arco> it1 = archi.iterator(); it1.hasNext(); ) {
                    Arco arco = it1.next();

                    if (arco.getNodo_from() == nodo)
                        v.addTerm(variabili[k], 1);
                    if (arco.getNodo_to() == nodo)
                        v.addTerm(variabili[k], -1);

                    k++;
                }
                model.addEq(v, nodo.getValore(), "vincolo nodo " + nodo.getId());
                j++;
            }
            model.exportModel("problemaFlusso3.lp");

            /**soluzione**/
            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());

                for (int v = 0; v < variabili.length; v++) {
                    System.out.println("x" + (v + 1) + " value: " + model.getValue(variabili[v]));
                }

            } else {
                System.out.println("Solution status: " + model.getStatus());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


}


