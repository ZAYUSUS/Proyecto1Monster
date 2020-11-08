package tec.monster.History;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;


/**
 * Es el nodo especializado de la lista que contiene la información de la partida.
 */

public class NodoHistory {

    private NodoHistory next;
    private NodoHistory previous;
    private TextArea datos;
    private MenuItem visual;

    public NodoHistory(TextArea datos ,int ronda){
        visual = new MenuItem("Ronda "+ronda);
        this.datos = datos;
        next = null;
        previous = null;

        visual.setOnAction(e->{
            Pane panel = new Pane();
            datos.setLayoutX(0);
            datos.setLayoutY(0);
            datos.setPrefSize(200,200);
            panel.getChildren().add(datos);
        });
    }


    public TextArea getDatos() {
        return datos;
    }
    public NodoHistory getNext(){
        return next;
    }
    public NodoHistory getPrevious(){
        return previous;
    }

    public MenuItem getVisual() {
        return visual;
    }

    public void setNext(NodoHistory sig){
        next = sig;
    }
    public void setPrevious(NodoHistory prev){
        previous = prev;
    }

    public void setDatos(TextArea datos) {
        this.datos = datos;
    }

    public void setVisual(MenuItem visual) {
        this.visual = visual;
    }
}
