package tec.monster.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import tec.monster.Connections.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tec.monster.Game.Cards;
import tec.monster.Game.Deck;
import tec.monster.Game.DeckControl;

import java.net.URL;
import java.util.ResourceBundle;

public class Gameviewcontroller implements Initializable {
    private Server servidor;
    private Deck deck;
    @FXML
    private Label nickname,nickoponente,pvida,pmana,pvidarival,pmanarival,numronda;
    @FXML
    private Circle indicaturnorival,indicaturno;
    @FXML
    private Button passbutton,ideck;

    private void Uploader(){
        try {
            Cards firts = deck.Top();
            Image img = new Image(firts.getImagen());
            ImageView view = new ImageView(img);

            ideck.setGraphic(view);

        }catch (Exception e){
            System.out.println("Hubo un problema cargando el deck"+ e);
        }

    }

    /**
     * Se encarga de crear el deck al inicio de la partida, lee los objetos del archivo
     * Cards.json y los transforma a instancias de la clase Cards
     */

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
        numronda.setText("1");
        //indicaturno.setFill(Color.GREEN);
        deck= new DeckControl().GenerateDeck();
        Uploader();
    }
}
