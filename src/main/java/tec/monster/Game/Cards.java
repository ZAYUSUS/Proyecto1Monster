package tec.monster.Game;

import java.io.Serializable;

public class Cards implements Serializable {
/**
 * Clase encargada del diseño y la especificación de las cartas utilizadas en el juego para mostrarlas en pantalla
 * @author Bryan & Gustavo
 *
 */
    private String id;//Nombre de la carta
    private String efecto;//función de la carta
    private String tipo;//tipo de carta
    private String imagen;//imagen personalizada de la carta
    private int coste;
    private int damage;//daño de la carta


    public void setTipo(String Tipo) {
        tipo = Tipo;
    }
    public void setEfecto(String Efecto) {
        efecto = Efecto;
    }
    public void setImagen(String image) {
        this.imagen = image ;
    }
    public void setID(String ID) {
        id = ID;
    }

    public void setCoste(int Coste) {
        coste = Coste;
    }

    public void setDamage(int Damage) {
        damage = Damage;
    }

    public String getEfecto() {
        return efecto;
    }
    public String getTipo() {
        return tipo;
    }
    public String getImagen() {
        String dir = "tec/monster/Gameimages"+"/"+tipo+"/"+imagen;
        return dir;
    }
    public String getID() {
        return id;
    }
    public int getCoste() {
        return coste;
    }

    public int getDamage() {
        return damage;
    }
}
