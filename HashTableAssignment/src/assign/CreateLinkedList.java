package assign;

/**
 * @ClassName CreateLinkedList
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/22 01:34
 * @Version 1.0
 */
public class CreateLinkedList {

    //指定链表的头,默认为空
    private Employee head;

    //添加,添加到链表的尾部
    public void add(Employee emp) {
        //如果是第一个元素
        if (head == null) {
            head = emp;
        } else {
            //建立一个辅助指针
            Employee temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //已经找到链表的尾部，添加元素
            temp.setNext(emp);
        }
    }


    //删除
    public void del(int id) {

        if (head.getNext() == null && head.getId() == id) {
            head = null;
        } else if (head.getNext() != null && head.getId() == id) {
            head = head.getNext();
        } else {
            //建立一个辅助指针
            Employee temp = head;

            while (temp.getNext() != null && temp.getNext().getId() != id) {
                temp = temp.getNext();
            }
            if (temp.getNext() != null) {
                //已经找到要删除元素的前一个元素
                temp.setNext(temp.getNext().getNext());
            } else {
                System.out.println("哈希表中没有此元素");
            }
        }
    }

    //查找
    public Employee search(int id) {
        //建立一个辅助指针
        Employee temp = head;
        int count = 1;
        while (temp != null && temp.getId() != id) {
            count++;
            temp = temp.getNext();
        }
        if (temp == null) {
            return null;
        } else {
            return temp;
        }
    }

    public int searchStep(int id) {
        //建立一个辅助指针
        Employee temp = head;
        int count = 1;
        while (temp != null && temp.getId() != id) {
            count++;
            temp = temp.getNext();
        }
        if (temp == null) {
            return 0;
        } else {
            count++;
            return count;
        }
    }

    //展示
    public void show(int num) {
        if (head == null) {
            System.out.println("第" + (num + 1) + "条链表没有数据");
            return;
        }
        System.out.printf("第" + (num + 1) + "条链表输出数据为");
        //建立一个辅助指针
        Employee temp = head;
        while (temp != null) {
            System.out.printf("==>id=%d,age=%d,name=%s\t", temp.getId(), temp.getAge(), temp.getName());
            temp = temp.getNext();
        }
        System.out.println();
    }

    public Employee getHead() {
        return head;
    }

}
