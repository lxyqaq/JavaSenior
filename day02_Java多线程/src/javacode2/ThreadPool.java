package javacode2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ThreadPool
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/15 15:41
 * @Version 1.0
 */
public class ThreadPool {
    public static void main(String[] args) {

        //1.提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        //2.设置线程池的属性
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        //executor.setCorePoolSize();
        //executor.setMaximumPoolSize();
        //executor.setKeepAliveTime();

        //3.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适合适用于Runnable
        service.execute(new NumberThread1());//适合适用于Runnable

        //service.submit(Callable callable);//适合使用于Callable

        //4.关闭连接池
        service.shutdown();

    }
}

class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}

class NumberThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}