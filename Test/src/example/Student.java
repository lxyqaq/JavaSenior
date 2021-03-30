package example;

class Student implements Visitable {

    public void accept(Visitor v) {
        v.visit(this);
    }

    private String name;
    private int attended;
    private int absent;

    public Student(String nm) {
        name = nm;
        attended = 0;
        absent = 0;
    }

    public void markAttented() {
        attended++;
    }

    public void markAbsent() {
        absent++;
    }

    public int readAttented() {
        return attended;
    }

    public int readAbsent() {
        return absent;
    }

    public String readName() {
        return name;
    }

}