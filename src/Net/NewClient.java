package Net;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class NewClient {
    public static void main(String[] args) {
        StringBuffer temp = new StringBuffer();
        TextArea get_message = new TextArea();
        TextArea peoplelist = new TextArea();
        Gui gui = new Gui(temp,get_message,peoplelist);
        gui.init();
        gui.run();
        Socket socket = null;
        try {
            socket = new Socket("localhost", 6666);
        } catch (IOException e) {
            System.out.println("server is not exist");
            return;
        }
        new Thread(new NewClientsup(socket,get_message,peoplelist)).start();
        try (
                OutputStream os = socket.getOutputStream();
                PrintStream ps = new PrintStream(os);
        ) {
            while (true) {
                if (!temp.toString().equals("")){
                    ps.println(temp);
                    temp = temp.replace(0, temp.length(), "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NewClientsup implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private String temp;
    TextArea get_message;
    TextArea peoplelist;

    public NewClientsup(Socket socket,TextArea textArea,TextArea peoplelist) {
        this.socket = socket;
        get_message = textArea;
        this.peoplelist = peoplelist;
        try {
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while ((temp = br.readLine()) != null) {
                if (IsInform(temp)){
                    temp = temp.substring(3, temp.length()-3);
                    String[] strings = temp.split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String s : strings){
                        sb.append(s+"\n");
                    }
                    peoplelist.setText(sb.toString());
                }
                else {
                    get_message.append(temp+"\r\n");
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean IsInform(String string){
        if (string.matches("@#[\\s\\S]*#@")){
            return true;
        }
        else {
            return false;
        }
    }
}

class Gui implements Runnable {
    StringBuffer stringBuffer;
    Frame frame = new Frame("聊天室");
    TextArea get_message = new TextArea();
    TextArea send_message = new TextArea();
    TextArea people_list = new TextArea();
    Button button = new Button("SEND");
    Panel panel = new Panel();

    public Gui(StringBuffer string,TextArea textArea,TextArea people_list) {
        this.stringBuffer = string;
        get_message = textArea;
        this.people_list = people_list;
    }

    public void init() {
        //frame.setSize(800, 800);
        frame.add(people_list, BorderLayout.EAST);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 50, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 400;
        gbl.setConstraints(get_message, gbc);
        panel.add(get_message);

        gbc.gridwidth = 300;
        gbl.setConstraints(send_message, gbc);
        panel.add(send_message);

        gbc.gridheight = 50;
        gbl.setConstraints(button, gbc);
        panel.add(button);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void run() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                stringBuffer = stringBuffer.append(send_message.getText());
                send_message.setText("");
            }
        });
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    stringBuffer = stringBuffer.append(send_message.getText());
                    send_message.setText("");
                }
            }
        });
        get_message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    stringBuffer = stringBuffer.append(send_message.getText());
                    send_message.setText("");
                }
            }
        });
    }
}