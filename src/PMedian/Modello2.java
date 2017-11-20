package PMedian;

import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class Modello2 {

    public static void run(int o, int[] d, int[][] c, int p) {
        try {

            IloCplex model = new IloCplex();

            /**insieme di variabili**/
            IloNumVar[] x = model.boolVarArray(o);
            IloNumVar[][] y = new IloNumVar[o][];

            for (int i = 0; i < o; i++) {

                y[i] = model.boolVarArray(d.length);
            }

            /**funzione obiettivo**/
            IloLinearNumExpr function = model.linearNumExpr();

            for (int i = 0; i < o; i++) {
                for (int j = 0; j < d.length; j++) {
                    function.addTerm(y[i][j], c[i][j] * d[i]);
                }
            }
            model.addMinimize(function);

            /**vincoli**/
            //vincolo 1
            IloLinearNumExpr v = model.linearNumExpr();
            for (int i = 0; i < o; i++) {
                v.addTerm(x[i], 1);
            }
            model.addEq(v, p);

            //vincolo 2
            for (int j = 0; j < d.length; j++)
             {
                IloLinearNumExpr v1 = model.linearNumExpr();
                 for (int i = 0; i < o; i++)
                 {
                    v1.addTerm(y[i][j], 1);
                }
                model.addEq(v1, 1);
            }

            //vincolo 3
            for (int i = 0; i < o; i++) {

                for (int j = 0; j < d.length; j++) {
                    model.addLe(y[i][j], x[i]);
                }
            }

            /**soluzione**/
            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());

                for (int i = 0; i < o; i++) {
                    if (model.getValue(x[i]) > 0.5)
                        System.out.println("Offerta " + (i + 1));
                }

            } else {
                System.out.println("Solution status: " + model.getStatus());
            }

            model.end();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
