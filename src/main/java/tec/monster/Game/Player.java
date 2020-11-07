package tec.monster.Game;

import java.io.Serializable;

public class Player implements Serializable {
    int mana;
    int life;
    public Player(){
        mana=200;
        life =1000;
    }

    public int getLife() {
        return life;
    }

    public int getMana() {
        return mana;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
