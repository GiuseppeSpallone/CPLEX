package com.company.Grafo;

public class Nodo {
    private int id;
    private int valore;

    public Nodo(int id, int valore) {
        this.id = id;
        this.valore = valore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValore() {
        return valore;
    }

    public void setValore(int valore) {
        this.valore = valore;
    }
}
