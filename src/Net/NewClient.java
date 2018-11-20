package Net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NewClient {
    public static void main(String[] args) throws IOException {
        String temp = new String();
        Socket socket = null;
        try {
            socket = new Socket("localhost",6666);
        } catch (IOException e) {
            e.printStackTrace();
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
    public NewClientsup(Socket socket) throws IOException{
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
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