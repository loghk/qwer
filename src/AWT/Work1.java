package AWT;
/*
* 随机数生成器，图形界面
* */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Work1 {
    public static void main(String[] args) {
        Event event = new Event();
        event.init();
        event.run();
    }
}

class Event {
    Frame frame = new Frame("随机数");
    Button stop = new Button("Stop");
    Button begin = new Button("Begin");
    TextArea text = new TextArea(1, 1);
    Boolean flag = true;
    public void init() {
        frame.setSize(800, 800);
        Panel panel = new Panel();
        panel.add(stop);
        panel.add(begin);
        frame.setLayout(new BorderLayout(10, 100));
        frame.add(text);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    //管理容器运行的线程
    public void run(){
        TextThread textThread = new TextThread();
        textThread.start();
        stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag = false;
            }
        });

        begin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag = true;
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    class TextThread extends Thread{
        Random random = new Random();
        int i= 0;
        @Override
        public void run() {
            while (true) {
                while (flag){
                    text.setFont(new Font("黑体", Font.BOLD, 500));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    text.setText(String.valueOf(random.nextInt(50)));
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
