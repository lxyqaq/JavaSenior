package example;

public class MoreFunctions implements Visitor {

    private Student student;

    public void visit(Visitable v) {
        student = (Student) v;
    }

    public int calculatePerCentAttendance() {
        double total = (double) student.readAbsent() + student.readAttented();
        return (int) ((student.readAttented() / total) * 100);
    }

    public boolean fiftyPerCentAttendance() {
        if (student.readAttented() >= student.readAbsent()) return true;
        else return false;
    }

}

