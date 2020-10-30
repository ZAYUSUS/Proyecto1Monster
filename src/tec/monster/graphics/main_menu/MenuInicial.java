package tec.monster.graphics.main_menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MenuInicial extends Application {
    public static Stage firtsWindow;

    @Override
    public void start(Stage stage) throws Exception {
        this.firtsWindow = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/tec/monster/graphics/views/Menuview.fxml"));
        stage.setTitle("Monster");
        stage.setResizable(false);
        Scene window = new Scene(root);

        stage.setScene(window);
        stage.show();
    }
}