import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  synchronized关键字、ReentrantLock演示
 */


public class SyncExample {

    // 模拟死锁
    static final Object LOCK1 = new Object();
    static final Object LOCK2 = new Object();


    public static void main(String[] args) {
        test1();
    }

    // 实现同步机制

    // 1. synchronized关键字
    public static void test1() {
        // 多线程计数器
        Count count = new Count(0);

        // 线程1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count.add(1);
            }
        }).start();

        // 线程2
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count.desc(2);
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        if (count.getCount() == -10) {
            System.out.println("并发计数器正确！");
        } else {
            System.out.println("并发计数器失败！");
        }
    }

    // 可重入的锁

    /**
     *  可重入的锁指的是可以被线程多次获取的锁
     *  例如，考虑下面这个代码
     *   public synchronized void add(int n) {
     *         if (n < 0) {
     *             dec(-n);
     *         } else {
     *             count += n;
     *         }
     *     }
     *     public synchronized void dec(int n) {
     *         count += n;
     *     }
     *
     *     当一个线程调用add(-10)，那么这个线程已经拿到了this锁，但是在这个desc函数里面又要拿到锁，所以说这个线程实际上是拿到了两次this锁
     *     也就是说这个锁是可以重入的
     *
     *     再来看看什么是死锁
     *     死锁就是一个线程拥有A锁，同时去请求B锁，而此时另外一个线程拥有B锁，此时它再去请求A锁，这样可能会引发死锁
     */
    public static void test2() {
        // 此时有两个线程
        int cnt = 0;

        // 线程1
//        new Thread(() -> {
//            synchronized (LOCK1) {
//                cnt += 10;
//                synchronized (LOCK2) {
//                    cnt -= 10;
//                }
//            }
//        }).start();
//
//        // 线程2
//        new Thread(() -> {
//            synchronized (LOCK2) {
//                cnt -= 10;
//                synchronized (LOCK1) {
//                    cnt += 10;
//                }
//            }
//        }).start();
//
//        // 如果两者同时执行，就会引发死锁
//
//        // 解决死锁的一个好办法是，对于锁来说，不同的线程获取锁的循序要一致， 下面是一个合理的顺序
//        new Thread(() -> {
//            synchronized (LOCK1) {
//                cnt += 10;
//                synchronized (LOCK2) {
//                    cnt -= 10;
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            synchronized (LOCK1) {
//                cnt += 10;
//                synchronized (LOCK2) {
//                    cnt -= 10;
//                }
//            }
//        }).start();

        // 这样，获取LOCK1的时候就不会去发生死锁
    }

    /**
     * 生产者和消费者模式
     * 这是一个经典的条件变量的应用场景，这里模拟一个队列，生产者线程会向队列里面写入数据
     * 而消费者线程会消费队列里面的数据，前提是队列里面至少有一条数据
     * 实际上，当队列里面不存在数据时，消费者应该去挂起等待，这里就是条件变量的使用
     */
    public static void test3() {
        // wait和notify函数
    }
}

// 使用synchronized
class Count {
    int count;
    Count(int count) {
        this.count = count;
    }

    public void add(int count) {
        synchronized (this) {
            this.count += count;
        }
    }

    public void desc(int count) {
        synchronized (this) {
            this.count -= count;
        }
    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }

    // synchronized锁this对象的时候可以加在方法前

    // 等同于上面那个add方法
    public synchronized void add() {

    }

    // 等同于上面那个desc
    public synchronized void desc() {

    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        // 通知挂起的进程
        this.notify();
    }

    /**
     * 为什么要使用while而不使用if?
     * 考虑有多个消费者线程,假设是A, B, C三个消费者线程
     * 当A线程执行的时候,队列还为空,此时A线程挂起等待, 然后生产者线程将一个元素添加到队列里面,此时A线程被唤醒,但是A还未来得及进行消费
     * 就被B抢先了,也就是说此时队列变为空了,但是A线程则认为队列不为空(如果使用if, 不会进行二次检查),所以也会去执行remove, 这就导致了不正确的bug
     *
     */
    public synchronized String getTask() throws InterruptedException {
        // 队列为空,应该持续等待
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}


/**
 * 更常见的锁 ReentrantLock
 * 可以重入的锁,不同的是,这个锁可以去进行尝试,如果拿不到锁的话,直接返回
 */

class ReentrantLockExample {
    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    // add
    public void add() {
        lock.lock();
        try {
            this.count += 10;
        } finally {
            lock.unlock();
        }
    }

    // desc
    public void desc() {
        lock.lock();
        try {
            this.count -= 10;
        } finally {
            lock.unlock();
        }
    }

    // 尝试获取锁
    public void tryGetLock() throws InterruptedException {
        if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                //...
            } finally {
                // 没有获取到锁是不会执行unlock操作
                lock.unlock();
            }
        }
    }
}
