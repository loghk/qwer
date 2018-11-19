package Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    public static int count = 0;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println("Server Start!");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("user no:"+(++count)+" client in");
            new Thread(new Serversup(socket)).start();
            }
        }
    }



class Serversup implements Runnable{
    private Socket socket;
    private BufferedReader br = null;
    public Serversup(Socket socket) throws IOException{
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        String temp;
        try (
                OutputStream os = socket.getOutputStream();
                PrintStream ps = new PrintStream(os);
                ){
            while ((temp = Read())!=null){
                ps.println(temp);
            }
        }catch (IOException e){
            System.out.println("Client has closed");
        }
    }

    private String Read(){
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Client has closed");
        }
        return null;
    }
}