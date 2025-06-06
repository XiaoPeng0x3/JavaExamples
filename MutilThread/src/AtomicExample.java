import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    // 线程安全计数器

    public static void main(String[] args) throws InterruptedException {
        // 初始化为0
        AtomicInteger counter = new AtomicInteger(0);
        // 创建两个线程
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 有点像 ++count
                counter.incrementAndGet();
                // 有点像 count++
                counter.getAndIncrement();
                // get
                counter.get();
                // set， 设置为新值
                counter.set(0);
            }
        });

        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        counter.incrementAndGet();
                    }
                }
        );

        // 同时开启两个线程
        t1.start();
        t2.start();

        // 主线程等待
        t1.join();
        t2.join();

        System.out.println(counter);
    }
}
