package Thread;

public class Work1 {
    public static void main(String[] args) {
        new Thread(new B()).start();
        new Thread(new A()).start();
    }
}

class A implements Runnable {
    int i = 0;
    @Override
    public void run() {
        while (true) {
            if (i==20){
                break;
            }
            if (i==10){
                try {
                    System.out.println("A stop!");
                    Thread.sleep(5000);
                    System.out.println("A start!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A"+"---"+i);
            i++;
        }
    }
}

class B implements Runnable {
    int i = 0;
    @Override
    public void run() {
        while (true) {
            if (i==30){
                break;
            }
            if (i==12){
                try {
                    System.out.println("B stop!");
                    Thread.sleep(3000);
                    System.out.println("B start!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B"+"---"+i);
            i++;
        }
    }
}
