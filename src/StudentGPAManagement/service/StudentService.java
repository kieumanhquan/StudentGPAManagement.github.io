package StudentGPAManagement.service;

import StudentGPAManagement.entity.Student;
import StudentGPAManagement.main.MainRun;
import StudentGPAManagement.util.DataUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentService {
    public static final String STUDENT_DATA_FILE = "student.dat";

    public void addNewStudent() {
        System.out.print("Nhập số sinh viên muốn thêm mới: ");
        int studentNumber = -1;
        do {
            try {
                studentNumber = new Scanner(System.in).nextInt();
                if (studentNumber > 0) {
                    break;
                }
                System.out.print("Số giảng viên muốn thêm mới phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số giảng viên muốn thêm mới phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
        for (int i = 0; i < studentNumber; i++) {
            Student student = new Student();
            student.inputInfo();
            addStudentToArray(student);
        }
        MainRun.fileUtil.writeDataToFile(MainRun.students, STUDENT_DATA_FILE);
    }

    public void addStudentToArray(Student student) {
        for (int i = 0; i < MainRun.students.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.students[i])) {
                MainRun.students[i] = student;
                break;
            }
        }
    }

    public void showStudents() {
        for (int i = 0; i < MainRun.students.length; i++) {
            Student student = MainRun.students[i];
            if (DataUtil.isNullOrEmpty(student)) {
                break;
            }
            System.out.println(student);
        }
    }

    public Student findStudentById(int studentId) {
        for (int i = 0; i < MainRun.students.length; i++) {
            if (studentId == MainRun.students[i].getIdSV()) {
                return MainRun.students[i];
            }
        }
        return null;
    }
}
