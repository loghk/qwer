package Net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NewClient {
    public static void main(String[] args){
        String temp = new String();
        Socket socket = null;
        try {
            socket = new Socket("localhost",6666);
        } catch (IOException e) {
            System.out.println("server is not exist");
            return;
        }
        new Thread(new NewClientsup(socket)).start();
        Scanner scanner = new Scanner(System.in);
        try (
                OutputStream os = socket.getOutputStream();
                PrintStream ps = new PrintStream(os);
                ){
            while (true){
                   temp = scanner.next();
                   ps.println(temp);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class NewClientsup implements Runnable{
    private Socket socket;
    private BufferedReader br;
    private String temp;
    public NewClientsup(Socket socket){
        this.socket = socket;
        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while ((temp = br.readLine())!=null){
                System.out.println(temp);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}