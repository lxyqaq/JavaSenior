package assign;

/**
 * @ClassName HashT
 * @Description HashTable Class
 * @Author Xiangyu Liu @Email A00279565@student.ait.ie
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

    public CreateLinkedList[] readEmployee() {
        return arr;
    }

    /**
     * @param emp
     * @return void
     * @description add Employee method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/23 13:46
     */
    public void add(Employee emp) {
        int position = Hash(emp.getId());
        arr[position].add(emp);
    }

    /**
     * @param id
     * @return boolean
     * @description delete Employee method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/23 13:47
     */
    public boolean del(int id) {
        int position = Hash(id);
        boolean del = arr[position].del(id);
        return del;
    }

    /**
     * @param id
     * @return assign.Employee
     * @description find Employee method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/23 13:57
     */
    public Employee find(int id) {
        int position = Hash(id);
        return arr[position].search(id);
    }

    /**
     * @param id
     * @return int
     * @description return steps of search Employee by ID
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/23 14:09
     */
    public int findStep(int id) {
        int position = Hash(id);
        return arr[position].searchStep(id);
    }

    /**
     * @param id
     * @param age
     * @param name
     * @return void
     * @description update Employee method
     * @author Xiangyu Liu @email A00279565@student.ait.ie
     * @date 2021/2/23 14:10
     */
    public void update(int id, int age, String name) {
        int position = Hash(id);
        arr[position].search(id).setAge(age);
        arr[position].search(id).setName(name);
    }

}
