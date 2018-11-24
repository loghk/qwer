package AWT;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Work2 {
    public static void main(String[] args)
    {
        new MyCalculator().init();
    }
}

class MyCalculator{
    Frame frame = new Frame("计算器");
    TextArea textArea = new TextArea(1,1);
    Panel panel = new Panel();
    String[] name = {"AC","+/-","%","/","7","8","9","*","4","5","6","-","1","2","3","+","0",".","="};
    public void init(){
        textArea.setFont(new Font("黑体", Font.BOLD, 60));
        frame.setSize(500, 500);
        frame.add(textArea,BorderLayout.NORTH);
        panel.setLayout(new GridLayout(5,4));
        for (int i = 0;i<name.length;i++){
            panel.add(new Button(name[i]));
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}