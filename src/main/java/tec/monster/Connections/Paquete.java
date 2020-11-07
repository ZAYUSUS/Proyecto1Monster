package tec.monster.Connections;

import tec.monster.Game.Cards;
import tec.monster.Game.Player;
import tec.monster.HandStructure.Hand;

import java.io.Serializable;

/***
 * Clase utilizada para las comunicaciones entre los servers de los jugadores,
 * es el medio principal para transferir la información.
 */

public class Paquete implements Serializable {
    int puerto;//puerto donde se desea enviar la info
    int puerto_salida;//puerto del servidor donde escucha el rival
    int ronda;//la ronda del juego
    int turno;


    String Ip;
    String usuario;
    String fuente;

    Hand mano_rival;
    Cards carta;
    Player jugador;


//sets y gets de los atributos

    public int getPuerto() {
        return puerto;
    }
    public String getUsuario() {
        return usuario;
    }
    public int getRonda() {
        return ronda;
    }
    public Player getJugador() {
        return jugador;
    }
    public String getIp() {
        return Ip;
    }
    public int getPuerto_salida() {
        return puerto_salida;
    }

    public String getFuente() {
        return fuente;
    }

    public Hand getMano_rival() {
        return mano_rival;
    }

    public int getTurno() {
        return turno;
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

    public void setJugador(Player jugador) {
        this.jugador = jugador;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public void setPuerto_rival(int puerto_rival) {
        this.puerto_salida = puerto_rival;
    }
    public void setFuente(String fuente) {
        this.fuente = fuente;
    }
    public void setManoRival(Hand mano) {
        this.mano_rival = mano;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
