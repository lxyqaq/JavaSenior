package example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;


class Module implements Container, Iterator {


    private String name;
    private String lecturer;
    private Student[] list;
    private int index;

    public Module(String nm, String tp, Student[] li) {
        lecturer = tp;
        name = nm;
        list = li;
        index = 0;
    }

    public void addStudent(String nm) {
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = new Student(nm);
    }

    public int reaNumberOfStudents() {
        return list.length;
    }

    public String readName() {
        return name;
    }

    public String readLecturer() {
        return lecturer;
    }

    public Iterator iterator() {
        return new Module(name, lecturer, list);
    }

    public boolean hasNext() {
        return index < list.length;
    }

    public Object next() {
        Student res = list[index++];
        return res;
    }
}
