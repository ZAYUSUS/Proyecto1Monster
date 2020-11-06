package tec.monster.Connections;

import tec.monster.Game.Cards;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *Clase encargada de crear el Serversocket para la conexión entre el Anfitrión y el invitado.
 * La clase desenpaqueta objetos enviados por medio del protocolo de comunicación JSON y utilizando Jackson para
 * serializar y deserializar los objetos enviados y recibidos.
 * <p>
 * Esta clase hereda de la clase Observable que permite notificar a diferentes observadores sobre las acciones
 * que se realizan.
 * <p>
 * Implementa la interfaz Runnable que permite a las instancias creadas correr en segundo plano por medio de el
 * método Run().
 *
 *
 * @author Bryan
 */

public class Client implements Runnable {
    final String host;
    private String usuario;
    private int puerto;
    private int puertoSalida;

    public Client(int puerto, String host, String usuario, int puertosalida){
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.puertoSalida= puertosalida;
    }
    /**
     * Crea el Serversocket y escucha en un puerto dado por el atributo port, luego crea un objeto de tipo inputstream que será el encargado
     * de guardar los objetos que sean recibidos y luego los deserializa para enviarlos a otra clase.
     * Todos los objetos son recibidos y enviados por medio del protocolo de Json.
     * <p>
     * Al recibir los objetos este método envía un aviso de cambio a todos los obserevadores vinculados.
     *
     * @throws IOException lanzada por un error al conectar con el socket o que un objeto vienen mal enpaquetado.
     */
    @Override
    public void run() {
        ObjectOutputStream output;

        try {
            Socket conector = new Socket(host,puerto);

            Paquete paquete = new Paquete();

            paquete.setUsuario(this.usuario);
            paquete.setPuerto(this.puertoSalida);


            output = new ObjectOutputStream(conector.getOutputStream());
            output.writeObject(paquete);

            System.out.println("Objeto enviado");

            conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
