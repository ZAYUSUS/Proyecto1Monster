package tec.monster.History;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import tec.monster.Connections.Paquete;

public class ListaHistory {
    private NodoHistory first;
    private NodoHistory data;
    private MenuButton menu ;

    public ListaHistory() {
        first = null;
        data = null;
        menu = new MenuButton("HISTORY");
        menu.setStyle("-fx-background-color: #b88112;");
        menu.setLayoutX(27);
        menu.setLayoutY(250);
    }

    public void add(TextArea dato,int ronda) {
        NodoHistory nuevo = new NodoHistory(dato,ronda);

        if (first == null) {//si la lista esta vacia
            nuevo.setDatos(dato);
            first = nuevo;
        } else {//si ya tiene elementos
            NodoHistory prev = first;
            NodoHistory previous = first.getPrevious();

            while (previous != null) {
                prev = previous;
                previous = previous.getNext();
            }

            if(previous == null){
                nuevo.setDatos(dato);
                nuevo.setPrevious(prev);
                nuevo.setNext(null);
                prev.setNext(nuevo);
            }
        }
        Platform.runLater(()->menu.getItems().add(nuevo.getVisual()));
    }
    public MenuButton Agregar(){
        NodoHistory p = first;
        if (first == null) {
            System.out.println("No hay elementos en la lista");
        } else {
            while (p != null) {
                MenuItem item = p.getVisual();
                Platform.runLater(()->menu.getItems().add(item));
                p = p.getNext();
            }
        }
        return  menu;
    }
    public MenuButton getMenu(){
        return  menu;
    }
}
