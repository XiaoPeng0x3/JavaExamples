import java.util.concurrent.locks.StampedLock;

/**
 *  乐观锁
 *  乐观锁认为读写冲突出现的概率很小，所以对于临界区的资源，我们可以先读出来，以某种手段再去检测一下是否被修改了即可
 */
public class StampedLockExample {

    // 初始化一个乐观读写锁
    private final StampedLock stampedLock = new StampedLock();

    private int x = 0;
    private int y = 0;

    // 写的时候还是一个线程去写
    public void add(int a, int b) {
        // 获取写锁
        long stamp = stampedLock.writeLock();
        try {
            x += a;
            y += b;
        } finally {
          stampedLock.unlockWrite(stamp);
        }
    }

    // 读的时候允许写
    public int getX() {
        // 获得一个乐观锁
        long stamp = stampedLock.tryOptimisticRead();

        int curX = x;
        int curY = y;

        // 读的时候，某个线程写入了
        if (!stampedLock.validate(stamp)) {
            // 获取一个悲观读锁
            stamp = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                // 释放锁
                stampedLock.unlockRead(stamp);
            }
        }
        return curX;
    }

}
