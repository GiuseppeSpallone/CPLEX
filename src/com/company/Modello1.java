package com.company;

import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class Modello1 {

    public static void main(String[] args) {
        modello();
    }

    public static void modello() {

        try {
            IloCplex model = new IloCplex();

            /**insieme di variabili**/
            // x1 = quantità normale
            // x2 = quantità super
            IloNumVar x1 = model.numVar(0.0, Double.MAX_VALUE, "normale");
            IloNumVar x2 = model.numVar(0.0, Double.MAX_VALUE, "super");

            /**funzione obiettivo**/
            // max 1000x1 + 1200x2
            IloLinearNumExpr function = model.linearNumExpr();
            function.addTerm(x1, 1000);
            function.addTerm(x2, 1200);
            model.addMaximize(function);

            /**vincoli**/
            // x1 + 0,4x2 <= 4
            IloLinearNumExpr v1 = model.linearNumExpr();
            v1.addTerm(x1, 1);
            v1.addTerm(x2, 0.4);
            model.addLe(v1, 4, "v1");
            // 0,75x1 + x2 <= 6
            IloLinearNumExpr v2 = model.linearNumExpr();
            v1.addTerm(x1, 0.75);
            v1.addTerm(x2, 1);
            model.addLe(v2, 6, "v2");
            // x1 <= 3,5
            model.addLe(x1, 3.5, "v3");

            model.exportModel("modello1.lp");

            /**soluzione**/
            if (model.solve()) {
                System.out.println("Solution status: " + model.getStatus());
                System.out.println("Solution value: " + model.getObjValue());
                System.out.println("x1 value: " + model.getValue(x1));
                System.out.println("x2 value: " + model.getValue(x2));
            } else {
                System.out.println("Solution status: " + model.getStatus());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
