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
        System.out.println("QU???N L?? TR??? L????NG CHO GI???NG VI??N TH???NH GI???NG");
        System.out.println("1. Nh???p danh s??ch m??n h???c m???i v?? in ra danh s??ch m??n h???c trong tr?????ng");
        System.out.println("2. Nh???p danh s??ch sinh vi??n m???i v?? in ra danh s??ch sinh vi??n trong tr?????ng");
        System.out.println("3. B???ng ??i???m cho sinh vi??n v?? in ra danh s??ch b???ng ??i???m");
        System.out.println("4. S???p x???p danh s??ch b???ng ??i???m theo t??n sinh  vi??n");
        System.out.println("5. S???p x???p danh s??ch b???ng ??i???m theo t??n m??n h???c");
        System.out.println("6. T??nh to??n ??i???m t???ng k???t");
        System.out.println("7. Tho??t");
        System.out.println("----------------------------------------------");
        System.out.print("Ch???n ch???c n??ng: ");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice >= 1 && choice <= 7) {
                    return choice;
                }
                System.out.print("Ch???c n??ng kh??? d???ng l?? m???t s??? nguy??n t??? 1 t???i 6, vui l??ng ch???n l???i: ");
            } catch (InputMismatchException ex) {
                System.out.print("Ch???c n??ng ch???n ph???i l?? m???t s??? nguy??n, vui l??ng nh???p l???i: ");
            }
        } while (true);
    }
}
