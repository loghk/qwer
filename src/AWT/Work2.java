package AWT;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Work2 {
    public static void main(String[] args) {
        MyCalculator m = new MyCalculator();
        m.init();
        m.run();
    }
}

class MyCalculator {
    Frame frame = new Frame("计算器");
    TextArea textArea = new TextArea(1, 1);
    Panel panel = new Panel();
    String[] name = {"AC", "+/-", "%", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "="};
    Button[] buttons = new Button[name.length];

    public void init() {
        for (int i = 0; i < name.length; i++) {
            buttons[i] = new Button(name[i]);
        }
        textArea.setFont(new Font("黑体", Font.BOLD, 60));
        frame.setSize(500, 500);
        frame.add(textArea, BorderLayout.NORTH);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < name.length; i++) {
            gbc.weighty = 1;
            gbc.weightx = 1;
            gbc.gridwidth = 1;
            if (name[i].equals("0")) {
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridwidth = 2;
                gbc.gridheight = 1;
                gbl.setConstraints(buttons[i], gbc);
                panel.add(buttons[i]);
            } else {
                if (name[i].equals("/") || name[i].equals("*") || name[i].equals("-") || name[i].equals("+")) {
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                }
                gbc.fill = GridBagConstraints.BOTH;
                gbl.setConstraints(buttons[i], gbc);
                panel.add(buttons[i]);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }

    public void run() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        buttonListener();
    }

    public void buttonListener() {
        // AC
        buttons[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.setText(null);
            }
        });
        // +/-
        buttons[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.setText("-" + textArea.getText());
            }
        });
        // %
        buttons[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double temp = Double.valueOf(textArea.getText());
                textArea.setText(String.valueOf(temp / 100));
            }
        });
        // /
        buttons[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("/");
            }
        });
        // 7
        buttons[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("7");
            }
        });
        // 8
        buttons[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("8");
            }
        });
        // 9
        buttons[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("9");
            }
        });
        // *
        buttons[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("*");
            }
        });
        // 4
        buttons[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("4");
            }
        });
        // 5
        buttons[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("5");
            }
        });
        // 6
        buttons[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("6");
            }
        });
        // -
        buttons[11].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("-");
            }
        });
        // 1
        buttons[12].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("1");
            }
        });
        // 2
        buttons[13].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("2");
            }
        });
        // 3
        buttons[14].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("3");
            }
        });
        // +
        buttons[15].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("+");
            }
        });
        // 0
        buttons[16].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("0");
            }
        });
        // .
        buttons[17].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append(".");
            }
        });
        // =
        buttons[18].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mathematics(textArea.getText());
            }
        });


    }

    public void mathematics(String string) {
        String[] str = string.split("[+*/\\-]");
        System.out.println(Arrays.toString(str));
        /*double num1 = Double.valueOf(str[0]);
        double num2 = Double.valueOf(str[1]);*/
        BigDecimal num1 = new BigDecimal(str[0]);
        BigDecimal num2 = new BigDecimal(str[1]);
        MathContext mathContext = new MathContext(str[0].length()*2,RoundingMode.HALF_UP);
        char[] temp = string.toCharArray();
        char sign = '1';
        for (char c : temp) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                sign = c;
            }
        }
        BigDecimal ans = null;
        try {
            switch (sign) {
                case '+':
                    ans = num1.add(num2);
                    break;
                case '-':
                    ans = num1.subtract(num2);
                    break;
                case '*':
                    ans = num1.multiply(num2);
                    break;
                case '/':
                    ans = num1.divide(num2,mathContext);
                    break;
            }
        } catch (ArithmeticException e) {
            textArea.setText("格式不正确");
            return;
        }
        textArea.setText(ans.toString());
    }
}