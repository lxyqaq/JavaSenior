package javacode;

import org.junit.Test;

import java.io.*;

/**
 * @ClassName InputStreamReaderTest
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/22 21:34
 * @Version 1.0
 */
public class InputStreamReaderTest {

    @Test
    public void test1() {

        InputStreamReader isr = null;
        try {
            FileInputStream fis = new FileInputStream("hello.txt");

            isr = new InputStreamReader(fis, "UTF-8");

            char[] cbuf = new char[5];
            int len;
            while ((len = isr.read(cbuf)) != -1) {
                String str = new String(cbuf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void test2() throws IOException {

        //1.造文件、造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");

        //2.读写过程
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            osw.write(cbuf, 0, len);
        }

        //3.关闭资源
        isr.close();
        osw.close();


    }

}
