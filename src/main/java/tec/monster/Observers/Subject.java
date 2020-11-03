package tec.monster.Observers;

import tec.monster.Connections.Paquete;
import tec.monster.Observers.Observer;
import tec.monster.Game.Cards;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observerList = new ArrayList<Observer>();
    private Paquete state;

    public Paquete getState(){
        return state;
    }

    public void setState(Paquete newState){
        this.state = newState;
        NotifyAll();
    }

    public void add(Observer observer){
        observerList.add(observer);
    }

    public void NotifyAll(){
        for (Observer observer : observerList){
            observer.update();
        }
    }
}
