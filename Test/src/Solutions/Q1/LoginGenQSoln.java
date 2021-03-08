package Solutions.Q1;

import java.util.Scanner;


class Login<T extends Comparable<T>> {
    private T username;
    private T password;

    public Login(T n, T pw) {
        username = n;
        password = pw;
    }

    public T readUserName() {
        return username;
    }

    public T readPassword() {
        return password;
    }

    public void resetPassword(T pw) {
        password = pw;
    }

    public boolean login(T id, T pw) {
        if ((username.equals(id)) && (password.equals(pw))) return true;
        else return false;
    }

}

public class LoginGenQSoln {
    public static void main(String[] args) {

        Login<String> log = new Login("abcde", "54321");

        System.out.println("UserName: " + log.readUserName());
        System.out.println("Password: " + log.readPassword());

        System.out.println("\n\n Check Login (abcde, 54320: " + log.login("abcde", "54320"));

        System.out.println("\nChange Password to 54320: ");
        log.resetPassword("54320");

        System.out.println("\n\n Check Login (12345, 54320: " + log.login("abcde", "54320"));

    }
}
