package tec.monster.DataStructures;

import tec.monster.Game.Cards;

public class Nodo {
    private Cards dato;
    private Nodo siguiente;


    public void Nodo(){
        this.dato = new Cards();
        this.siguiente = null;
    }

    // Métodos get y set para los atributos.

    public Cards getCarta() {
        return dato;
    }

    public void setCarta(Cards carta) {
        this.dato = carta;
    }

    public Nodo getNext() {
        return siguiente;
    }

    public void setNext(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}