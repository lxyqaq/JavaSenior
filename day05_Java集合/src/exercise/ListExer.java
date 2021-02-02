package exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListExer
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/2 22:36
 * @Version 1.0
 */
public class ListExer {

    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(123);
        updateList(list);
        System.out.println(list);
    }

    private void updateList(List list) {
        list.remove(new Integer(2));
    }

}
