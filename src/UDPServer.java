import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static DataInputStream dataInputStream = null;

    public static void main (String args[]) throws Exception {
        try(DatagramSocket serverSocket = new DatagramSocket(5000)) {
            System.out.println("listening to port:5000");

            DatagramPacket request = new DatagramPacket(new byte[1], 1);

            serverSocket.receive(request);

            FileOutputStream fileOutputStream = new FileOutputStream("./recebidos/NewFile2.txt");
            byte[] buffer = request.getData();

            long size = dataInputStream.readLong();

            int bytes = 0;
            while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
                fileOutputStream.write(buffer,0,bytes);
                size -= bytes;
            }

            InetAddress clientAddress = request.getAddress();

            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, 5000);

            serverSocket.send(response);
        }
    }
}
