import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass {

    public static void main(String[] args) throws InterruptedException {

        TestClass tc = new TestClass();
        for (int i = 0; i < 10; i++) {
            tc.testIncrement("T"+i);
        }

        Thread.sleep(1000);

        System.out.println("count is: " + tc.count);
    }

    private void testIncrement(String id)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increment();
                }
            }
        }).start();
    }

    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    private void increment() {

        //statement
        lock.lock();
        try {
            int sum = count + 1;
            count = sum;
        }
        finally {
            lock.unlock();
        }

    }

}
