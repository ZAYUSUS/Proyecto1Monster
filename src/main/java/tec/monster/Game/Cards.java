package tec.monster.Game;

import javafx.scene.control.Button;

import java.io.Serializable;

public class Cards extends Button implements Serializable {
    private String Usuario;
    private int puertoSalida;
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

    public void setPuerto(int puerto) {
        this.puertoSalida = puerto;
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
    public int getPuerto() {
        return puertoSalida;
    }
}
