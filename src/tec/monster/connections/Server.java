package tec.monster.connections;
import tec.monster.GameEssentials.Cards;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Server extends Observable implements Runnable {
    int port ;
    Cards carta;

    public Server (){
        this.port = Port();
    }

    public int Port(){
        int p = 0;
        for (int ports = 5000; ports<6000; ports++){
            try{
                ServerSocket pruebaServer = new ServerSocket(ports);
                pruebaServer.close();
                p = ports;
                break;
            }catch (UnknownHostException e){
                System.out.println("NO se reconoce el host");
            }catch (IOException e1){
                System.out.println("Problema con el servidor");
            }
        }
        return p;
    }
    public int getPort(){
        return this.port;
    }
    public Cards getCarta(){ return this.carta;}

    @Override
    public void run() {
        ServerSocket servidor = null;
        ObjectInputStream input;


        Socket conexion = null;
        try {
            System.out.println("Servidor Iniciado");
            servidor = new ServerSocket(port);

            Cards cartas;

            while (true){
                conexion = servidor.accept();

                input = new ObjectInputStream(conexion.getInputStream());

                cartas = (Cards) input.readObject();
                carta = cartas;

                System.out.println(cartas.getColor());
                System.out.println(cartas.getTipo());
                System.out.println(cartas.getEfecto());

                this.setChanged();
                this.notifyObservers(cartas);
                this.clearChanged();

                conexion.close();
                System.out.println("Cliente desconectado");
                }
            }catch (IOException a) {
            System.out.println("Problema en el servidor");
            } catch (Exception e) {
            System.out.println("Problema en el server");
            }
    }
}
