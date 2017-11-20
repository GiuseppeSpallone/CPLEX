package PMedian;

import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class Modello {

    public static void run(int[] studenti, int[] fermate, int[][] distanze, int p) {
        try {

            IloCplex model = new IloCplex();

            /**insieme di variabili**/
            IloNumVar[] x = model.boolVarArray(fermate.length);
            IloNumVar[][] y = new IloNumVar[studenti.length][];

            for (int i = 0; i < studenti.length; i++) {

                y[i] = model.boolVarArray(fermate.length);
            }

            /**funzione obiettivo**/
            IloLinearNumExpr function = model.linearNumExpr();

            for (int i = 0; i < studenti.length; i++) {
                for (int j = 0; j < fermate.length; j++) {
                    function.addTerm(y[i][j], distanze[i][j] * studenti[i]);
                }
            }
            model.addMinimize(function);

            /**vincoli**/

            //vincolo 1
            IloLinearNumExpr v = model.linearNumExpr();
            for (int i = 0; i < fermate.length; i++) {
                v.addTerm(x[i], 1);
            }
            model.addEq(v, p);

            //vincolo 2
            for (int i = 0; i < studenti.length; i++) {
                IloLinearNumExpr v1 = model.linearNumExpr();

                for (int j = 0; j < fermate.length; j++) {
                    v1.addTerm(y[i][j], 1);
                }
                model.addEq(v1, 1);
            }

            //vincolo 3
            for (int i = 0; i < studenti.length; i++) {

                for (int j = 0; j < fermate.length; j++) {
                    model.addLe(y[i][j], x[j]);
                }
            }

            /**soluzione**/
            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());

                for (int i = 0; i < fermate.length; i++) {
                    if (model.getValue(x[i]) > 0.5)
                        System.out.println("fermata " + (i + 1));
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
