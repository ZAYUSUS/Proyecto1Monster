package tec.monster.graphics.controllers.views;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tec.monster.connections.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class Invitadocontroller implements Initializable {
    @FXML
    private Button botonconectar;
    @FXML
    private TextField eIP;
    @FXML
    private TextField ePuerto;


    public void Conectar(){
        int puerto = Integer.parseInt(this.ePuerto.getText());
        Client cliente = new Client(puerto,this.eIP.getText());
        Thread hilo = new Thread(cliente);
        hilo.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}