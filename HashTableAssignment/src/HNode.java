
class HNode {
    int key;
    String name;
    int age;
    HNode next;

    HNode(int k, String nm, int a) {
        key = k;
        name = nm;
        age = a;
        next = null;
    }

    public int readKey() {
        return key;
    }

    public String readName() {
        return name;
    }

    public int readAge() {
        return age;
    }
}