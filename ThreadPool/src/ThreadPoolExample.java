import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    public static void main(String[] args) {
        // 创建一个线程池

        // 固定大小的线程池
        // 每次最多有5个线程在使用
        // 当线程不够用时，只能挂起等待
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // 可以自行调整大小的线程池
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // 单线程执行的线程池


        // 提交任务
        for (int i = 0; i < 10; i++) {
            pool.submit(new Task("" + i));
        }

        // 关闭线程池
        pool.shutdown();

        System.out.println("-----------------------------------------");

        /**
         *  这里的关闭线程也是有说法的
         *  shutdown 是等待所有线程执行完任务后再进行释放
         *  shutdownNow 是不管任务是否执行完毕都去关闭线程池
         *  awaitTermination 是指定时间让线程池关闭
         */

        // 定时执行的任务
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        // 1秒后执行一次任务
        scheduledExecutorService.schedule(new Task("schedule"), 1, TimeUnit.SECONDS);
        //scheduledExecutorService.shutdown();

        // 定时任务执行
        // 1秒后开始执行，每隔3秒执行一次
        // 注意，这是一个定时触发的任务，所以不管 new Task中的任务执行多久，这个定时任务依旧还会触发
        scheduledExecutorService.scheduleAtFixedRate(new Task("FixedRate"), 1, 3, TimeUnit.SECONDS);

        // 还有一种定时任务
        // 1秒后开始执行，每3秒一次
        // 只不过这个间隔指的是上次任务执行完之后才开始计算
        // 也就是说，每一个任务都是在上一个任务执行完后才开始重新计算间隔
        // 然后再进行执行的
        scheduledExecutorService.scheduleWithFixedDelay(new Task("FixedDelay"), 1, 3, TimeUnit.SECONDS);
    }
}

class Task implements Runnable {

    private final String name;
    public Task(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("Running " + name);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}
