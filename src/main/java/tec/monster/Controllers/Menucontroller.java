package tec.monster.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tec.monster.Connections.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Menucontroller implements Initializable {
    private Stage viewInvitado;
    private Stage viewAnfitrion;
    /**
     *
     * Clase que maneja los eventos y acciones realizadas en la ventana de la clase tec.monster.Graphics.MenuInicial
     *es la encargada de abrir los diferentes modos de acceso al juego ya sea como anfitrión o invitado.
     * <p>
     *Esta clase implementa la interfaz Initializable y sobreescribe el método Initialize que permite ejecutar código
     * al iniciar una instancia de la clase.
     * <p>
     * Posee dos atributos de la clase Button de javafx.scene.control.Button, estos son los mostrados en pantalla.
     *
     * @author Bryan
     * @since 1.0
     */
    @FXML
    Button binvitado,banfitrion;
    @FXML
    public TextField nickname;

    /***
     * Este método inicializa la ventana para que el invitado dijite la ip y e puerto al cual conectarse, creando un
     * nuevo objeto de tipo Stage luego asignandole a este un de objeto tipo Scene que contiene un objeto de tipo Parent con el
     * archivo FXML asociado.
     * @param e evento enviado por el boton al recibir un click
     * @throws IOException etecta si hubo algún error al utilizar el método load() que forma parte de la clase Parent
     *de JavaFX
     */
    @FXML
    private void ClickInvitado(ActionEvent e) throws IOException {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        //stage.hide();

        //cargando la vista de la segunda ventana y obteniendo el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("tec/monster/Invitadoview.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        Invitadocontroller invitadocont = loader.getController();
        this.viewInvitado= new Stage();
        this.viewInvitado.setScene(new Scene(root));
        invitadocont.setNickname(nickname.getText());
        this.viewInvitado.setResizable(false);
        this.viewInvitado.setTitle("Conexión");
        this.viewInvitado.show();
        this.viewInvitado.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                stage.show();
                Server servidor = invitadocont.getServer();
                servidor.close();
            }
        });
    }

    /**
     *Este método inicializa la ventana de el anfitrion, creando un
     * nuevo objeto de tipo Stage luego asignandole a este un de objeto tipo Scene que contiene un objeto de tipo Parent con el
     * archivo FXML asociado.
     * @throws IOException detecta si hubo algún error al utilizar el método load() que forma parte de la clase Parent
     * de JavaFX.
     */
    @FXML
    private void ClickAnfitrion(ActionEvent e) throws IOException {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        //stage.hide();

        FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("tec/monster/Anfitrionview.fxml"));
        AnchorPane root = (AnchorPane) loader2.load();

        Anfitrioncontroller anfitrioncont = loader2.getController();
        this.viewAnfitrion = new Stage();
        this.viewAnfitrion.setScene(new Scene(root));
        anfitrioncont.setNickname(nickname.getText());
        this.viewAnfitrion.setResizable(false);
        this.viewAnfitrion.setTitle("Conexión");
        this.viewAnfitrion.show();
        this.viewAnfitrion.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Server servidor = anfitrioncont.getServidor();
                servidor.close();
                stage.show();

            }
        });
    }
    public String getName(){
        return nickname.getText();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
