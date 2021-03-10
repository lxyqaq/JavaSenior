package Q1;

import java.util.Scanner;


class Module<T extends Comparable<T>> {
    private T name;
    private T courseCode;

    public Module(T n, T cc) {
        name = n;
        courseCode = cc;
    }

    public T readModuleName() {
        return name;
    }

    public T readCourseCode() {
        return courseCode;
    }

    public void resetName(T nm) {
        name = nm;
    }

    public boolean changeCourseCode(T oldCC, T newCC) {
        if (courseCode.equals(oldCC)) {
            courseCode = newCC;
            return true;
        } else return false;
    }

}

public class ModuleGenQ1 {
    public static void main(String[] args) {

        Module module = new Module<String>("Maths", "54321");

        System.out.println("Module: " + module.readModuleName());
        System.out.println("Code:   " + module.readCourseCode());

        System.out.println("\n\n Try Change Code (12345, 54320: " + module.changeCourseCode("12345", "54320"));

        System.out.println("\n\n Try Change Code (54321, 54320: " + module.changeCourseCode("54321", "54320"));

        System.out.println("\n\nModule: " + module.readModuleName());
        System.out.println("Code:   " + module.readCourseCode());
    }
}
