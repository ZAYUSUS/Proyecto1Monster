package tec.monster.Connections;

import tec.monster.Game.Cards;

import java.io.Serializable;
/***
 * Clase encargada de obtener y setear el puerto, el usuario y las cartas para así generar el puente entre el cliente y el servidor
 * <p>
 * La clase implementa la interfaz Serializable la cual hace el empaquetamiento de los atributos para así ser enviados
 * @author Bryan
 *
 */
public class Paquete implements Serializable {
    int puerto;
    String usuario;
    Cards carta;


/***
 * Método que se encarga de obtener el puerto en el cual se van a conectar
 * @return puerto
 */
    public int getPuerto() {
        return puerto;
    }
    /***
     * Método que se encarga de obtener el nombre del usuario el cual está siendo el anfitrión de la conexión
     * @return usuario
     */
    public String getUsuario() {
        return usuario;
    }
    /***
     * Método que se encarga de obtener las cartas que se van a enviar 
     * @return carta
     */
    public Cards getCarta() {
        return carta;
    }
    /***
     * Método que se encarga de setear el atributo puerto para así actualizarlo con el puerto nuevo que se obtuvo del método getPuerto()
     * @param puerto
     */
    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    /***
     * Método que se encarga de setear el atributo usuario para así actualizarlo
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /***
     * Método que se encarga de setear el atributo carta para así actualizarlo
     * @param carta
     */
    public void setCarta(Cards carta) {
        this.carta = carta;
    }
}
