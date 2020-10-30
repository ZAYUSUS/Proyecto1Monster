package tec.monster.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import tec.monster.Observers.Observer;
import tec.monster.connections.Server;

import java.net.URL;
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

public class Anfitrioncontroller extends Observer implements Initializable{
    private Server servidor;
    @FXML
    private Button conectar;
    @FXML
    private TextArea statusarea;

    private void Inicializador(){
        this.statusarea.appendText("Abriendo el server...\n");
        this.servidor = new Server();
        this.subject = servidor;
        this.subject.add(this);
        Thread hilo = new Thread(servidor);
        hilo.start();
        this.statusarea.appendText("Conectando...\n");
        this.statusarea.appendText("Servidor escuchando en el puerto: \n"+servidor.getPort()+"\n");
        this.statusarea.appendText("Esperando...\n");
    }

    /**
     *
     * Se sobreescribe por la implementación de la interfaz Initializable, este método se corre cuando se presione el boton banfitrion de la
     * ventana de la clase MenuInicial.
     * Método encargado de instanciar la clase Server  e informar al statusarea el avance con la conexión
     * También crea un hilo para correr el servidor esto habilita que se pueda correr sin interrumpir el hilo principal.
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
     */
    @Override
    public void update() {
        statusarea.appendText("Se encontró una conexión "+servidor.getState().getUsuario());
    }
}
