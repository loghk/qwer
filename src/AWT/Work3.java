package AWT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Work3 {
    public static void main(String[] args)
    {
        Game game = new Game();
        game.init();
        game.run();
    }
}

class Game{
    private final int circleSize = 15;
    private final int ban_hight = 20;
    private final int ban_width = 100;
    private final int size_hight = 600;
    private final int size_width = 400;
    private final int ban_speed = 50;
    private int ban_x_location = (size_width-ban_width)/2;
    private int ban_y_location = size_hight-100;
    Random random = new Random();
    private int x_location = random.nextInt(200)+50;
    private int y_location = random.nextInt(100)+50;
    private int x_speed = 5;
    private int y_speed = (int) (x_speed*1.5);
    private Frame frame = new Frame("小球");
    private Dialog dialog = new Dialog(frame, "你输了", false);
    private MyCancas myCancas = new MyCancas();
    private Timer timer;
    Button button_yes = new Button("YES");
    Button button_no = new Button("NO");
    public void init(){
        frame.setSize(size_width, size_hight);
        frame.add(myCancas);
        frame.setVisible(true);
        dialog.setLayout(new GridLayout(1, 2,10,10));
        dialog.setSize(300, 200);
        dialog.add(button_yes);
        dialog.add(button_no);
        //dialog.pack();
    }

    public void run(){
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        KeyAdapter KA = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_LEFT&&ban_x_location>0){
                    ban_x_location -= ban_speed;
                }
                if (e.getKeyCode()==KeyEvent.VK_RIGHT&&ban_x_location<size_width-ban_width){
                    ban_x_location += ban_speed;
                }
            }
        };
        frame.addKeyListener(KA);
        myCancas.addKeyListener(KA);
        button_no.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        button_yes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x_location = random.nextInt(200)+50;
                y_location = random.nextInt(10)+50;
                dialog.setVisible(false);
                timer.start();
            }
        });
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        ActionListener AL = e -> {
            if (x_location<=0||x_location>=size_width-circleSize){
                x_speed = -x_speed;
            }
            if (y_location<=0||y_location>=size_hight-circleSize){
                y_speed = -y_speed;
            }
            if (y_location+circleSize>=ban_y_location&&x_location>=ban_x_location&&x_location<=ban_x_location+ban_width){
                y_speed = -y_speed;
            }
            if (y_location>=ban_y_location){
                timer.stop();
                dialog.setVisible(true);
            }
            x_location += x_speed;
            y_location += y_speed;
            myCancas.repaint();
        };
        timer = new Timer(10, AL);
        timer.start();
    }
    class MyCancas extends Canvas{
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(255, 17, 0));
            g.fillOval(x_location, y_location, circleSize, circleSize);
            g.setColor(new Color(0, 21, 255));
            g.fillRect(ban_x_location, ban_y_location, ban_width, ban_hight);
        }
    }

}
