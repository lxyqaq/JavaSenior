package exercise;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName AccountTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/14 20:43
 * @Version 1.0
 */

/**
 * 银行有一个账户。
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 * 分析：
 * 1.是否是多线程问题？ 是，两个储户线程
 * 2.是否有共享数据？ 有，账户（或账户余额）
 * 3.是否有线程安全问题？有
 * 4.需要考虑如何解决线程安全问题？同步机制：有三种方式。
 */
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(0);
        Customer customer = new Customer(account);

        Thread thread1 = new Thread(customer);
        Thread thread2 = new Thread(customer);

        thread1.setName("甲");
        thread2.setName("乙");

        thread1.start();
        thread2.start();
    }
}

class Account {

    private double balance;

    private ReentrantLock lock = new ReentrantLock();

    public Account(double balance) {
        this.balance = balance;
    }

    //存钱的方法
    public void deposit(double money) {

        lock.lock();

        if (money > 0) {
            balance += money;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":存钱成功。余额为: " + balance);
        }

        lock.unlock();

    }

}

class Customer implements Runnable {

    private Account account;

    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            account.deposit(1000);
        }

    }

}
