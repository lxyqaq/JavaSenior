package javacode;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName FileInputOutputStreamTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/21 21:54
 * @Version 1.0
 */
public class FileInputOutputStreamTest {

    /**
     * 使用字节流FileInputStream处理文本文件，可能出现乱码。
     */
    @Test
    public void test1() {

        FileInputStream fis = null;

        try {
            //1.造文件
            File file = new File("hello.txt");

            //2.造流
            fis = new FileInputStream(file);

            //3.读数据
            byte[] buffer = new byte[5];
            //记录每次读取字节的个数
            int len;

            while ((len = fis.read(buffer)) != -1) {

                String str = new String(buffer, 0, len);
                System.out.println(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                //4.关闭资源
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 对图片的复制
     */
    @Test
    public void test2() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File("爱情与友情.jpg");
            File destFile = new File("爱情与友情2.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * 指定路径下文件的复制
     */
    @Test
    public void test3() {
        long start = System.currentTimeMillis();

        String srcPath = "C:\\Users\\Administrator\\Desktop\\01-视频.avi";
        String destPath = "C:\\Users\\Administrator\\Desktop\\02-视频.avi";


//        String srcPath = "hello.txt";
//        String destPath = "hello3.txt";

        copyFile(srcPath, destPath);


        long end = System.currentTimeMillis();

        System.out.println("复制操作花费的时间为：" + (end - start));//618
    }

    public void copyFile(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                //
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
