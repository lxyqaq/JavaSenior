class Display implements Runnable {

    /*public String name;

    public Display(String nm) {
        name = nm;
    }*/

    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


public class Q1Skel {
    public static void main(String[] args) {
        Display d1 = new Display();
        Thread thread1 = new Thread(d1);
        Thread thread2 = new Thread(d1);
        thread1.start();
        thread2.start();
    }
}
