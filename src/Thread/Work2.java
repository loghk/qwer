package Thread;
/*
*   两个线程同步执行，当Y线程执行到 i = 5 时，两个线程才交替执行
*   使用同步代码块的时候注意 notify 和 wait 的顺序，wait是立即阻塞，放弃cpu和锁，所以 notify 要在 wait 之前
*   多线程的时候尽量使用线程安全的类 StringBuffer
* */
public class Work2 {
    public static void main(String[] args) {
        StringBuffer lock = new StringBuffer("false");
        new Thread(new X(lock)).start();
        new Thread(new Y(lock)).start();
    }
}

class X implements Runnable {
    private StringBuffer lock;

    public X(StringBuffer lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("X");
        int i = 0;

        synchronized (lock) {
            lock.notify();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (; i < 10; i++) {
                try {
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + "---" + i);
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock = lock.replace(0, lock.length(), "true");
            System.out.println(lock);
            lock.notifyAll();
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("X end");
        }

    }
}

class Y implements Runnable {
    private StringBuffer lock;

    public Y(StringBuffer lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Y");
        int i = 0;

        synchronized (lock) {
            while (true) {
                for (; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                        System.out.println(Thread.currentThread().getName() + "---" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i >= 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                while (lock.toString().equals("true")) {
                    System.out.println("Y out");
                    lock.notifyAll();
                    try {
                        lock.wait(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}