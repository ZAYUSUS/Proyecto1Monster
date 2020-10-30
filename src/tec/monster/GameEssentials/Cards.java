package tec.monster.GameEssentials;

import java.io.Serializable;

public class Cards implements Serializable {
    private String Usuario;
    private String Efecto;
    private String Tipo;
    private String Color;


    /***
     * metodo para cambiar el color del objeto
     * @param color
     */
    public void setColor(String color) {
        Color = color;
    }
    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }
    public void setEfecto(String efecto) {
        this.Efecto = efecto;
    }

    public void setUsuario(String usuario) { Usuario = usuario;}

    public String getColor() {
        return this.Color;
    }
    public String getEfecto() {
        return this.Efecto;
    }
    public String getTipo() {
        return this.Tipo;
    }
    public String getUsuario() { return Usuario; }
}
