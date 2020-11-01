package tec.monster.Controllers;

import tec.monster.Connections.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class Gameviewcontroller implements Initializable {
    private Server servidor;
    @FXML
    private TextArea statusarea;

    public void setServer(Server server){
        this.servidor = server;
        String oponente = this.servidor.getState().getUsuario();
        this.statusarea.appendText("Se conecto correctamente con: "+ oponente);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
