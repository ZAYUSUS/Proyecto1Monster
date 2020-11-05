package tec.monster.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tec.monster.Connections.Client;
import tec.monster.Connections.Server;
import tec.monster.Observers.Observer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase encargada de controlar las acciones realizadas en la ventana de el anfitrión,
 * esta crea la instancia del servidor y cambia la ventana para poder comenzar el juego al
 * haber encontrado un invitado que haya conectado con el server creado.
 * <p>
 *Esta clase implementa la interfaz Initializable y sobreescribe el método Initialize que permite ejecutar código
 *al iniciar una instancia de la clase. También implementa la interfaz Observer que permite recibir las actualizaciones de
 * la clase a la que se desee observar, en este caso será la clase servidor para saber cuándo se recibe información.
 *<p>
 *Posee como atributos un Server y por parte de la ventana a la que está asociado obtiene un objeto de tipo TextArea que es el
 * encargado de mostrar la información con respecto al server.
 *
 * @author Bryan
 * @since 1.0
 */

public class Anfitrioncontroller extends Observer implements Initializable{
    private Server servidor;
    private Stage Gameview;
    private String nickname;
    @FXML
    private Button conectar;
    @FXML
    private TextArea statusarea;

    /**
     *Método que abre la ventana del juego y envía una respuesta al cliente que envío la conexión
     * para unirse a la ventana de juego.
     */
    public void Clickconectar(javafx.event.ActionEvent actionEvent) throws IOException {

        Client cliente = new Client(servidor.getState().getPuerto(),"127.0.0.1",nickname,servidor.getPort());
        Thread hilo = new Thread(cliente);
        hilo.start();


        final Node source = (Node) actionEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("tec/monster/Gameview.fxml"));
        AnchorPane root = (AnchorPane) loader2.load();

        Gameviewcontroller gamecont = loader2.getController();
        Gameview = new Stage();
        Gameview.initModality(Modality.APPLICATION_MODAL);
        Gameview.setScene(new Scene(root));
        Gameview.setResizable(false);
        Gameview.setTitle("Conexión");
        gamecont.setServer(servidor);
        gamecont.setNickname(nickname);
        Gameview.show();
        Gameview.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                servidor.close();
                stage.show();
            }
        });
    }
    /**
     * Método que se encarfa de retornar el servidor que se está usando.
     *
     * @return servidor para poder acceder a los diferentes métodos del ServerSocket desde otras clases
     */
    public Server getServidor() {
        return servidor;
    }
    public void setNickname(String name){
        this.nickname = name;
    }

    /**
     *
     * Se sobreescribe por la implementación de la interfaz Initializable, este método se corre cuando se presione el boton banfitrion de la
     * ventana de la clase tec.monster.Graphics.MenuInicial.
     * Método encargado de instanciar la clase Server  e informar al statusarea el avance con la conexión
     * También crea un hilo para correr el servidor esto habilita que se pueda correr sin interrumpir el hilo principal.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conectar.setVisible(false);
        this.statusarea.appendText("Abriendo el server...\n");
        this.servidor = new Server();
        this.subject = servidor;
        this.subject.add(this);
        Thread hilo = new Thread(servidor);
        hilo.start();
        this.statusarea.appendText("Conectando...\n");
        this.statusarea.appendText("Servidor escuchando en el puerto: \n"+servidor.getPort()+"\n");
        this.statusarea.appendText("Esperando...\n");
    }

    /**
     * Clase sobreescrita por la implementación de la interfaz Observer, permite recibir la notificaión
     * de cambio del objeto observado, en este caso el objeto observado es la el servidor.
     */
    @Override
    public void update() {
        statusarea.appendText("Se encontró una conexión de "+servidor.getState().getUsuario()+"\n");
        conectar.setVisible(true);
    }


}
