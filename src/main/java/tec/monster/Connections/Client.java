package tec.monster.Connections;

import tec.monster.Game.Cards;

import java.io.IOException;
import java.io.ObjectInputStream;
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

    public Client(int puerto, String host, String usuario, int puertosalida){
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.puertoSalida= puertosalida;
    }

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
