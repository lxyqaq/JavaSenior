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
    private Employee[] employees;
    private int size;

    protected HashT(int size) {
        this.size = size;
        arr = new CreateLinkedList[size];

        //将哈希表中的每一个链表初始化
        for (int i = 0; i < size; i++) {
            arr[i] = new CreateLinkedList();
        }
    }

    //散列函数,用于确定数据放在哪一个链表里
    public int Hash(int id) {
        return id % size;
    }

    //添加
    public void add(Employee emp) {
        int position = Hash(emp.getId());
        arr[position].add(emp);
    }

    //删除
    public void del(int id) {
        int position = Hash(id);
        arr[position].del(id);
    }

    //展示
    public void show() {
        for (int i = 0; i < size; i++) {
            arr[i].show(i);
        }
    }

    //查找
    public Employee find(int id) {
        int position = Hash(id);
        return arr[position].search(id);
    }

    public int findStep(int id) {
        int position = Hash(id);
        return arr[position].searchStep(id);
    }

    //修改
    public void update(int id, int age, String name) {
        int position = Hash(id);
        arr[position].search(id).setAge(age);
        arr[position].search(id).setName(name);
    }

    public CreateLinkedList[] readEmployee() {
        return arr;
    }

}
