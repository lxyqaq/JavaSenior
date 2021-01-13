package javacode1;

/**
 * @ClassName DeadLockTest2
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/13 21:54
 * @Version 1.0
 */
public class DeadLockTest2 implements Runnable {

    public static void main(String[] args) {
        DeadLockTest2 dl = new DeadLockTest2();
        new Thread(dl).start();

        dl.init();
    }

    A a = new A();
    B b = new B();

    public void init() {
        Thread.currentThread().setName("主线程");
        // 调用a对象的foo方法
        a.foo(b);
        System.out.println("进入了主线程之后");
    }

    public void run() {
        Thread.currentThread().setName("副线程");
        // 调用b对象的bar方法
        b.bar(a);
        System.out.println("进入了副线程之后");
    }

}

//死锁的演示
class A {
    public synchronized void foo(B b) { //同步监视器：A类的对象：a
        System.out.println("当前线程名: " + Thread.currentThread().getName()
                + " 进入了A实例的foo方法"); // ①
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("当前线程名: " + Thread.currentThread().getName()
                + " 企图调用B实例的last方法"); // ③
        b.last();
    }

    public synchronized void last() {//同步监视器：A类的对象：a
        System.out.println("进入了A类的last方法内部");
    }
}

class B {
    public synchronized void bar(A a) {//同步监视器：b
        System.out.println("当前线程名: " + Thread.currentThread().getName()
                + " 进入了B实例的bar方法"); // ②
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("当前线程名: " + Thread.currentThread().getName()
                + " 企图调用A实例的last方法"); // ④
        a.last();
    }

    public synchronized void last() {//同步监视器：b
        System.out.println("进入了B类的last方法内部");
    }

}