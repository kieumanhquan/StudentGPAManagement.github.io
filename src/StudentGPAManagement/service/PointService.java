package StudentGPAManagement.service;



import StudentGPAManagement.entity.Student;
import StudentGPAManagement.entity.Subject;
import StudentGPAManagement.entity.table.PointTable;
import StudentGPAManagement.entity.table.SubjectPoint;
import StudentGPAManagement.main.MainRun;
import StudentGPAManagement.util.DataUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PointService {
    public static final String POINT_DATA_FILE = "pointTable.dat";

    private static boolean isNullOfEmptyStudentOrSubject() {
        return MainRun.students.length == 0 || MainRun.subjects.length == 0;
    }

    public void creatPointTable() {

        // Thêm 1 bảng điểm gốm nhiều môn học cho 1 sv
        if (isNullOfEmptyStudentOrSubject()) {
            System.out.println("Bạn cần nhập thông tin sinh viên và môn học trước khi thêm bảng điểm.");
            return;
        }
        Student student = inputStudentId();

        int subjectNumber = inputSubjectNumber();
        PointTable pointTable = findPointTable(student.getIdSV());

        if (DataUtil.isNullOrEmpty(pointTable)) {
            // Thêm mới
            SubjectPoint[] subjectPoints = new SubjectPoint[subjectNumber];
            createSubjectPoint(subjectPoints, subjectNumber, student);
            addPointTableToArray(new PointTable(student, subjectPoints));

        } else {
            // uodate bảng điểm sv đã có trong hệ thống
            SubjectPoint[] subjectPointExits = pointTable.getSubjectPoints();
            int lenSPE = subjectPointExits.length;
            SubjectPoint[] subjectPoints = new SubjectPoint[subjectNumber + lenSPE];
            System.arraycopy(subjectPointExits, 0, subjectPoints, 0, lenSPE);
            updateOrAddSubjectPointExits(subjectPoints, subjectNumber, student);

            updatePointTableToArray(new PointTable(student, subjectPoints), findIndexPointTableExisted(student.getIdSV()));
        }
        MainRun.fileUtil.writeDataToFile(MainRun.pointTables, POINT_DATA_FILE);
    }

    private void updatePointTableToArray(PointTable pointTable, int indexPointTableExisted) {
        MainRun.pointTables[indexPointTableExisted] = pointTable;
    }

    private void updateOrAddSubjectPointExits(SubjectPoint[] subjectPoints, int subjectNumber, Student student) {
        for (int j = 0; j < subjectNumber; j++) {
            Subject subject = inputSubjectId(j, student);
            float subjectPointNumber = inputSubjectPointNumber(subject, student);
            int index = findIndexSubjectPointExisted(subject.getIdSubject(), student.getIdSV());
            SubjectPoint subjectPoint = new SubjectPoint(subject, subjectPointNumber);
            if (index >= 0) {
                subjectPoints[index] = subjectPoint;
            } else {
                for (int i = 0; i < subjectPoints.length; i++) {
                    if (DataUtil.isNullOrEmpty(subjectPoints[i])) {
                        subjectPoints[i] = subjectPoint;
                        break;
                    }
                }
            }
        }
    }

    private void addPointTableToArray(PointTable pointTable) {
        for (int k = 0; k < MainRun.pointTables.length; k++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[k])) {
                MainRun.pointTables[k] = pointTable;
                break;
            }
        }
    }

    private void createSubjectPoint(SubjectPoint[] subjectPoints, int subjectNumber, Student student) {
        for (int j = 0; j < subjectNumber; j++) {
            Subject subject = inputSubjectId(j, student);
            float subjectPointNumber = inputSubjectPointNumber(subject, student);
            SubjectPoint subjectPoint = new SubjectPoint(subject, subjectPointNumber);
            addSubjectPointToArray(subjectPoint, subjectPoints);
        }
    }

    private float inputSubjectPointNumber(Subject subject, Student student) {
        System.out.print("Nhập số điểm của môn " + subject.getNameSubject() + " mà sinh viên " + student.getFullName() + " đã có(hoặc update): ");
        float subjectPoint = -1;
        do {
            try {
                subjectPoint = new Scanner(System.in).nextFloat();
                if (subjectPoint >= 0 && subjectPoint <= 10) {
                    break;
                }
                System.out.print("Số điểm cho 1 môn học là số nguyên dương và không được lớn hơn 10 và Không bé hơn 0, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số điểm muốn dạy phải là một số thực dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return subjectPoint;
    }

    private Subject inputSubjectId(int j, Student student) {
        System.out.print("Nhập ID môn học thứ " + (j + 1) + "  mà sinh viên " + student.getFullName() + " đã có điểm: ");
        Subject subject;
        do {
            try {
                int subjectId = new Scanner(System.in).nextInt();
                subject = MainRun.subjectService.findSubjectById(subjectId);
                if (!DataUtil.isNullOrEmpty(subject)) {
                    break;
                }
                System.out.print("ID môn học vừa nhập không tồn tại trong hệ thống, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("ID môn học phải là số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return subject;
    }

    private void addSubjectPointToArray(SubjectPoint subjectPoint, SubjectPoint[] subjectPoints) {
        for (int k = 0; k < subjectPoints.length; k++) {
            if (DataUtil.isNullOrEmpty(subjectPoints[k])) {
                subjectPoints[k] = subjectPoint;
                break;
            }
        }
    }

    private int inputSubjectNumber() {
        System.out.print("Nhập số lượng môn học mà sinh viên đã có điểm : ");
        int subjectNumber = -1;
        do {
            try {
                subjectNumber = new Scanner(System.in).nextInt();
                if (subjectNumber > 0) {
                    break;
                }
                System.out.print("Số môn phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số môn là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
        return subjectNumber;
    }

    private Student inputStudentId() {
        System.out.print("Nhập ID của sinh viên" + " mà bạn muốn thêm điểm: ");
        Student student;
        do {
            try {
                int studentId = new Scanner(System.in).nextInt();
                student = MainRun.studentService.findStudentById(studentId);
                if (!DataUtil.isNullOrEmpty(student)) {
                    break;
                }
                System.out.print("ID sinh viên vừa nhập không tồn tại trong hệ thống, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("ID sinh viên phải là một số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return student;
    }


    private int findIndexSubjectPointExisted(int subjectId, int studentId) {
        PointTable pointTable = findPointTable(studentId);
        if (DataUtil.isNullOrEmpty(pointTable))
            return -1;
        SubjectPoint[] subjectPoints = pointTable.getSubjectPoints();
        for (int i = 0; i < subjectPoints.length; i++) {
            if (DataUtil.isNullOrEmpty(subjectPoints[i]))
                return -1;
            if (subjectPoints[i].getSubject().getIdSubject() == subjectId)
                return i;
        }
        return -1;
    }

    public PointTable findPointTable(int studentId) {
        for (int i = 0; i < MainRun.pointTables.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[i]))
                return null;
            if (MainRun.pointTables[i].getStudent().getIdSV() == studentId) {
                return MainRun.pointTables[i];
            }
        }
        return null;
    }

    public int findIndexPointTableExisted(int studentId) {
        for (int i = 0; i < MainRun.pointTables.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[i]))
                return -1;
            if (MainRun.pointTables[i].getStudent().getIdSV() == studentId) {
                return i;
            }
        }
        return -1;
    }


    public void showPointTable() {
        for (int i = 0; i < MainRun.pointTables.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[i])) {
                break;
            }
            System.out.println(MainRun.pointTables[i]);
        }
    }

    public void sortPointTableToNameStudent() {
        for (int i = 0; i < MainRun.pointTables.length - 1; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[i]))
                break;
            for (int j = i + 1; j < MainRun.pointTables.length; j++) {
                if (DataUtil.isNullOrEmpty(MainRun.pointTables[j]))
                    break;
                int compare = compareNameStudent(MainRun.pointTables[i], MainRun.pointTables[j]);
                if (compare > 0) {
                    swapPointTable(i, j);
                }
            }
        }
    }

    public void sortPointTableToNameSubject() {
        for (int i = 0; i < MainRun.pointTables.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[i]))
                break;
            for (int j = 0; j < MainRun.pointTables[i].getSubjectPoints().length - 1; j++) {
                if (DataUtil.isNullOrEmpty(MainRun.pointTables[i].getSubjectPoints()[j]))
                    break;
                for (int k = 0; k < MainRun.pointTables[i].getSubjectPoints().length; k++) {
                    if (DataUtil.isNullOrEmpty(MainRun.pointTables[i].getSubjectPoints()[k]))
                        break;
                    int compare = compareNameSubject(MainRun.pointTables[i].getSubjectPoints()[j]
                            , MainRun.pointTables[i].getSubjectPoints()[k]);
                    if (compare > 0) {
                        swapPointTable(i, j, k);
                    }
                }
            }
        }
    }

    private int compareNameSubject(SubjectPoint o1, SubjectPoint o2) {
        String[] ten1 = o1.getSubject().getNameSubject().split("\\s+");
        String[] ten2 = o2.getSubject().getNameSubject().split("\\s+");
        if (ten1[ten1.length - 1].equalsIgnoreCase(ten2[ten2.length - 1])) {
            return o1.getSubject().getNameSubject().compareToIgnoreCase(o2.getSubject().getNameSubject());
        } else {
            return ten1[ten1.length - 1].compareToIgnoreCase(ten2[ten2.length - 1]);
        }
    }

    private void swapPointTable(int i, int j, int k) {
        SubjectPoint tempSubjectPoint = MainRun.pointTables[i].getSubjectPoints()[j];
        MainRun.pointTables[i].getSubjectPoints()[j] = MainRun.pointTables[i].getSubjectPoints()[k];
        MainRun.pointTables[i].getSubjectPoints()[k] = tempSubjectPoint;
    }


    private void swapPointTable(int i, int j) {
        PointTable tempPointTable = MainRun.pointTables[i];
        MainRun.pointTables[i] = MainRun.pointTables[j];
        MainRun.pointTables[j] = tempPointTable;
    }

    public int compareNameStudent(PointTable o1, PointTable o2) {
        String[] ten1 = o1.getStudent().getFullName().split("\\s+");
        String[] ten2 = o2.getStudent().getFullName().split("\\s+");
        if (ten1[ten1.length - 1].equalsIgnoreCase(ten2[ten2.length - 1])) {
            return o1.getStudent().getFullName().compareToIgnoreCase(o2.getStudent().getFullName());
        } else {
            return ten1[ten1.length - 1].compareToIgnoreCase(ten2[ten2.length - 1]);
        }
    }

    public void pointCalculation() {
        for (int i = 0; i < MainRun.pointTables.length; i++) {
            if (DataUtil.isNullOrEmpty(MainRun.pointTables[i])) {
                break;
            }
            System.out.println(MainRun.pointTables[i]);
            System.out.println("Có điểm Tổng kết:" + MainRun.pointTables[i].getAvgPoint());
        }
    }

    public void initializePointTableData() {
        Object pointTableFromFile = MainRun.fileUtil.readDataFromFile(PointService.POINT_DATA_FILE);
        if (!DataUtil.isNullOrEmpty(pointTableFromFile)) {
            PointTable[] copyPointTables = (PointTable[]) pointTableFromFile;
            System.arraycopy(copyPointTables, 0, MainRun.pointTables, 0, copyPointTables.length);
        }
    }
}
