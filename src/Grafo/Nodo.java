package Grafo;

import java.util.ArrayList;

public class Nodo {
    public int id;
    public int valore;
    public ArrayList<Arco> archi = new ArrayList<>();

    public Nodo(int id) {
        this.id = id;
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

    public ArrayList<Arco> getArchi() {
        return archi;
    }

    public void setArchi(ArrayList<Arco> archi) {
        this.archi = archi;
    }
}
