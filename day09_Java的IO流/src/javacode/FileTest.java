package javacode;

import org.junit.Test;

import java.io.File;
import java.util.Date;

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

    /**
     * public String getAbsolutePath()：获取绝对路径
     * public String getPath() ：获取路径
     * public String getName() ：获取名称
     * public String getParent()：获取上层文件目录路径。若无，返回null
     * public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
     * public long lastModified() ：获取最后一次的修改时间，毫秒值
     * <p>
     * 如下的两个方法适用于文件目录：
     * public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
     * public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
     */
    @Test
    public void test2() {

        File file1 = new File("hello.txt");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

    }

    /**
     * public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
     * public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
     */
    @Test
    public void test3() {

        File file = new File("/Users/lxyqaq/IdeaProjects/JavaSenior");

        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("************");

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println(f);
        }

    }

    /**
     * public boolean renameTo(File dest):把文件重命名为指定的文件路径
     *      比如：file1.renameTo(file2)为例：
     *         要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
     */
    @Test
    public void test4() {

        File file1 = new File("hello.txt");
        File file2 = new File("/Users/lxyqaq/IdeaProjects/JavaSenior/day09_Java的IO流/hi.txt");

        boolean renameTo = file1.renameTo(file2);

        System.out.println(renameTo);

    }

}
