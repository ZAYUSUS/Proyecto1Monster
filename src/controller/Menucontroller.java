package controller;

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
    @FXML
    private Button binvitado,banfitrion;

    @FXML
    private void ClickInvitado(ActionEvent evento) throws IOException {

        //cargando la vista de la segunda ventana y obteniendo el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Invitadoview.fxml"));
        Parent root = loader.load();

        Invitadocontroller invitadocont = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Conexión");
        stage.show();


    }
    @FXML
    private void ClickAnfitrion(ActionEvent evento) throws IOException {
        //cargando la vista de la segunda ventana y obteniendo el controlador
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/views/Anfitrionview.fxml"));
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
