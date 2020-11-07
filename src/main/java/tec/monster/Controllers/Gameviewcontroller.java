package tec.monster.Controllers;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import tec.monster.Connections.Client;
import tec.monster.Connections.Paquete;
import tec.monster.Connections.Server;
import javafx.fxml.FXML;
import tec.monster.Game.Cards;
import tec.monster.DeckStructure.Deck;
import tec.monster.Game.Player;
import tec.monster.HandStructure.Hand;
import tec.monster.Observers.Observer;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/***
 * Controlador de el archivo Gameview.fxml su función principal es la de llamar a las diferentes clases encargadas de
 * controlar las acciones realizadas en la ventana.
 *
 * author Bryan
 */

public class Gameviewcontroller extends Observer {
    private Server servidor;
    private Deck deck;
    private Hand hand,rivalhand;

    private Timer timer;
    private int rondactual = 0;
    private int puertorival;

    private ArrayList<Button> listabotones,listacartasrival;
    private ArrayList<Label> mostradorcartas,mostradorcartasrival;


    private String oponente;

    private Player jugador;

    private Paquete pack;

    @FXML
    private Label nickname,nickoponente,pvida,pmana,pvidarival,pmanarival,numronda;
    @FXML
    private Label hechizo,esbirro,secreto;
    @FXML
    private Circle indicaturnorival,indicaturno;
    @FXML
    private Button passbutton,ideck,c1,c2,c3,c4,cr1,cr2,cr3,cr4;
    @FXML
    private Pane campojuego,cartasoponente;


    /**
     * Uploader se encarga de al iniciar la ventana se generén las listas que contienen las cartas del deck y las de la mano
     * para empezar el juego
     */

    private void Uploader(){
        this.listabotones = new ArrayList<>();//lista con los botones que conforman la mano
        this.listabotones.add(c1);
        this.listabotones.add(c2);
        this.listabotones.add(c3);
        this.listabotones.add(c4);
        this.listacartasrival = new ArrayList<>();//lista de botones de la mano rival
        this.listacartasrival.add(cr1);
        this.listacartasrival.add(cr2);
        this.listacartasrival.add(cr3);
        this.listacartasrival.add(cr4);

        try {
            Cards firts = deck.Top();
            Image img = new Image(firts.getImagen());
            ImageView view = new ImageView(img);
            ideck.setGraphic(view);

            Hand aux = hand;

            for(Button boton1:listacartasrival){
                Image rimg = new Image(rivalhand.extractfirts().getImagen());
                ImageView rview = new ImageView(rimg);

                boton1.setGraphic(rview);
            }

            for(Button boton:listabotones){
                Image bimg = new Image(aux.extractfirts().getImagen());
                ImageView bview = new ImageView(bimg);


                boton.setGraphic(bview);
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Hubo un problema cargar las cartas"+ e);
            System.out.println(servidor.getState().getFuente());
        }

    }
    /**
     * Se encarga de crear el deck al inicio de la partida, lee los objetos del archivo
     * Cards.json y los transforma a instancias de la clase Cards
     */

    public void setServer(Server server){
        this.servidor = server;
        oponente = this.servidor.getState().getUsuario();
        this.nickoponente.setText(oponente);
        this.subject = servidor;
        this.subject.add(this);
        this.jugador = this.servidor.getState().getJugador();

        pmanarival.setText(Integer.toString(this.servidor.getState().getJugador().getMana()));
        pvidarival.setText(Integer.toString(this.servidor.getState().getJugador().getLife()));
    }

    public void setPack(Paquete pack) {
        this.pack = pack;
    }
    public void setNickname(String name){
        nickname.setText(name);
    }
    public void setDeck(Deck deck) { this.deck = deck; }
    public void setHand(Hand hand) { this.hand = hand; }
    public void setRivalhand(Hand rivalhand) { this.rivalhand = rivalhand;}

    /***
     * Método que lleva el conteo de las rondas, cada ronda dura 20 segundos
     */
    public void Ronds(){
            timer = new Timer();//cronometro para las rondas

            TimerTask ronda = new TimerTask() {
                @Override
                public void run() {
                    //codigo que se ejecuta cada 20 segundos
                    rondactual++;
                    Platform.runLater(()->numronda.setText(Integer.toString(rondactual)));
                    pack.setRonda(rondactual);
                    pack.setJugador(jugador);
                    if(rondactual%2==0){
                        indicaturnorival.setFill(Color.GREEN);
                        indicaturno.setFill(Color.RED);
                    }else{
                        indicaturnorival.setFill(Color.RED);
                        indicaturno.setFill(Color.GREEN);

                    }
                    Client cliente = new Client(pack);
                    Thread hilo = new Thread(cliente);
                    hilo.start();
                }
            };
            timer.scheduleAtFixedRate(ronda, 10000, 30000);
    }


    public void AccionCarta(){


    }
    /**
     * Su función es la de correr el método encargado de dar las cartas iniciales y formar el deck
     * @param mode según sea el modo anfitrión o invitado cambia la manera de llevar la cuenta de la ronda.
     */
    public void Start(String mode) {
        pvida.setText(Integer.toString(jugador.getLife()));
        pmana.setText(Integer.toString(jugador.getMana()));
        numronda.setText(Integer.toString(1));
        if(mode == "Anfitrion"){
            Ronds();
        }
        mostradorcartas = new ArrayList<>();
        mostradorcartas.add(secreto);
        mostradorcartas.add(hechizo);
        mostradorcartas.add(secreto);

        for (Label etiqueta:mostradorcartas) {
            Image simg = new Image("tec/monster/Gameimages/Tapa.png");
            ImageView sview = new ImageView(simg);
            etiqueta.setGraphic(sview);
        }

        Uploader();
    }

    /**
     * Método que se implemeta ya que la clase GameviewController es un observador y cada vez que
     * el server envía información la ventana se actualiza.
     *
     */
    @Override
    public void update() {//esto solo lo usa la ventana que se abrio derivada de el invitado
        Platform.runLater(()->numronda.setText(Integer.toString(servidor.getState().getRonda())));
        if(servidor.getState().getRonda()%2==0){
            indicaturnorival.setFill(Color.RED);
            indicaturno.setFill(Color.GREEN);
        }else{
            indicaturnorival.setFill(Color.GREEN);
            indicaturno.setFill(Color.RED);
        }
    }
}