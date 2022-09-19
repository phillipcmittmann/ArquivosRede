import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCliente {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(5000)) {
            FileInputStream fileInputStream = new FileInputStream("NewFile2.txt");
            byte[] buffer = fileInputStream.readAllBytes();

            DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 5000);

            socket.send(sendPacket);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
