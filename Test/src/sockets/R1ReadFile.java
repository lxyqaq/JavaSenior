package sockets;

import java.io.FileInputStream;
import java.util.Scanner;

public class R1ReadFile {

    public static void main(String[] args) throws Exception {

        ///Users/lxyqaq/IdeaProjects/JavaSenior/Test/src/sockets/test.txt
        FileInputStream fin = new FileInputStream("///Users/lxyqaq/IdeaProjects/JavaSenior/Test/src/test.txt");
        Scanner scanner = new Scanner(fin);

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
