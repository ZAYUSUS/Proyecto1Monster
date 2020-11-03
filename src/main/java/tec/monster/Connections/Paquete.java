package tec.monster.Connections;

import tec.monster.Game.Cards;

import java.io.Serializable;

public class Paquete implements Serializable {
    int puerto;
    String usuario;
    Cards carta;

    public int getPuerto() {
        return puerto;
    }
    public String getUsuario() {
        return usuario;
    }

    public Cards getCarta() {
        return carta;
    }
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setCarta(Cards carta) {
        this.carta = carta;
    }
}
