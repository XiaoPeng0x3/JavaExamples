import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  条件变量这一块
 *  使用ReentrantLock代替synchronized时，为了达到之前同样的效果，可以使用Reentrant的条件变量来进行替换
 */
public class ConditionExample {
    ReentrantLock lock = new ReentrantLock();
    // 条件变量
    Condition condition = lock.newCondition();

    int count = 0;

    // 生产者
    public void add() {
        lock.lock();
        try {
            count += 1;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    // 消费者
    public void remove() {
        lock.lock();
        try {
            while (count <= 0) {
                condition.await();
            }
            // 消费者
            count -= 1;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }
}
