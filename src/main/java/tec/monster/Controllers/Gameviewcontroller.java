package tec.monster.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import tec.monster.Connections.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Gameviewcontroller implements Initializable {
    private Server servidor;
    @FXML
    private Label nickname,nickoponente,pvida,pmana,pvidarival,pmanarival,numronda;
    @FXML
    private Circle indicaturnorival,indicaturno;
    @FXML
    private Button passbutton;

    public void setServer(Server server){
        this.servidor = server;
        String oponente = this.servidor.getState().getUsuario();
        this.nickoponente.setText(oponente);
    }
    public void setNickname(String name){
        nickname.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //indicaturno.setFill(Color.GREEN);
    }
}
