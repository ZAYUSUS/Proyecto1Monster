package tec.monster.connections;

import tec.monster.GameEssentials.Cards;

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
    public Client(int puerto,String host,String usuario){
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
    }

    @Override
    public void run() {
        ObjectOutputStream output;

        try {
            Socket conector = new Socket(host,puerto);

            Cards carta = new Cards();

            carta.setUsuario(this.usuario);


            output = new ObjectOutputStream(conector.getOutputStream());
            output.writeObject(carta);

            System.out.println("Objeto enviado");

            conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
