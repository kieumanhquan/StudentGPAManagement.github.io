package StudentGPAManagement.main;

import StudentGPAManagement.entity.Student;
import StudentGPAManagement.entity.Subject;
import StudentGPAManagement.entity.table.PointTable;
import StudentGPAManagement.service.PointService;
import StudentGPAManagement.service.StudentService;
import StudentGPAManagement.service.SubjectService;
import StudentGPAManagement.util.DataUtil;
import StudentGPAManagement.util.file.FileUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainRun {
    public static Subject[] subjects;
    public static Student[] students;
    public static PointTable[] pointTables;


    public static StudentService studentService = new StudentService();
    public static SubjectService subjectService = new SubjectService();
    public static PointService pointService = new PointService();

    public static FileUtil fileUtil = new FileUtil();


    public static void main(String[] args) {
        initializeData();
        menu();
    }

    private static void initializeData() {
        Object studentFromFile = fileUtil.readDataFromFile(StudentService.STUDENT_DATA_FILE);
        MainRun.students = DataUtil.isNullOrEmpty(studentFromFile) ? new Student[100] : (Student[]) studentFromFile;

        Object subjectFromFile = fileUtil.readDataFromFile(SubjectService.SUBJECT_DATA_FILE);
        MainRun.subjects = DataUtil.isNullOrEmpty(subjectFromFile) ? new Subject[100] : (Subject[]) subjectFromFile;

        Object tableFromFile = fileUtil.readDataFromFile(PointService.POINT_DATA_FILE);
        MainRun.pointTables = DataUtil.isNullOrEmpty(tableFromFile) ? new PointTable[100] : (PointTable []) tableFromFile;
    }

    public static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    subjectService.addNewSubject();
                    subjectService.showSubjects();
                    break;
                case 2:
                    studentService.addNewStudent();
                    studentService.showStudents();
                    break;
                case 3:
                    pointService.creatPointTable();
                    pointService.showPointTable();
                    break;
                case 4:
                    pointService.sortPointTableToNameStudent();
                    pointService.showPointTable();
                    break;
                case 5:
                    pointService.sortPointTableToNameSubject();
                    pointService.showPointTable();
                    break;
                case 6:
                    pointService.pointCalculation();
                    break;
                case 7:
                    System.exit(0);
            }
        } while (true);
    }


    public static int functionChoice() {
        System.out.println("QUẢN LÝ TRẢ LƯƠNG CHO GIẢNG VIÊN THỈNH GIẢNG");
        System.out.println("1. Nhập danh sách môn học mới và in ra danh sách môn học trong trường");
        System.out.println("2. Nhập danh sách sinh viên mới và in ra danh sách sinh viên trong trường");
        System.out.println("3. Bảng điểm cho sinh viên và in ra danh sách bảng điểm");
        System.out.println("4. Sắp xếp danh sách bảng điểm theo tên sinh  viên");
        System.out.println("5. Sắp xếp danh sách bảng điểm theo tên môn học");
        System.out.println("6. Tính toán điểm tổng kết");
        System.out.println("7. Thoát");
        System.out.println("----------------------------------------------");
        System.out.print("Chọn chức năng: ");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice >= 1 && choice <= 7) {
                    return choice;
                }
                System.out.print("Chức năng khả dụng là một số nguyên từ 1 tới 6, vui lòng chọn lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Chức năng chọn phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
    }
}
