package FlussoCostoMinimo;

import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;

public class Modello {

    public static void run(ArrayList<int[]> grafo, int[] nodi) {

        try {

            IloCplex model = new IloCplex();

            IloNumVar[] variabili = model.numVarArray(grafo.size(), 0, Double.MAX_VALUE);

            IloLinearNumExpr function = model.linearNumExpr();

            for (int i = 0; i < variabili.length; i++) {
                function.addTerm(variabili[i], grafo.get(i)[2]);
            }
            model.addMinimize(function);

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
            model.exportModel("FlussoCostoMinimo.lp");

            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());
            } else {
                System.out.println("Solution status: " + model.getStatus());
            }

            model.end();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

