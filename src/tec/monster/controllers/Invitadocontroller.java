package tec.monster.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tec.monster.Observers.Observer;
import tec.monster.connections.Client;
import tec.monster.connections.Server;

import java.io.IOException;
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
public class Invitadocontroller extends Observer implements Initializable {
    private Server servidor;
    private Stage Gameview;
    @FXML
    private Button botonconectar,botoniniciar;
    @FXML
    private TextField eIP,ePuerto,eNombre;
    @FXML
    private Label puertoCliente;

    /**
     * Método encargado de instanciar un objeto de la clase Cliente para conectar por medio de un socket al servidor,
     * la dirección a la que se va a conectar es indicada por el usuario mediante los objetos TextField de que
     * están ubicados en la ventana Invitadoview.
     */
    @FXML
    public void Conectar(ActionEvent e){
        int puerto = Integer.parseInt(this.ePuerto.getText());
        Client cliente = new Client(puerto,this.eIP.getText(),this.eNombre.getText(),servidor.getPort());
        Thread hilo = new Thread(cliente);
        hilo.start();
    }

    public Server getServer(){ return servidor;}

    @FXML
    private void Inicio(ActionEvent event) throws IOException {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/tec/monster/graphics/views/Gameview.fxml"));
        Parent root = loader2.load();

        Gameviewcontroller gamecont = loader2.getController();
        Gameview = new Stage();
        Gameview.initModality(Modality.APPLICATION_MODAL);
        Gameview.setScene(new Scene(root));
        Gameview.setResizable(false);
        Gameview.setTitle("Conexión");
        Gameview.show();
        gamecont.setServer(servidor);
        Gameview.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                servidor.close();
                stage.show();
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eIP.setText("127.0.0.1");
        ePuerto.setText("5000");
        botoniniciar.setVisible(false);

        this.servidor = new Server();
        this.subject = servidor;
        this.subject.add(this);
        Thread hilo = new Thread(servidor);
        hilo.start();

        this.puertoCliente.setText("Puerto Cliente : " + Integer.toString(servidor.getPort()));

    }

    @Override
    public void update() {
        botonconectar.setVisible(false);
        botoniniciar.setVisible(true);

    }
}