package com.company.Grafo;

public class Arco {
    private Nodo nodo_from;
    private Nodo nodo_to;
    private int costo;

    public Arco(Nodo nodo_from, Nodo nodo_to, int costo) {
        this.nodo_from = nodo_from;
        this.nodo_to = nodo_to;
        this.costo = costo;
    }

    public Nodo getNodo_from() {
        return nodo_from;
    }

    public void setNodo_from(Nodo nodo_from) {
        this.nodo_from = nodo_from;
    }

    public Nodo getNodo_to() {
        return nodo_to;
    }

    public void setNodo_to(Nodo nodo_to) {
        this.nodo_to = nodo_to;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
