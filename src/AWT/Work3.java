package AWT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/*
 *   可以用一个数组储存每个方块信息，画一个方块方阵，实现打方块游戏;
 *
 * */
public class Work3 {
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }
}

class Game {
    private targetInf[][] targets = new targetInf[5][5];
    private targetInf temp;
    private final int target_size = 50;
    private final int circleSize = 15;
    private final int ban_hight = 20;
    private final int ban_width = 100;
    private final int size_hight = 1000;
    private final int size_width = 470;
    private final int ban_speed = ban_width - 1;
    private int ban_x_location = (size_width - ban_width) / 2;
    private int ban_y_location = size_hight - 100;
    Random random = new Random();
    private int x_location = random.nextInt(200) + 50;
    private int y_location = random.nextInt(50) + 250;
    private int x_speed = 1;
    private int y_speed = 1;
    private Frame frame = new Frame("小球");
    private Dialog dialog = new Dialog(frame, "你输了", false);
    private MyCancas myCancas = new MyCancas();
    private Timer timer;
    Button button_yes = new Button("YES");
    Button button_no = new Button("NO");

    public void init() {
        frame.setResizable(false);
        frame.setSize(size_width, size_hight);
        frame.add(myCancas);
        frame.setVisible(true);
        dialog.setLayout(new GridLayout(1, 2, 10, 10));
        dialog.setSize(300, 200);
        dialog.add(button_yes);
        dialog.add(button_no);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                targets[i][j] = new targetInf(40 + 80 * j, 40 + 80 * i, target_size);
            }
        }
    }

    public void run() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        KeyAdapter KA = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && ban_x_location > 0) {
                    if (ban_x_location - ban_speed <= 0) {
                        ban_x_location = 0;
                    } else
                        ban_x_location -= ban_speed;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && ban_x_location + ban_width < size_width) {
                    if (ban_x_location + ban_width + ban_speed >= size_width + 1) {
                        ban_x_location = size_width - ban_width;
                    } else
                        ban_x_location += ban_speed;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP&&ban_y_location<700){
                    ban_y_location += ban_speed;
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN&&ban_y_location+ban_hight<=size_hight){
                    if (ban_y_location+ban_hight+ban_speed>=size_hight){
                        ban_y_location = size_hight-ban_hight;
                    }
                    ban_y_location += ban_speed;
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
                x_location = random.nextInt(200) + 50;
                y_location = random.nextInt(10) + 50;
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
            if (x_location <= 0 || x_location >= size_width - circleSize) {
                x_speed = -x_speed;
            }
            if (y_location <= 0 || y_location >= size_hight - circleSize) {
                y_speed = -y_speed;
            }
            if (y_location + circleSize >= ban_y_location && x_location >= ban_x_location && x_location <= ban_x_location + ban_width) {
                y_speed = -y_speed;
            }
//            if (y_location >= ban_y_location) {
//                timer.stop();
//                dialog.setVisible(true);
//            }
            if ((temp = isTouch())!=null){
                temp.size = 0;
            }
//            if ((temp = isIn())!=null){
//                temp.size = 0;
//            }
            isTouch();
            x_location += x_speed;
            y_location += y_speed;
            myCancas.repaint();
        };
        timer = new Timer(1, AL);
        timer.start();
    }

    public targetInf isTouch() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((x_location + circleSize == targets[i][j].x_postion || x_location == targets[i][j].x_postion + targets[i][j].size)
                        && y_location >= targets[i][j].Y_postion
                        && y_location + circleSize <= targets[i][j].Y_postion + targets[i][j].size) {
                    x_speed = -x_speed;
                    return targets[i][j];
                }
                if ((y_location + circleSize == targets[i][j].Y_postion || y_location == targets[i][j].Y_postion + targets[i][j].size)
                        && x_location >= targets[i][j].x_postion
                        && x_location <= targets[i][j].x_postion + targets[i][j].size) {
                    y_speed = -y_speed;
                    return targets[i][j];
                }
            }
        }
        return null;
    }

    public targetInf isIn(){
        for (int i = 0;i<3;i++){
            for (int j = 0;j<5;j++){
                if (x_location+circleSize<=targets[i][j].x_postion+targets[i][j].size
                &&x_location+circleSize>=targets[i][j].x_postion
                &&y_location+circleSize>=targets[i][j].Y_postion
                &&y_location+circleSize<=targets[i][j].Y_postion+targets[i][j].size){
                    return targets[i][j];
                }
            }
        }
        return null;
    }
    class MyCancas extends Canvas {
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(255, 17, 0));
            g.fillOval(x_location, y_location, circleSize, circleSize);

            g.setColor(new Color(0, 21, 255));
            g.drawRect(ban_x_location, ban_y_location, ban_width, ban_hight);

            g.setColor(new Color(0, 0, 0));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    g.drawRect(targets[i][j].getX_postion(), targets[i][j].getY_postion(), targets[i][j].size, targets[i][j].size);
                }
            }
        }
    }

    class targetInf {
        int x_postion;
        int Y_postion;
        int size;

        public targetInf(int x_postion, int y_postion, int size) {
            this.x_postion = x_postion;
            Y_postion = y_postion;
            this.size = size;
        }

        public int getX_postion() {
            return x_postion;
        }

        public void setX_postion(int x_postion) {
            this.x_postion = x_postion;
        }

        public int getY_postion() {
            return Y_postion;
        }

        public void setY_postion(int y_postion) {
            Y_postion = y_postion;
        }
    }
}
