package tec.monster.HandStructure;

import tec.monster.Game.Cards;

public class Handnode {
    Cards carta;
    Handnode next;
    Handnode previous;

   public Handnode() {
        this.carta = new Cards();
        this.next = null;
        this.previous = null;
   }

    public Cards getCarta() { return carta;}
    public Handnode getNext(){ return next;}
    public Handnode getPrevious() { return previous;}

    public void setCarta(Cards carta) { this.carta = carta;}
    public void setNext(Handnode next) { this.next = next;}
    public void setPrevious(Handnode previous) {this.previous = previous;}
}
