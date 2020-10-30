package tec.monster.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tec.monster.connections.Client;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * Clase encargada de controlar las acciones realizadas en la ventana de el invitado,
 * esta clase crea un instancia de la clase Cliente que es el encargado de crear la conexión con el ServerSocket
 * mediante un Socket.
 *
 * @author Bryan
 * @since 1.0
 */
public class Invitadocontroller implements Initializable {
    @FXML
    private Button botonconectar;
    @FXML
    private TextField eIP,ePuerto,eNombre;

    /**
     * Método encargado de instanciar un objeto de la clase Cliente para conectar por medio de un socket al servidor,
     * la dirección a la que se va a conectar es indicada por el usuario mediante los objetos TextField de que
     * están ubicados en la ventana Invitadoview.
     */
    public void Conectar(){
        int puerto = Integer.parseInt(this.ePuerto.getText());
        Client cliente = new Client(puerto,this.eIP.getText(),this.eNombre.getText());
        Thread hilo = new Thread(cliente);
        hilo.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eIP.setText("127.0.0.1");
        ePuerto.setText("5000");
    }
}