package javacode;

import org.junit.Test;

import java.io.File;

/**
 * @ClassName FileTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/21 15:23
 * @Version 1.0
 */
public class FileTest {

    /**
     * 1.如何创建File类的实例
     * File(String filePath)
     * File(String parentPath,String childPath)
     * File(File parentFile,String childPath)
     * <p>
     * 2.
     * 相对路径：相较于某个路径下，指明的路径。
     * 绝对路径：包含盘符在内的文件或文件目录的路径
     * <p>
     * 3.路径分隔符
     * windows:\\
     * unix:/
     */
    @Test
    public void test1() {

        //构造器1
        File file1 = new File("hello.txt");//相对于当前module

        System.out.println(file1);

        //构造器2：
        File file3 = new File("D:\\workspace_idea1", "JavaSenior");
        System.out.println(file3);

        //构造器3：
        File file4 = new File(file3, "hi.txt");
        System.out.println(file4);

    }

}
