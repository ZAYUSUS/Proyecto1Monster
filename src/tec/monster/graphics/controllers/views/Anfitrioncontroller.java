package tec.monster.graphics.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import tec.monster.connections.Server;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class Anfitrioncontroller implements Initializable,Observer {
    Server servidor;
    @FXML
    private TextArea statusarea;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializador();
    }

    @Override
    public void update(Observable o, Object arg) {
        statusarea.appendText(servidor.getCarta().getColor());
    }
}
