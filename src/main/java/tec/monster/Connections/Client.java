package tec.monster.Connections;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 *
 *
 * @author Bryan
 */

public class Client implements Runnable {
    final String host;
    private String usuario;
    private int puerto;
    private int puertoSalida;
    private Paquete pack;

    public Client(Paquete packet){
        pack = packet;
        this.host = packet.getIp();
        this.usuario = packet.getUsuario();
        this.puerto = packet.getPuerto();
        this.puertoSalida = packet.getPuerto_salida();
    }
    @Override
    public void run() {
        ObjectOutputStream output;

        try {
            Socket conector = new Socket(host,puerto);


            output = new ObjectOutputStream(conector.getOutputStream());
            output.writeObject(pack);

            System.out.println("Objeto enviado");

            conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
