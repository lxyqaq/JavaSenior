package assign;

/**
 * @ClassName HashT
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/22 01:34
 * @Version 1.0
 */
public class HashT {

    private CreateLinkedList[] arr;
    private int size;

    protected HashT(int size) {
        this.size = size;
        arr = new CreateLinkedList[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new CreateLinkedList();
        }
    }

    public int Hash(int id) {
        return id % size;
    }

    public void add(Employee emp) {
        int position = Hash(emp.getId());
        arr[position].add(emp);
    }

    public boolean del(int id) {
        int position = Hash(id);
        boolean del = arr[position].del(id);
        return del;
    }

    public Employee find(int id) {
        int position = Hash(id);
        return arr[position].search(id);
    }

    public int findStep(int id) {
        int position = Hash(id);
        return arr[position].searchStep(id);
    }

    public void update(int id, int age, String name) {
        int position = Hash(id);
        arr[position].search(id).setAge(age);
        arr[position].search(id).setName(name);
    }

    public CreateLinkedList[] readEmployee() {
        return arr;
    }

}
