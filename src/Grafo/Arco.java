package Grafo;

public class Arco {
    public Nodo nodo_from;
    public Nodo nodo_to;
    public int costo;

    public Arco(Nodo nodo_from, Nodo nodo_to) {
        this.nodo_from = nodo_from;
        this.nodo_to = nodo_to;
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
