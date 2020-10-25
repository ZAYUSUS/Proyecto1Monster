package Menu;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;


public class MenuInicial extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //modalitys.NONE,WINDWOW_MODAL,APPLICATION_MODAL
        stage.setTitle("Monster");
        stage.setWidth(800);
        stage.setHeight(820);
        stage.setResizable(false);

        Image background = new Image(new FileInputStream("Images/Backgrounds/fprincipal.png"));
        ImageView fondo = new ImageView(background);

        //Componentes----------------
        Button binvitado= new Button("Invitado");
        binvitado.setPrefSize(100,30);
        binvitado.setLayoutX(150);
        binvitado.setLayoutY(480);
        //-----------
        binvitado.setOnAction(e->{
            new MenuInvitado().Comenzar();
        });

        Button banfitrion= new Button("Anfitrion");
        banfitrion.setPrefSize(100,30);
        banfitrion.setLayoutX(550);
        banfitrion.setLayoutY(480);
        //-------------
        banfitrion.setOnAction(e->{
            new MenuAnfitrion().Inicio();
        });

        Group root = new Group();//grupo de los componentes
        root.getChildren().addAll(fondo,binvitado,banfitrion);//da los nodos al grupo padre

        Scene scene1 =  new Scene(root);

        stage.setScene(scene1);//le da los componentes a la ventana
        stage.show();
    }
}
