package tec.monster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane root = FXMLLoader.load(getClass().getResource("Menuview.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Monster");
        stage.setResizable(false);
        stage.show();
        System.out.println("INICIE");
    }

}

