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

    public boolean isEmpty(){return firts==null;}
    /**
     * Método para insertar nodos
     *
     * @param carta
     */
    public void Insert(Cards carta){
        Handnode nuevo = new Handnode();
        nuevo.setCarta(carta);

        if (this.firts==null){
            firts = nuevo;
            firts.setNext(firts);
            firts.setPrevious(firts);
            last = nuevo;
        }else{
            last.setNext(nuevo);
            nuevo.setNext(firts);
            nuevo.setPrevious(firts.getPrevious());
            firts.setPrevious(nuevo);
            nuevo.previous.next = nuevo;
            firts=nuevo;
        }
        size++;

    }
    /***
     * Metodo que busca una carta por su Id
     * param id es el nombre de la carta a buscar
     */
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

    /**
     * Método para extraer el primer elemento de la lista
     */
    public Cards extractfirts() {
        if (firts != null) {
            Cards carta;
            if (firts.next != firts) {
                carta = firts.carta; // se inserta el dato
                firts.next.previous = firts.getPrevious(); // inicio en su nodo siguiente, en su .ant apunta al nodo anterior de inicio
                firts.previous.next = firts.getNext(); // inicio en su nodo anterior, en su .next apunta al nodo siguiente de inicio
                firts = firts.next;
                size--;
            } else {
                carta = firts.getCarta();
                firts = null;
            }
            return carta;
        }
        return null;
    }

    /***
     * Método para eliminar una carta de la lista por su Id
     *
     */
    public void Remove(String id){
        boolean state=false;
        Handnode aux = firts;

        if(aux != null){
            while (aux.getNext() != firts){
                if(aux.getCarta().getID().equals(id)){
                    firts.next.previous = aux.getPrevious();
                    firts.previous.next = aux.getNext();
                    firts = firts.getNext();
                    state=true;
                    size--;
                }
                aux = aux.getNext();
            }
        }
    }
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
