import java.io.Serializable;
import java.util.Arrays;

public class MarkSheet extends Students implements Serializable {
    private Students students;
    private Subject subjects;
    private float marks;

    public MarkSheet() {
    }

    public MarkSheet(Students students, Subject subjects, float marks) {
        this.students = students;
        this.subjects = subjects;
        this.marks = marks;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Subject getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "MarkSheet {" +
                " student = " + students +
                ", subjects = " + subjects +
                '}';
    }
}
