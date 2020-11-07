package tec.monster.HandStructure;

import tec.monster.Game.Cards;

import java.io.Serializable;

/**
 * Crea los elementos que van dentro de la lista circular doblemente enlazada
 *
 */

public class Handnode implements Serializable {
    Cards carta;
    Handnode next;
    Handnode previous;
//contructor por defecto
   public Handnode() {
        this.carta = new Cards();
        this.next = null;
        this.previous = null;
   }
   //sets y gets de los atributos
    public Cards getCarta() { return carta;}
    public Handnode getNext(){ return next;}
    public Handnode getPrevious() { return previous;}

    public void setCarta(Cards carta) { this.carta = carta;}
    public void setNext(Handnode next) { this.next = next;}
    public void setPrevious(Handnode previous) {this.previous = previous;}
}
