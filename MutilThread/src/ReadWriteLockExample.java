import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁实际上还是一种悲观锁，因为在读写的过程中，它认为线程很有可能会去修改数据，所以提前把数据保护起来
 *  值得注意的是，读锁可以并发的读，而写锁却只允许一个线程去写
 *  而且，因为这是一个悲观锁，写的时候，所有读的线程都不允许，读的时候，所有写的线程都不被允许
 */
public class ReadWriteLockExample {
    // 读写锁例子

    // 可重入的读写锁
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    // 获得read lock
    private final Lock readLock = rwLock.readLock();
    // 获得写锁
    private final Lock writeLock = rwLock.writeLock();

    private int counter = 0;

    // 写的时候只允许一个线程写
    public void add() {
        writeLock.lock();
        try {
            counter += 10;
        } finally {
            writeLock.unlock();
        }
    }

    // 读的时候可以并发的读
    public int getCounter() {
        readLock.lock();

        try {
            return counter;
        } finally {
            readLock.unlock();
        }
    }
}
