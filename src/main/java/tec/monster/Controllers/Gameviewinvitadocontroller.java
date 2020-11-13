package tec.monster.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tec.monster.Connections.Client;
import tec.monster.Connections.Paquete;
import tec.monster.Connections.Server;
import tec.monster.DeckStructure.Deck;
import tec.monster.Game.Cards;
import tec.monster.Game.Player;
import tec.monster.HandStructure.Hand;
import tec.monster.HandStructure.Handnode;
import tec.monster.History.ListaHistory;
import tec.monster.Observers.Observer;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;

public class Gameviewinvitadocontroller extends Observer {
    private Stage vista;

    private Server servidor;
    private Deck deck;
    private Hand hand,rivalhand;
    private Timer timer;
    private ListaHistory listaHistory;

    private int rondactual = 1;
    private int turno = 1;
    private int daniorecibido=0;


    private ArrayList<Button> listacartas,listacartasrival;
    private ArrayList<String> cartasusadas;

    private String oponente;
    private Player jugador;
    private Paquete pack;

    private int danioataque = 0;

    @FXML
    private Label nickname,nickoponente,pvida,pmana,pvidarival,pmanarival,numronda,idanio;
    @FXML
    private Label hechizo,esbirro,secreto;
    @FXML
    private Circle indicaturnorival,indicaturno;
    @FXML
    private Button ideck;
    @FXML
    private AnchorPane principal;
    @FXML
    private MenuButton menuButton;

    /**
     * Uploader se encarga de al iniciar la ventana se generén las listas que contienen las cartas del deck y las de la mano
     * para empezar el juego
     */
    private void Uploader(){
        this.listacartas = new ArrayList<>();//lista con los botones que conforman la mano
        this.listacartasrival = new ArrayList<>();//lista de botones de la mano rival

        try {
            RefreshDeck();

            Handnode caux = hand.getFirts();
            Handnode raux = rivalhand.getFirts();
            int contador = 0;
            int contar = 0;

            int posx = 170;
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
    public void setVista(Stage vista) { this.vista = vista; }

    /***
     * M'etodo que se encarga de refrescar la imagen de la carta que está en la primera posición del deck en pantalla
     */
    public void RefreshDeck(){
        Cards firts = null;
        try {
            firts = deck.Top();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image img = new Image(firts.getImagen());
        ImageView view = new ImageView(img);
        ideck.setText(firts.getID());//le da la id como texto para luego poder buscar la carta por este mismo
        ideck.setGraphic(view);
    }

    /**
     * Método que se encarga de agregar un item al historial cada ronda que pasa
     */
    public void History(int ronda){
        TextArea datos = new TextArea();
        datos.setEditable(false);
        datos.appendText("Jugador"+"\n");
        datos.appendText("Ronda:"+ronda+"\n");
        datos.appendText("Daño recibido en total:"+daniorecibido+"\n");
        datos.appendText("Daño recibido en la ronda:"+servidor.getState().getDanioeviado()+"\n");
        datos.appendText("Cartas utilizadas en la ronda:"+"\n");
        for (String carta:cartasusadas) {
            datos.appendText(carta+"\n");
        }

        Platform.runLater(()->listaHistory.add(datos,ronda,principal));
    }

    /***
     * Método que se usará por cada carta al ser presionada
     *
     */

    public  void AccionCarta(Button boton , Cards carta){

        if(jugador.getMana()>carta.getCoste()){
            double posx = boton.getLayoutX();//obtiene la posición del boton anterior
            cartasusadas.add(carta.getID());//añade la carta a la lista de cartas usadas
            listacartas.remove(boton);//remueve el boton de la pantalla
            principal.getChildren().remove(boton);//remueve el boton de la pantalla

            try {
                if(!deck.isEmpty()){
                    hand.Insert(deck.Top());//obtiene la primera carta del Deck
                    deck.Remove(deck.Top().getID());

                    Button cartanueva = hand.getFirts().getCarta().GenerateButton();

                    listacartas.add(cartanueva);//añade la nueva carta a la lista que contiene los botones de las cartas
                    cartanueva.setLayoutX(posx);//obtiene la posición del boton anterior
                    cartanueva.setLayoutY(669);
                    cartanueva.setOnMouseClicked(mouseEvent -> AccionCarta(cartanueva,hand.getFirts().getCarta()));//le dá la función a realizar cuando se use

                    principal.getChildren().add(cartanueva);//añade el boton a la pantalla
                    RefreshDeck();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ALERTA!");
                alert.setHeaderText(null);
                alert.setContentText("No tiene cartas en el Deck");
                alert.showAndWait();
            }
            Eventselector(carta);

            int mana = jugador.getMana();
            jugador.setMana(mana-carta.getCoste());
            pmana.setText(Integer.toString(jugador.getMana()));

            this.pack.setJugador(jugador);
            //hand.Remove(carta.getID());//se saca la carta de la mano
            Notificador();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//Aviso que se muestra si no se tiene suficiente mana
            alert.setTitle("ALERTA!");
            alert.setHeaderText(null);
            alert.setContentText("No tienes suficiente mana!");
            alert.showAndWait();
        }
    }


    /***
     * Método que indica cuál eventos sucederá según el tipo de la carta y su ID
     */
    public void Eventselector(Cards carta){
        String tipo = carta.getTipo();
        //String Id = carta.getID();
        int danio = carta.getDamage();
        danioataque+=danio;
        Platform.runLater(()->idanio.setText(Integer.toString(danioataque)));

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
     * Método encargado de cerrar el programa una vez la vida llegue a 0.
     */
    public void CheckLife(){
        if(jugador.getLife()<0){//si tiene la vida en negativo o es cero se cierra el progrma y se acaba la partida
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//alerta que slata al perder
            alert.setTitle("Game Over");
            alert.setHeaderText("GRACIAS POR JUGAR");
            alert.setContentText("ADIOS");

            ButtonType ok = new ButtonType("Ok");
            alert.getButtonTypes().setAll(ok);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK){
                this.pack.setEstado(false);
                Notificador();
                vista.close();
                Platform.exit();
                System.exit(0);
            }

        }
    }


    /**
     * Su función es la de correr el método encargado de dar las cartas iniciales y formar el deck
     *
     */
    public void Start() {
        Uploader();
        pvida.setText(Integer.toString(jugador.getLife()));
        pmana.setText(Integer.toString(jugador.getMana()));
        numronda.setText(Integer.toString(1));
        cartasusadas = new ArrayList<>();
        listaHistory = new ListaHistory();
        principal.getChildren().add(listaHistory.getMenu());

        indicaturnorival.setFill(Color.GREEN);
        indicaturno.setFill(Color.RED);
    }

    /***
     * Función que recibe los cambios y los refleja graficamente.
     */
    @Override
    public void update() {
        if(servidor.getState().getEstado()==false){
            CheckLife();
        }
        jugador.setLife(jugador.getLife()-servidor.getState().getDanioeviado());//le resta el daño enviado por el oponente a la vida actual
        daniorecibido+=servidor.getState().getDanioeviado();//suma el daño que se recibio al contador de daño

        Platform.runLater(()->{//se encarga de actualizar la información que se encuentra en pantalla
            CheckLife();
            pvida.setText(Integer.toString(jugador.getLife()));
            pmanarival.setText(Integer.toString(servidor.getState().getJugador().getMana()));
            pvidarival.setText(Integer.toString(servidor.getState().getJugador().getLife()));
            numronda.setText(Integer.toString(servidor.getState().getRonda()));
        });//genera el cambio visual de la vida y los demás parametros

        //indica que se cambio la ronda
        if(servidor.getState().getTurno()%2==0){//si el turno es par
            indicaturnorival.setFill(Color.RED);
            indicaturno.setFill(Color.GREEN);
            for (Button bot:listacartas) {
                bot.setDisable(false);
            }
            this.pack.setDanioeviado(danioataque);// envia el daño generado en la ronda
            Platform.runLater(()->pvidarival.setText(Integer.toString(servidor.getState().getJugador().getLife()-danioataque)));
            danioataque=0;
            Platform.runLater(()-> {
                idanio.setText(Integer.toString(danioataque));
            });

        }else{//si el turno no es par
            indicaturnorival.setFill(Color.GREEN);
            indicaturno.setFill(Color.RED);
            for (Button bot:listacartas) {
                bot.setDisable(true);
            }//desabilita los botones si no está en turno
            int  mana = jugador.getMana();//se agrega el 25% del mana total
            if(mana+50>200){
                jugador.setMana(200);
            }else{
                jugador.setMana(mana+50);
            }
            Platform.runLater(()->pmana.setText(Integer.toString(jugador.getMana())));//cambia la informacion dle mana en pantalla
        }
        if(rondactual!=servidor.getState().getRonda()){
            History(rondactual);//añade una la informacion de la ronda al historial
            cartasusadas = new ArrayList<>();//resetea la lista de las cartas usadas
            rondactual=servidor.getState().getRonda();
        }
        this.pack.setJugador(jugador);
        Notificador();
    }
}
