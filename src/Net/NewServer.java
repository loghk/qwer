package Net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NewServer {
    public static Map<Socket,Integer> sockets_id = Collections.synchronizedMap(new HashMap<>());
    public static int count = 0;
    public static StringBuffer Serverinform = new StringBuffer();
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("Server start!");
        while (true) {
            Socket socket = serverSocket.accept();
            count++;
            NewServer.sockets_id.put(socket, count);
            System.out.println("no:"+sockets_id.get(socket)+"  Client in!");
            StringBuilder temp = new StringBuilder();
            for (Socket socket1 : NewServer.sockets_id.keySet()){
                temp = temp.append(NewServer.sockets_id.get(socket1)+" ");
            }
            NewServer.Serverinform.replace(0, NewServer.Serverinform.length(), "@#*"+temp.toString()+"*#@");
            System.out.println(NewServer.Serverinform);
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
    public void run(){
        SendAll();
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
        SendAll();
        Close();
    }
    //读
    private String Read(){

        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("no "+NewServer.sockets_id.get(this.socket)+"  Client exit!");
            NewServer.sockets_id.remove(this.socket);
            StringBuilder temp = new StringBuilder();
            for (Socket socket1 : NewServer.sockets_id.keySet()){
                temp = temp.append(NewServer.sockets_id.get(socket1)+" ");
            }
            NewServer.Serverinform.replace(0, NewServer.Serverinform.length(), "@#*"+temp.toString()+"*#@");
            System.out.println(NewServer.Serverinform);
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

    private void SendAll(){
        PrintStream printStream = null;
        for (Socket socket : NewServer.sockets_id.keySet()){
            try {
                printStream = new PrintStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            printStream.println(NewServer.Serverinform.toString());
        }
    }
}