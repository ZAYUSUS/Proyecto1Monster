package tec.monster.HandStructure;

import tec.monster.Game.Cards;

import java.io.Serializable;

public class Hand implements Serializable {
    Handnode firts;
    Handnode last;
    int size;
    /***
     * Contructor de la lista, inicia sin elementos
     * por eso el firts y last son nulos
     *
     */
    public Hand(){
        this.firts = null;
        this.last = null;
    }
    public int getSize() {return size;}
    public Handnode getFirts(){
        return this.firts;
    }

    /**
     * Método que verifica si la lista tienen elementos o no
     * @return true si está vacía la lista
     */

    public boolean isEmpty(){return firts==null;}
    /**
     * Método para insertar nodos
     *
     * @param carta
     */
    public void Insert(Cards carta){
        Handnode nuevo = new Handnode();
        nuevo.setCarta(carta);

        if (isEmpty()){
            firts = nuevo;
            firts.setNext(firts);
            firts.setPrevious(firts);
            last = nuevo;
        }else{
            last.setNext(nuevo);
            nuevo.setNext(firts);
            nuevo.setPrevious(firts.getPrevious());
            firts.setPrevious(nuevo);
            nuevo.getPrevious().setNext(nuevo);
            nuevo.previous.next = nuevo;

            firts=nuevo;
        }
        size++;

    }
    /***
     * Metodo que busca una carta por su Id
     * param id es el nombre de la carta a buscar
     */
    /*
    public Cards Search(String id){
        boolean state = false;
        Handnode aux = firts;

        if (aux!=null){
            while (aux.getNext()!=firts){
                if (aux.getCarta().getID().equals(id)){
                    state=true;
                    break;
                }
                aux = aux.getNext();
            }
        }
        if (state){
            return aux.getCarta();
        }else{
            return null;
        }
    }
     */

    /***
     * Muestra los Id de las cartas en la lista
     */
    public void Show(){
        int cont = 1;
        Handnode aux = firts;

        if (aux!= null){
            while (aux.getNext()!=firts){
                System.out.println( cont +". "+aux.getCarta().getID());
                cont++;
                aux= aux.next;
            }
            System.out.println(cont+"."+aux.getCarta().getID());
        }
    }
}
