package tec.monster.Controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import tec.monster.Connections.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tec.monster.Game.Cards;
import tec.monster.DeckStructure.Deck;
import tec.monster.Game.DeckControl;
import tec.monster.Game.Handcontrol;
import tec.monster.HandStructure.Hand;
import tec.monster.HandStructure.Handnode;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * Controlador de el archivo Gameview.fxml su función principal es la de llamar a las diferentes clases encargadas de
 * controlar las acciones realizadas en la ventana.
 *
 * author Bryan
 */

public class Gameviewcontroller implements Initializable {
    private Server servidor;
    private Deck deck;
    private Hand hand;
    @FXML
    private Label nickname,nickoponente,pvida,pmana,pvidarival,pmanarival,numronda;
    @FXML
    private Circle indicaturnorival,indicaturno;
    @FXML
    private Button passbutton,ideck,c1,c2,c3,c4,cr1,cr2,cr3,cr4;
    @FXML
    private Pane campojuego,cartasoponente;
    @FXML
    AnchorPane cartasmazo;
    /***
     * Se encarga de obtener la lista circular enlazada de cartas 
     * 
     */
    private void Uploader(){
        ArrayList<Button> listabotones = new ArrayList<>();
        listabotones.add(c1);
        listabotones.add(c2);
        listabotones.add(c3);
        listabotones.add(c4);

        try {
            Cards firts = deck.Top();
            Image img = new Image(firts.getImagen());
            ImageView view = new ImageView(img);
            Hand aux = hand;
            for(Button boton:listabotones){
                Image bimg = new Image(aux.extractfirts().getImagen());
                ImageView bview = new ImageView(bimg);


                boton.setGraphic(bview);
            }

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

    /**
     * Se encarga de mostrar el nickname del usuarion en pantalla
     * @param name
     */
    public void setNickname(String name){
        nickname.setText(name);
    }
    /**
     * Se encarga de inicializar el juego y mostrar el deck y la mano al jugador en pantalla
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numronda.setText("1");
        //indicaturno.setFill(Color.GREEN);
        deck = new DeckControl().GenerateDeck();
        Handcontrol chand = new Handcontrol();
        this.hand = chand.GenerateHand(deck);
        deck = chand.getDeck();

        Uploader();
    }
}
