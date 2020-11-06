package tec.monster.Game;
/**
 * Clase encargada del diseño y la especificación de las cartas utilizadas en el juego para mostrarlas en pantalla
 * @author Bryan & Gustavo
 *
 */
public class Cards{
    private String id;
    private String efecto;
    private String tipo;
    private String imagen;
    private int coste;
    private int damage;


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
