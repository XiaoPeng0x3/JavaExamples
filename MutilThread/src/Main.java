

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 两种创建多线程的方式
        // 1.实例化Thread类
        System.out.println("Main函数");
        Thread thread = new MyThread();
        thread.start();
        Thread.sleep(100);
        System.out.println("Main函数执行完毕");

        // 2.实现Runable接口
        Thread thread2 = new Thread(() -> {
            System.out.println("实现runable接口");
        });
        thread2.start();
        // 注意，下面的启动方式是错误的
        // thread.run(); 
        Thread.sleep(100);
        System.out.println("Main函数执行完毕");

        // 3. 中断线程

    }

}

class MyThread extends Thread {
    public void run() {
        System.out.println("多线程方法！！");
    }
}