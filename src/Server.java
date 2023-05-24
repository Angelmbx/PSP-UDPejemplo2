import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) throws IOException {

        int port = 9876;
        //datagram que sera recibido
        DatagramSocket serverSocket = new DatagramSocket(port);

        byte [] recibidos;
        byte [] enviados= new byte[1024];
        String cadena;

        System.out.println("SERVER set on PORT: " + serverSocket.getLocalPort());

       while (true) {

           System.out.println("Esperando datagrama...");

           //Recibo de datagrama y muestra info
           recibidos = new byte[1024];
           DatagramPacket paquetRecibido = new DatagramPacket(recibidos, recibidos.length);
           serverSocket.receive(paquetRecibido);
           System.out.println("Datagrama recibido!");
           cadena = new String(paquetRecibido.getData());
           InetAddress iPOrigen = paquetRecibido.getAddress();
           int pueroOrigen = paquetRecibido.getPort();
           System.out.println("IP ORIGEN: " + iPOrigen + " Puerto ORIGEN: " + pueroOrigen);
           System.out.println("Mensaje recibido: " + cadena.trim());

           //Convertir a mayúscula
           String cadenaMayus = cadena.trim().toUpperCase();
           enviados = cadenaMayus.getBytes(); //meto la nueva cadena en el bufer de salida

           //Envio de cadena mayúsculas
           //obtengo la direccion IP y el Puerto al que enviar del paquete que recibo.
           DatagramPacket paquetEnviado = new DatagramPacket(enviados, enviados.length, iPOrigen, pueroOrigen);
           serverSocket.send(paquetEnviado);

           //salir del bucle
           if(cadena.trim().equals("*")) break;

       }

       //close socket
        serverSocket.close();

    }

}