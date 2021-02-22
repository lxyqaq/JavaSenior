package assign;

/**
 * @ClassName CreateLinkedList
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/22 01:34
 * @Version 1.0
 */
public class CreateLinkedList {

    private Employee head;

    public Employee search(int id) {
        Employee temp = head;
        while (temp != null && temp.getId() != id) {
            temp = temp.getNext();
        }
        if (temp == null) {
            return null;
        } else {
            return temp;
        }
    }

    public void add(Employee emp) {
        if (head == null) {
            head = emp;
        } else {
            Employee temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(emp);
        }
    }

    public boolean del(int id) {
        boolean isFlag = false;
        if (head.getNext() == null && head.getId() == id) {
            head = null;
        } else if (head.getNext() != null && head.getId() == id) {
            head = head.getNext();
            isFlag = true;
        } else {
            Employee temp = head;
            while (temp.getNext() != null && temp.getNext().getId() != id) {
                temp = temp.getNext();
            }
            if (temp.getNext() != null) {
                temp.setNext(temp.getNext().getNext());
            } else {
                isFlag = false;
            }
        }
        return isFlag;
    }

    public int searchStep(int id) {
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

    public Employee getHead() {
        return head;
    }

}
