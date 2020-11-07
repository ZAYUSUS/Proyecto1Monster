package tec.monster.Observers;

import tec.monster.Connections.Paquete;
import tec.monster.Observers.Observer;
import tec.monster.Game.Cards;

import java.util.ArrayList;
import java.util.List;

/***
 * Sujeto al cual se observara para notificcar los cambios a un observador
 * o observadores dados.
 *
 * Esta clase es usada en el Server
 *
 */

public class Subject {
    private List<Observer> observerList = new ArrayList<Observer>();
    private Paquete state;


    /**
     *
     * @return el estado actual del sujeto
     */
    public Paquete getState(){
        return state;
    }

    /***
     * Obtiene un objeto que indica un cambio de estado
     * @param newState nuevo objeto se toma como el nuevo estado
     */
    public void setState(Paquete newState){
        this.state = newState;
        NotifyAll();
    }

    /**
     * Añade un observador a la lista
     * @param observer
     */
    public void add(Observer observer){
        observerList.add(observer);
    }

    /**
     * Notifica que algo cambió en el objeto
     */
    public void NotifyAll(){
        for (Observer observer : observerList){
            observer.update();
        }
    }
}
