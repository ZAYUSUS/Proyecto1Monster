package tec.monster.connections;

import tec.monster.GameEssentials.Cards;

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
    public Client(int puerto,String host,String usuario, int puertosalida){
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.puertoSalida= puertosalida;
    }

    @Override
    public void run() {
        ObjectOutputStream output;
        ObjectInputStream input;

        try {
            Socket conector = new Socket(host,puerto);

            Cards carta = new Cards();

            carta.setUsuario(this.usuario);
            carta.setPuerto(this.puertoSalida);


            output = new ObjectOutputStream(conector.getOutputStream());
            output.writeObject(carta);

            System.out.println("Objeto enviado");

            conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
