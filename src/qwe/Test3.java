package qwe;



class A implements Runnable{
    public int i = 0;
    @Override
    public synchronized void run() {
        while (true){

            i++;
            System.out.println("AAAAAA"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i==15){

                notifyAll();
                return;
            }
        }
    }
}

class B implements Runnable{
    public int i = 0;
    @Override
    public synchronized void run() {

        while (true){
            i++;
            System.out.println("BBBBBB"+i);
            if (i==10) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Test3 {
    public static void main(String[] args)
    {
        Thread thread = new Thread(new A());

        Thread thread1 = new Thread(new B());
        thread.start();
        thread1.start();
        //System.out.println(Thread.currentThread().getId());
    }
}
