package tec.monster.Game;

import javafx.scene.Node;
import tec.monster.DataStructures.Nodo;

public class Deck {
    private Nodo first;//Inicio de la pila
    private int size;
    public Deck(){
        first = null;
        size = 0;
    }
    /**
     * Consulta si el deck esta vacío
     * @return true si el primer nodo, no apunta a otro nodo.
     */
    public boolean isEmpty(){
        return first == null;
    }
    /**
     * Consulta cuantas cartas tiene el deck.
     * @return el numero de cartas que contenga la lista
     */
    public int getSize(){
        return size;
    }
    /**
     * Agrega una nueva carta al deck.
     * @param carta a agregar.
     */
    public void Push(Cards carta){
        Nodo nuevo = new Nodo();
        nuevo.setCarta(carta);
        if (!isEmpty()) {
            nuevo.setNext(first);
        }
        first = nuevo;
        size++;
    }
    /**
     * Elimina el elemento que se encuentra en de primero en el deck.
     */
    public void Pop(){
        if (!isEmpty()) {
            first = first.getNext();//asigna el nodo siguiente al inicio de la pila
            size--;
        }
    }
    /**
     * Consulta la carta que se encuentra de primero en el deck.
     * @return un objeto de tipo Cards.
     * @throws Exception
     */
    public Cards Top() throws Exception {
        if(!isEmpty()){
            return first.getCarta();
        } else {
            throw new Exception("El deck se encuentra vacio.");
        }
    }
    /**
     * Busca un elemento en el deck.
     * @param reference el ID de la carta a buscar
     * @return true si la carta de referencia existe en el deck.
     */
    public boolean Search(String reference){
        //se tiene que llamar con carta.getID()

        Nodo aux = first;//copia el primer elemento de la pila

        boolean existe = false;

        while(existe != true && aux != null){
            if (reference == aux.getCarta().getID()) {
                existe = true;
            }
            else{
                aux = aux.getNext();
            }
        }
        return existe;
    }
    /**
     * Elimina una carta del deck ubicado.
     * @param reference carta de referencia en el deck.
     */
    public void Remove(String reference){
        if (Search(reference)) {
            Nodo pilaAux = null;
            while(reference != first.getCarta().getID()){
                Nodo temp = new Nodo();
                temp.setCarta(first.getCarta());
                if(pilaAux == null){
                    pilaAux = temp;
                }
                else{
                    temp.setNext(pilaAux);
                    pilaAux = temp;
                }
                Pop();
            }
            Pop();
            while(pilaAux != null){
                Push(pilaAux.getCarta());
                pilaAux = pilaAux.getNext();
            }
            pilaAux = null;
        }
    }
    /**
     * Actualiza el estado de la carta en deck.
     * @param reference carta que se quiere editar.
     * @param carta carta nueva.
     */
    public void Edit(String  reference, Cards carta){
        if (Search(reference)) {
            Nodo pilaAux = null;
            while(reference != first.getCarta().getID()){
                Nodo temp = new Nodo();
                temp.setCarta(first.getCarta());
                if(pilaAux == null){
                    pilaAux = temp;
                }
                else{
                    temp.setNext(pilaAux);
                    pilaAux = temp;
                }
                Pop();
            }

            first.setCarta(carta);

            while(pilaAux != null){
                Push(pilaAux.getCarta());
                pilaAux = pilaAux.getNext();
            }
            pilaAux = null;
        }
    }
    /**
     * Elimina el deck
     */
    public void Delete(){
        first = null;
        size = 0;
    }
    public void ShowId(){
        Nodo aux = first;

        while (aux!=null){
            System.out.println(aux.getCarta().getID());
            System.out.println(aux.getCarta().getImagen());
            System.out.println("-------------------");

            aux = aux.getNext();
        }
    }
}
//http://codigolibre.weebly.com/blog/pilas-en-java#:~:text=Una%20pila%20(stack%20en%20inglés,entrar%2C%20primero%20en%20salir).