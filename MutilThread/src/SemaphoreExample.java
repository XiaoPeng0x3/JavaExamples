import java.util.UUID;
import java.util.concurrent.Semaphore;

/**
 * 信号量机制
 * 主要用于资源受限访问
 * 当下载资源时，可以使用信号量来做到受限资源访问
 */

public class SemaphoreExample {
    // 信号量机制

    // 最多允许5个线程去下载资源
    Semaphore semaphore = new Semaphore(5);

    public String getAccess() throws InterruptedException {
        // 计数器-1
        semaphore.acquire();
        try {
            // do Sth
            return UUID.randomUUID().toString();

        } finally {
            // 计数器+1
            semaphore.release();
        }
    }
}
