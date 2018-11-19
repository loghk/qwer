package Net;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String temp = new String();
        Socket socket = new Socket("localhost",8088);
        new Thread(new Clientsup(socket)).start();
        while (true){
            temp = scanner.next();
            try {
                OutputStream os = socket.getOutputStream();
                PrintStream ps = new PrintStream(os, true);
                ps.println(temp);
                if (temp.equals("exit")){
                    break;
                }
            }catch (IOException e){
                System.out.println("client exit");
            }
        }
        socket.close();
    }
}

class Clientsup implements Runnable{
    private Socket socket;
    private BufferedReader br = null;
    public Clientsup(Socket socket) throws IOException{
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true)
                System.out.println(br.readLine());
        }catch (IOException e){

        }
    }
}
