package tec.monster.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Menucontroller implements Initializable {
    /**
     *
     * Clase que maneja los eventos y acciones realizadas en la ventana de la clase MenuInicial
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
    private Button binvitado,banfitrion;

    @FXML
    /***
     * Este método inicializa la ventana para que el invitado dijite la ip y e puerto al cual conectarse, creando un
     * nuevo objeto de tipo Stage luego asignandole a este un de objeto tipo Scene que contiene un objeto de tipo Parent con el
     * archivo FXML asociado.
     * @param evento este es enviado por el boton al recibir un click
     * @throws IOException etecta si hubo algún error al utilizar el método load() que forma parte de la clase Parent
     *de JavaFX
     */
    private void ClickInvitado(ActionEvent evento) throws IOException {

        //cargando la vista de la segunda ventana y obteniendo el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tec/monster/graphics/views/Invitadoview.fxml"));
        Parent root = loader.load();

        Invitadocontroller invitadocont = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Conexión");
        stage.show();
    }

    /**
     *Este método inicializa la ventana de el anfitrion, creando un
     * nuevo objeto de tipo Stage luego asignandole a este un de objeto tipo Scene que contiene un objeto de tipo Parent con el
     * archivo FXML asociado.
     * @param evento es la acción que envía el banfitrion al controlador.
     * @throws IOException detecta si hubo algún error al utilizar el método load() que forma parte de la clase Parent
     * de JavaFX.
     */
    @FXML
    private void ClickAnfitrion(ActionEvent evento) throws IOException {
        //cargando la vista de la segunda ventana y obteniendo el controlador
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/tec/monster/graphics/views/Anfitrionview.fxml"));
        Parent root = loader2.load();

        Anfitrioncontroller anfitrioncont = loader2.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Conexión");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
