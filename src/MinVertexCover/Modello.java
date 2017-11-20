package MinVertexCover;

import Grafo.Arco;
import Grafo.Nodo;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.Iterator;

public class Modello {

    public static void run(ArrayList<Nodo> nodi, ArrayList<Arco> archi) {

        try {

            IloCplex model = new IloCplex();

            /**insieme di variabili**/
            IloNumVar[] x = model.boolVarArray(nodi.size());

            /**funzione obiettivo**/
            IloLinearNumExpr function = model.linearNumExpr();

            for (int i = 0; i < nodi.size(); i++) {
                function.addTerm(x[i], 1);
            }
            model.addMinimize(function);

            /**vincoli**/
            for (int j = 0; j < archi.size(); j++) {
                Nodo from = archi.get(j).getNodo_from();
                Nodo to = archi.get(j).getNodo_to();

                IloLinearNumExpr v = model.linearNumExpr();
            }

            model.exportModel("VertexCover.lp");

            /**soluzione**/
            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());

                /*for (int v = 0; v < variabili.length; v++) {
                    System.out.println("x" + (v + 1) + " value: " + model.getValue(variabili[v]));
                }*/

            } else {
                System.out.println("Solution status: " + model.getStatus());
            }

            model.end();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
