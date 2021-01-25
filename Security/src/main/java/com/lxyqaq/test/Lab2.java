package com.lxyqaq.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @ClassName Lab2
 * @Description TODO
 * @Author lxyqaq @Email A00279565@student.ait.ie
 * @Date 2021/1/25 14:00
 * @Version 1.0
 */
public class Lab2 {

    public static void main(String[] args) {

        System.out.println("Enter: ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String str = DigestUtils.sha256Hex(s);
        System.out.println(str);
        Base64 base64 = new Base64();
        String str1 = base64.encodeToString(str.getBytes());
        System.out.println(str1);

    }

    @Test
    public void question2() throws IOException {

        File file = new File("test.txt");

        FileInputStream fis = new FileInputStream(file);

        byte[] buffer = new byte[64];
        int len;

        while ((len = fis.read(buffer)) != -1) {

            String str = new String(buffer, 0, len);
            System.out.println(str);

        }

        fis.close();

    }

}
