package javacode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CollectionTest1
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/2/2 00:45
 * @Version 1.0
 */
public class CollectionTest1 {

    @Test
    public void test1() {

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //1.contains(Object obj):判断当前集合中是否包含obj
        //我们在判断时会调用obj对象所在类的equals()。

        boolean contains = coll.contains(123);
        System.out.println(contains);

        System.out.println(coll.contains(new String("Tom")));

        //对比了三次
        System.out.println(coll.contains(new Person("Jerry", 20)));

        //2.containsAll(Collection coll1):判断形参coll1中的所有元素是否都存在于当前集合中。
        Collection coll1 = Arrays.asList(123, 456);
        System.out.println(coll.containsAll(coll1));

    }

    @Test
    public void test2() {

        //3.remove(Object obj):从当前集合中移除obj元素
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        System.out.println(coll);
        boolean remove = coll.remove(456);
        System.out.println(remove);
        boolean remove1 = coll.remove(new Person("Jerry", 20));
        System.out.println(remove1);
        System.out.println(coll);

        //4.removeAll(Collection coll):差集:从当前集合中移除coll1中所有的元素
        Collection coll1 = Arrays.asList(123, 456);
        coll.removeAll(coll1);
        System.out.println(coll);

    }

    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //5.retainAll(Collection coll):交集:获取当前集合和coll1集合的交集，并返回给当前集合
//        Collection coll1 = Arrays.asList(123, 456, 789);
//        coll.retainAll(coll1);
//        System.out.println(coll);

        //6.equals(Object obj):判断当前集合和形参
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add(123);
        coll1.add(new Person("Jerry", 20));
        coll1.add(new String("Tom"));
        coll1.add(false);

        System.out.println(coll.equals(coll1));

    }

    @Test
    public void test4() {

        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        //7.hashCode(): 返回当前对象的哈希值
        System.out.println(coll.hashCode());

        //8.集合 ----> 数组：toArray();
        Object[] array = coll.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        //拓展：数组 --->集合:调用Arrays类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);

        List arr1 = Arrays.asList(new int[]{123, 456});
        System.out.println(arr1.size());//1

        List arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());//2

    }

}
