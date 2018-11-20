package Net;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class NewServer {
    public static Map<Socket,Integer> sockets_id = Collections.synchronizedMap(new HashMap<>());
    public static int count = 0;
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server start!");
        while (true) {
            Socket socket = serverSocket.accept();
            count++;
            NewServer.sockets_id.put(socket, count);
            System.out.println("no:"+sockets_id.get(socket)+"  Client in!");
            new Thread(new NewServersup(socket)).start();
        }
    }
}

class NewServersup implements Runnable {
    String temp;
    private Socket socket;
    private BufferedReader br = null;
    public NewServersup(Socket socket) throws IOException{
        this.socket = socket;
        br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    //读写
    @Override
    public void run() {
        while ((temp = Read())!=null) {

            for (Socket s : NewServer.sockets_id.keySet()){
                try {
                    OutputStream os = s.getOutputStream();
                    PrintStream ps = new PrintStream(os);
                    if (s != this.socket)
                    ps.println("no "+NewServer.sockets_id.get(this.socket)+" : "+temp);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
        Close();
    }
    //读
    private String Read(){
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("no "+NewServer.sockets_id.get(this.socket)+"  Client exit!");
            NewServer.sockets_id.remove(this.socket);
            return null;
        }
    }

    private void Close(){
        try {
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}