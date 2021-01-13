class Display extends Thread {

    public String name;

    public Display(String nm) {
        name = nm;
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            System.out.println(name + ": " + i);
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
        Display d1 = new Display("First");
        Display d2 = new Display("Second");
        d1.start();
        d2.start();
    }
}
