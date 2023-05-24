import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {

        //Flujo entrada estandar
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();
        byte [] enviados = new byte[1024];
        byte [] recibidos = new byte[1024];

       // Datos del Servidor al que le hacemos el envio
        InetAddress IPServer = InetAddress.getLocalHost(); //localhost
        int puerto = 9876; //puerto que está a la escucha

        //Introducir datos por teclado
        System.out.println("Introduce un mensaje: ");
        String cadena = in.readLine();
        enviados = cadena.getBytes(); //llenamos enviados con el contenido de cadena

        //Envio de datagrama al Server
        System.out.println("Enviando " + enviados.length + " bytes al Server");
        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServer, puerto);
        clientSocket.send(envio);

        //Recibo de respuesta del servidor
        DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        System.out.println("Esperando respuesta del Server...");
        clientSocket.receive(recibo);
        System.out.println("Ha llegado algo!");
        String cadenaMayus =new String(recibo.getData()); //Unboxing del contenido recibido

        //Muestra info del paquete recibido
        int puertoOrigen = recibo.getPort();
        InetAddress IPorigen = recibo.getAddress();
        System.out.println("Se han recibido " + recibo.getLength() + " bytes");
        System.out.println("Procedentes de IP" + IPorigen  + " puerto " + puertoOrigen);
        System.out.println("El contenido es el siguiente: " + cadenaMayus);

        //close socket
        clientSocket.close();
    }

}
