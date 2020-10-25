package Menu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MenuAnfitrion extends Application {
    Stage ventana = new Stage();
    public void Inicio(){
        try {
            start(ventana);
        }catch (Exception e){
            System.out.println("Ocurrio un error en la ventana de anfitrion");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("Espere");
        stage.setHeight(400);
        stage.setWidth(400);

        Image background = new Image(new FileInputStream("Images/Backgrounds/espera.gif"));//background
        ImageView fondo = new ImageView(background);

        //Componentes------------------

        Group root = new Group();//grupo de elementos
        root.getChildren().addAll(fondo);

        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
}
