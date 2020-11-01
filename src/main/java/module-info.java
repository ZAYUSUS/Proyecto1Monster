module tec.monster {
    requires javafx.controls;
    requires javafx.fxml;

    opens tec.monster to javafx.fxml;
    exports tec.monster;
    opens tec.monster.Controllers to javafx.fxml;
    exports tec.monster.Controllers;
    opens tec.monster.Observers to javafx.fxml;
    exports tec.monster.Observers;
    opens tec.monster.Connections to javafx.fxml;
    exports tec.monster.Connections;
    opens tec.monster.Game to javafx.fxml;
    exports tec.monster.Game;
}