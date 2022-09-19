import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("listening to port:8080");

            Socket clientSocket = serverSocket.accept();

            System.out.println(clientSocket+" connected.");

            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            receiveFile("/recebidos/NewFile1.txt");

            dataInputStream.close();
            dataOutputStream.close();

            clientSocket.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void receiveFile(String fileName) throws Exception{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();

        byte[] buffer = new byte[4*1024];

        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;
        }

        fileOutputStream.close();
    }
}