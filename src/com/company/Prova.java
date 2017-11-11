package com.company;

import com.company.Grafo.Arco;
import com.company.Grafo.Nodo;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

import java.util.ArrayList;


public class Prova {

    public static void main(String[] args) {
        ArrayList<Nodo> I = new ArrayList<>();
        Nodo i1 = new Nodo(1, 20);
        Nodo i2 = new Nodo(2, 18);
        I.add(i1);
        I.add(i2);

        ArrayList<Nodo> J = new ArrayList<>();
        Nodo j1 = new Nodo(3, 0);
        Nodo j2 = new Nodo(4, 0);
        Nodo j3 = new Nodo(5, 0);
        Nodo j4 = new Nodo(6, 0);
        Nodo j5 = new Nodo(7, 0);
        J.add(j1);
        J.add(j2);
        J.add(j3);
        J.add(j4);
        J.add(j5);

        ArrayList<Arco> D = new ArrayList<>();
        Arco i1j1 = new Arco(i1, j1, 8);
        Arco i1j2 = new Arco(i1, j2, 4);
        Arco i1j3 = new Arco(i1, j3, 3);
        Arco i1j4 = new Arco(i1, j4, 5);
        Arco i1j5 = new Arco(i1, j5, 7);
        D.add(i1j1);
        D.add(i1j2);
        D.add(i1j3);
        D.add(i1j4);
        D.add(i1j5);

        Arco i2j1 = new Arco(i2, j1, 3);
        Arco i2j2 = new Arco(i2, j2, 5);
        Arco i2j3 = new Arco(i2, j3, 6);
        Arco i2j4 = new Arco(i2, j4, 7);
        Arco i2j5 = new Arco(i2, j5, 9);
        D.add(i2j1);
        D.add(i2j2);
        D.add(i2j3);
        D.add(i2j4);
        D.add(i2j5);

        int p = 1;

    }


}
