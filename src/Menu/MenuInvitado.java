package Menu;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.FileInputStream;


public class MenuInvitado extends Application{
    Stage ventana = new Stage();

    public void Comenzar(){
        try {
            start(ventana);
        }catch (Exception e){
            System.out.println("Ocurrio un error en la ventana de invitado");
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("Seleccione la Dirección");
        stage.setHeight(400);
        stage.setWidth(400);

        Image background = new Image(new FileInputStream("Images/Backgrounds/invitado.png"));//background
        ImageView fondo = new ImageView(background);

        //Componentes------------------
        //labels------------------------------------
        Label indicador1 = new Label("Ingrese la IP");
        indicador1.setTextFill(Color.web("#ffffff"));
        indicador1.setLayoutX(50);
        indicador1.setLayoutY(50);

        Label indicador2 = new Label("Ingrese el puerto");
        indicador2.setTextFill(Color.web("#ffffff"));
        indicador2.setLayoutX(230);
        indicador2.setLayoutY(50);
        //-----------------------------------------
        //Inputs-------------------------
        TextField IP = new TextField();
        IP.setLayoutX(30);
        IP.setLayoutY(80);

        TextField puerto = new TextField();
        puerto.setLayoutX(220);
        puerto.setLayoutY(80);
        //------------------------------
        //Botones-----------------------
        Button conectar = new Button("CONECTAR");
        conectar.setLayoutX(160);
        conectar.setLayoutY(280);
        conectar.setOnAction(e->{
            System.out.println(IP.getText());
        });
        //----------------------------

        Group root = new Group();//grupo de elementos
        root.getChildren().addAll(fondo,conectar,indicador1,indicador2,IP,puerto);

        Scene escena = new Scene(root);

        stage.setScene(escena);
        stage.show();

    }
}
