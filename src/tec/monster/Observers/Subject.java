package tec.monster.Observers;

import tec.monster.GameEssentials.Cards;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observerList = new ArrayList<Observer>();
    private Cards state;

    public Cards getState(){
        return state;
    }

    public void setState(Cards newState){
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
