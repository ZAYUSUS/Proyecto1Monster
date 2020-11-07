package tec.monster.Connections;
import tec.monster.Game.Cards;
import tec.monster.Observers.Subject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase encargada de crear el Serversocket para la conexión entre el Anfitrión y el invitado.
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
 * @since 1.0
 */
public class Server extends Subject implements Runnable {
    private String user;
    private int port ;
    private Paquete paquete;
    private ServerSocket servidor = null;

    /***
     * Al Server ser instanciado se le otorga un numero de un puerto disponible para crear el ServerSocket dado por
     * el método Port().
     */
    public Server (){
        this.port = Port();
    }

    /**
     * Método encargado de seleccionar un puerto que se encuentre disponible para el ServerSocket, este realiza la
     * búsqueda intentando conectar con diferentes números de puertos en una conexión temporal y luego cierra dicha conexión
     * y el puerto al que se pudo conectar se guarda en la variable p.
     *
     * @return p es un integer que guarda el numero de puerto disponible
     * @throws UnknownHostException se crea al encontrar un puerto y no se conoce el Host.
     * @throws IOException se lanza si sucede un problema con los sockets, por ejemplo si está ocupado.
     */
    private int Port(){
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

    /***
     * Método para obtener el puerto al que está escuchando el ServerSocket
     *
     * @return port es una variable de tipo integer que es igual al puerto al que está escuchando el Serversocket
     */
    public int getPort(){
        return this.port;
    }
    /***
     * Método para obtener el user 
     * @return user es una variable de tipo string que es igual al nombre del usuario al que está escuchando el Serversocket
     */
    public String getUser() {
        return user;
    }
    /***
     * Método para setear el user 
     * @param user es el user al cual se obtuvo en el métedo getUser
     */
    public void setUser(String user) {
        this.user = user;
    }
    /***
     * Método para cerrar la conexión con el servidor
     */
    public void close() {
        try{
            servidor.close();
        }catch (IOException a){
            System.out.println("Hubo un problema cerrando el servidor");
        }finally {
            System.out.println("Se cerro el servidor");
        }

    }

    /**
     * Crea el Serversocket y escucha en un puerto dado por el atributo port, luego crea un objeto de tipo inputstream que será el encargado
     * de guardar los objetos que sean recibidos y luego los deserializa para enviarlos a otra clase.
     * Todos los objetos son recibidos y enviados por medio del protocolo de Json.
     * <p>
     * Al recibir los objetos este método envía un aviso de cambio a todos los obserevadores vinculados.
     *
     * @throws IOException lanzada por un error al conectar con el socket o que un objeto vienen mal enpaquetado.
     *
     */
    @Override
    public void run() {
        ObjectInputStream input;
        ObjectOutputStream out;

        Socket conexion = null;
        try {
            System.out.println("Servidor Iniciado");
            servidor = new ServerSocket(port);

            while (true){
                conexion = servidor.accept();

                input = new ObjectInputStream(conexion.getInputStream());
                paquete = (Paquete) input.readObject();

                this.setState(paquete);

                conexion.close();
                System.out.println("Cliente desconectado");
            }
        }catch (IOException a) {
            System.out.println("Problema en el servidor "+ a);
        } catch (Exception e) {
            System.out.println("Problema grave server");
        }finally {
            System.out.println("se acabo el bloque del server");
        }

    }
}