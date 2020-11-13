module tec.monster {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;

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
    opens tec.monster.HandStructure;
    exports tec.monster.HandStructure;
}