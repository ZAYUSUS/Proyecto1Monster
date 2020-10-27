package tec.monster.connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    final ObjectInputStream deo;
    final ObjectOutputStream dso;
    final Socket conexion;
    public ClientHandler(Socket conector, ObjectInputStream datosentrada , ObjectOutputStream datossalida){
        this.deo = datosentrada;
        this.dso = datossalida;
        this.conexion = conector;
    }

    @Override
    public void run() {

    }
}
