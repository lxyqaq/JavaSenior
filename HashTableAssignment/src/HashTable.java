

class HashTable {
    public int hash(int id) {
        return id % 10;
    }

    private HNode[] head = new HNode[10];

    public HashTable() {
        for (int i = 0; i < 10; i++) head[i] = null;
    }

    public void insert(int k, String nm, int a) {
        HNode temp = new HNode(k, nm, a);
        int index = hash(k);
        temp.next = head[index];
        head[index] = temp;
    }

    public HNode[] readHNode() {
        return head;
    }


    public HNode search(int k) {
        int index = hash(k);
        HNode temp = head[index];
        boolean found = false;
        while (temp != null && found == false) {
            if (temp.key == k) {
                found = true;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }


}


   