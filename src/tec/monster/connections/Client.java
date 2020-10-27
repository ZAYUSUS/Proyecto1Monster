package tec.monster.connections;

import tec.monster.GameEssentials.Cards;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    final String host;
    int puerto;
    public Client(int puerto,String host){
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ObjectOutputStream output;
        ObjectInputStream input;

        try {
            Socket conector = new Socket(host,puerto);

            Cards cartas = new Cards();

            cartas.setColor("Negro");
            cartas.setEfecto("explosion");
            cartas.setTipo("hechizo");

            output = new ObjectOutputStream(conector.getOutputStream());
            output.writeObject(cartas);

            System.out.println("Objeto enviado");

            conector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
