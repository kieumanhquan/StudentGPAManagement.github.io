package StudentGPAManagement.entity;

import java.util.Scanner;

public class Student extends Person{
    public static int AUTO_ID = 10000;

    private int idSV;
    private String classRoom;

    public int getIdSV() {
        return idSV;
    }

    public void setIdSV(int idSV) {
        this.idSV = idSV;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public void inputInfo() {
        this.idSV = Student.AUTO_ID;
        Student.AUTO_ID++;

        super.inputInfo();

        System.out.println("Nhập tên lớp: ");
        this.classRoom = new Scanner(System.in).nextLine();
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", idSV=" + idSV +
                ", classRoom='" + classRoom + '\'' +
                '}';
    }
}
