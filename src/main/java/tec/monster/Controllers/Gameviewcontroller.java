package tec.monster.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import tec.monster.HandStructure.Handnode;
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
    private int turno = 0;

    private ArrayList<Button> listacartas,listacartasrival;
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
    private Button botonataque,ideck;
    @FXML
    private Pane campojuego,cartasoponente;
    @FXML
    private AnchorPane principal;


    /**
     * Uploader se encarga de al iniciar la ventana se generén las listas que contienen las cartas del deck y las de la mano
     * para empezar el juego
     */

    private void Uploader(){
        this.listacartas = new ArrayList<>();//lista con los botones que conforman la mano
        this.listacartasrival = new ArrayList<>();//lista de botones de la mano rival

        try {
            Cards firts = deck.Top();
            Image img = new Image(firts.getImagen());
            ImageView view = new ImageView(img);
            ideck.setText(firts.getID());//le da la id como texto para luego poder buscar la carta por este mismo
            ideck.setGraphic(view);

            Hand aux = hand;
            Handnode caux = hand.getFirts();
            Handnode raux = rivalhand.getFirts();
            int contador = 0;
            int contar = 0;

            int posx = 150;
            int posxr = 150;

            while (contador<=hand.getSize()){
                Cards carta = caux.getCarta();
                Button boton = carta.GenerateButton();
                listacartas.add(boton);
                boton.setLayoutX(posx);
                boton.setLayoutY(669);
                boton.setOnMouseClicked(mouseEvent -> AccionCarta(boton,carta));
                boton.setDisable(true);

                principal.getChildren().add(boton);
                posx+=190;
                caux = caux.getNext();
                contador++;
            }
            while (contar<=rivalhand.getSize()){
                Cards carta = raux.getCarta();
                Button boton = carta.GenerateButton();
                listacartasrival.add(boton);
                boton.setLayoutX(posxr);
                boton.setLayoutY(14);


                principal.getChildren().add(boton);
                posxr+=190;
                raux = raux.getNext();
                contar++;
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
     * Método que lleva el conteo de las rondas, cada turno dura 15 segundos, cada dos
     * turno se aumenta la ronda
     */
    public void Ronds(){
            timer = new Timer();//cronometro para las rondas
            TimerTask ronda = new TimerTask() {
                @Override
                public void run() {
                    turno++;
                    if (turno%2!=0){
                        rondactual++;
                        int  mana = jugador.getMana();
                        if(mana+50>200){
                            jugador.setMana(200);
                        }else{
                            jugador.setMana(mana+50);
                        }
                        Platform.runLater(()->pmana.setText(Integer.toString(jugador.getMana())));
                    }
                    Platform.runLater(()->numronda.setText(Integer.toString(rondactual)));
                    pack.setRonda(rondactual);
                    pack.setJugador(jugador);
                    pack.setTurno(turno);
                    if(turno%2==0){
                        indicaturnorival.setFill(Color.GREEN);
                        indicaturno.setFill(Color.RED);
                        for (Button bot:listacartas) {
                            bot.setDisable(true);
                        }
                        botonataque.setDisable(true);

                    }else{
                        indicaturnorival.setFill(Color.RED);
                        indicaturno.setFill(Color.GREEN);
                        for (Button bot:listacartas) {
                            bot.setDisable(false);
                        }
                        botonataque.setDisable(false);
                    }
                    Notificador();
                }
            };
            timer.scheduleAtFixedRate(ronda, 0, 15000);
    }

    /***
     * Método que se usará por cada carta al ser presionada
     * @return
     */
    public  void AccionCarta(Button boton , Cards carta){

        if(jugador.getMana()>carta.getCoste()){
            listacartas.remove(boton);
            principal.getChildren().remove(boton);
            String tipo = carta.getTipo();
            int mana = jugador.getMana();
            jugador.setMana(mana-carta.getCoste());
            pmana.setText(Integer.toString(jugador.getMana()));
            this.pack.setJugador(jugador);
            Notificador();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ALERTA!");
            alert.setHeaderText(null);
            alert.setContentText("No tienes suficiente mana!");
            alert.showAndWait();
        }
    }
    /**
     * Su función es la de correr el método encargado de dar las cartas iniciales y formar el deck
     */
    public void Start() {
        Uploader();
        pvida.setText(Integer.toString(jugador.getLife()));
        pmana.setText(Integer.toString(jugador.getMana()));
        numronda.setText(Integer.toString(1));
        Ronds();
        mostradorcartas = new ArrayList<>();
        mostradorcartas.add(secreto);
        mostradorcartas.add(hechizo);
        mostradorcartas.add(secreto);

        for (Label etiqueta:mostradorcartas) {
            Image simg = new Image("tec/monster/Gameimages/Tapa.png");
            ImageView sview = new ImageView(simg);
            etiqueta.setGraphic(sview);
        }

    }

    /***
     * Método que crea un cliente uy envía los eventos que suceden en la ronda
     */
    public void Notificador(){
        Client cliente = new Client(this.pack);
        Thread hilo = new Thread(cliente);
        hilo.start();
    }

    /**
     * Método que se implemeta ya que la clase GameviewController es un observador y cada vez que
     * el server envía información la ventana se actualiza.
     *
     */
    @Override
    public void update() {//esto solo lo usa la ventana que se abrio derivada de el invitado
        Platform.runLater(()-> pmanarival.setText(Integer.toString(servidor.getState().getJugador().getMana())));
        Platform.runLater(()-> pvidarival.setText(Integer.toString(servidor.getState().getJugador().getLife())));

    }
}