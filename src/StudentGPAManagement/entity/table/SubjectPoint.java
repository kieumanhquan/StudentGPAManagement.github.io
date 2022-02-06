package StudentGPAManagement.entity.table;


import StudentGPAManagement.entity.Subject;

import java.io.Serializable;

public class SubjectPoint implements Serializable {
    private Subject subject;

    private float point;

    public SubjectPoint(Subject subject, float point) {
        this.subject = subject;
        this.point = point;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "SubjectPoint{" +
                "subject=" + subject +
                ", point=" + point +
                '}';
    }
}
