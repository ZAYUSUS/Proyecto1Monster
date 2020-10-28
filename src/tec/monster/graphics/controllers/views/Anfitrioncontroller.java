package tec.monster.graphics.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import tec.monster.connections.Server;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Clase encargada de controlar las acciones realizadas en la ventana de el anfitrión,
 * esta crea la instancia del servidor y cambia la ventana para poder comenzar el juego al
 * haber encontrado un invitado que haya conectado con el server creado.
 * <p>
 *Esta clase implementa la interfaz Initializable y sobreescribe el método Initialize que permite ejecutar código
 *al iniciar una instancia de la clase. También implementa la interfaz Observer que permite recibir las actualizaciones de
 * la clase a la que se desee observar, en este caso será la clase servidor para saber cuándo se recibe información.
 *<p>
 *Posee como atributos un Server y por parte de la ventana a la que está asociado obtiene un objeto de tipo TextArea que es el
 * encargado de mostrar la información con respecto al server.
 *
 * @author Bryan
 * @since 1.0
 */

public class Anfitrioncontroller implements Initializable,Observer {
    Server servidor;
    @FXML
    private TextArea statusarea;

    /**
     * Método encargado de instanciar la clase Server  e informar al statusarea el avance con la conexión
     * También crea un hilo para correr el servidor esto habilita que se pueda correr sin interrumpir el hilo principal.
     *
     */
    public void Inicializador(){
        statusarea.appendText("Abriendo el server...\n");
        servidor = new Server();
        servidor.addObserver(this);
        Thread hilo = new Thread(servidor);
        hilo.start();
        statusarea.appendText("Conectando...\n");
        statusarea.appendText("Servidor escuchando en el puerto: "+servidor.getPort()+"\n");
        statusarea.appendText("Esperando...\n");

    }

    /**
     *
     * Se sobreescribe por la implementación de la interfaz Initializable, este método se corre cuando se presione el boton banfitrion de la
     * ventana de la clase MenuInicial.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializador();
    }

    /**
     * Clase sobreescrita por la implementación de la interfaz Observer, permite recibir la notificaión
     * de cambio del objeto observado, en este caso el objeto observado es la el servidor.
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        statusarea.appendText(servidor.getCarta().getColor());
    }
}
