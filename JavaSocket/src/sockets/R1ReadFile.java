package sockets;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class R1ReadFile {

    public static void main(String[] args) throws Exception {

        FileInputStream fin = new FileInputStream(new File("JavaSocket/test.txt"));
        Scanner scanner = new Scanner(fin);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

    }

}
