package qwe;

import java.util.ArrayList;
import java.util.Scanner;

class Loan{
    private double loan;

    public Loan() {
        loan = 0.0;
    }

    public Loan(double loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loan=" + loan +
                '}';
    }
}

class Date{
    private int year;
    private int month;
    private int day;
    public Date(){
        year = month = day =1;
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

class JFrame{
    private String framename;

    public JFrame(){
        framename = null;
    }
    public JFrame(String framename) {
        this.framename = framename;
    }

    @Override
    public String toString() {
        return "JFrame{" +
                "framename='" + framename + '\'' +
                '}';
    }
}

class Circle{
    private double radius;
    public Circle(){
        radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
public class Test1 {
    public static void main(String[] args)
    {
        ArrayList al = new ArrayList();
        Scanner sc = new Scanner(System.in);
        Loan loan = new Loan(sc.nextDouble());
        Date date = new Date(sc.nextInt(), sc.nextInt(),sc.nextInt());
        String str = sc.next();
        JFrame jFrame = new JFrame(sc.next());
        Circle circle = new Circle(sc.nextDouble());
        al.add(loan);
        al.add(date);
        al.add(str);
        al.add(jFrame);
        al.add(circle);
        for (int i = 0; i <al.size() ; i++) {
            System.out.println(al.get(i).toString());
        }
    }


}
