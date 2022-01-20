import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable {
    private static Students[] students;
    private static Subject[] subjects;
    private static MarkSheet[] markSheets;
    private static WriteAndRead writeAndRead = new WriteAndRead();
    private static Scanner scanner;

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    createNewStudent();
                    break;
                case 2:
                    createNewSubject();
                    break;
                case 3:
                    markSheetGpa();
                    break;
                case 4:
                    sortMarkSheet();
                    break;
                case 5:
//                    calculate();
                    break;
                case 6:
                    System.exit(0);
            }

        } while (true);
    }

    private static int functionChoice() {
        System.out.println("--------Quản lý điểm sinh viên--------");
        System.out.println("1.Nhập danh sách sinh viên");
        System.out.println("2.Nhập danh sách môn học");
        System.out.println("3.Nhập điểm bảng điểm cho sinh viên");
        System.out.println("4.Sắp xếp bảng điểm");
        System.out.println("5.Tính điểm và số đơn vị học trình cho sinh viên");
        System.out.println("6.Thoát");

        int functionChoice = 0;
        boolean check = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (functionChoice < 1 || functionChoice > 6) {
                System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
                check = false;
            }
        } while (!check);
        return functionChoice;
    }

    private static void rececalculateipt() {
        for (MarkSheet markSheet : markSheets){
            System.out.println("Thống điểm cho sinh viên "+ markSheet.getStudents().getName());
            System.out.println(markSheet.getMarks());
        }
    }

    private static void sortMarkSheet() {
        boolean check = true;
        if (markSheets == null || markSheets.length == 0){
            System.out.println("Bạn cần nhập danh sách điểm sinh viên và môn học trước khi sắp xếp");
            return;
        }
        do {
            int sortChoice = 0;
            System.out.println("---------- SẮP XẾP BẢNG ĐIỂM ---------");
            System.out.println("1. Theo tên sinh viên");
            System.out.println("2. Theo theo tên môn học");
            System.out.println("3. Thoát chức năng sắp xếp.");
            System.out.print("Xin mời chọn chức năng: ");
            do {
                try {
                    sortChoice = new Scanner(System.in).nextInt();
                    check = true;
                } catch (Exception e) {
                    System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                    check = false;
                    continue;
                }
                if (sortChoice < 1 || sortChoice > 3) {
                    System.out.print("Nhập trong khoảng từ 1 đến 3! Nhập lại: ");
                    check = false;
                }
            } while (!check);
            switch (sortChoice) {
                case 1:
                    sortByNameStudent();
                    break;
                case 2:
                    sortByNameSubject();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    private static void sortByNameStudent() {
        for (int i = 0; i < markSheets.length - 1; i++){
            for (int j = i + 1; j < markSheets.length; j++){
                if (markSheets[i].getStudents().getName().compareTo(markSheets[j].getStudents().getName()) > 0){
                    MarkSheet markSheet = markSheets[i];
                    markSheets[i] = markSheets[j];
                    markSheets[j] = markSheet;
                }
            }
        }
        for (MarkSheet markSheets : markSheets){
            System.out.println(markSheets);
        }
    }

    private static void sortByNameSubject() {
        for (int i = 0; i < markSheets.length - 1; i++){
            for (int j = i + 1; j < markSheets.length; j++){
                if (markSheets[i].getSubjects().getNameSubject().compareTo(markSheets[j].getSubjects().getNameSubject()) > 0){
                    MarkSheet markSheet = markSheets[i];
                    markSheets[i] = markSheets[j];
                    markSheets[j] = markSheet;
                }
            }
        }
        for (MarkSheet markSheets : markSheets){
            System.out.println(markSheets);
        }
    }

    public static boolean isValidStudentAndSubject() {
        return students != null && subjects != null && students.length != 0 && subjects.length != 0;
    }

//    private static boolean markSheetGpa() {
//        boolean check = true;
//        if (!isValidStudentAndSubject()) {
//            System.out.println("Cần nhập thông tin sinh viên và môn học trước khi nhập điểm: ");
//            return false;
//        }
//
//        int n = 0;
//        do {
//            try {
//                System.out.println("Nhập số lượng sinh viên muốn nhập điểm: ");
//                n = new Scanner(System.in).nextInt();
//                check = true;
//            } catch (Exception e) {
//                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
//                check = false;
//                continue;
//            }
//            if (n <= 0) {
//                System.out.println("Số lượng sinh viên phải lớn hơn 0! Nhập lại:");
//                check = false;
//            }
//        } while (!check);
//
//        markSheets = new MarkSheet[n];
//        for (int i = 0; i < markSheets.length; i++) {
//            float marks = 0;
////            int roomRest;
////            int day = 0;
//            System.out.println("Nhập id sinh viên muốn ghi điểm: ");
//            int studentId;
//            Subject subject;
//            Students students;
//            do {
//                try {
//                    studentId = new Scanner(System.in).nextInt();
//                    check = true;
//                } catch (Exception e) {
//                    System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
//                    check = false;
//                    continue;
//                }
//                students = searchStudents(studentId);
//                int retry = 1;
//                if (students != null && students.getIdSV() == studentId) {
//                    System.out.println("Sinh viên " + students.getName() + " là: ");
////                    System.out.println(customer.getRoomTypeRent());
//                    System.out.println("Số phòng loại " + customer.getRoomTypeRent() + " khách hàng muốn thuê là: ");
//                    System.out.println(customer.getRoomNumberRent());
//
//                    subject = searchSubjectType(subject.getRoomTypeRent());
//                    assert subject != null;
//                    System.out.println("Số môn " + subject.getRoomType() + " hiện có:");
//                    System.out.println(room.getRoomNumber());
//                    if (room.getRoomNumber() >= customer.getRoomNumberRent()) {
//                        roomRest = room.getRoomNumber() - customer.getRoomNumberRent();
//                        room.setRoomNumber(roomRest);
//                    } else {
//                        return suggestOtherRoom(customer, room, retry);
//                    }
//                    break;
//                }
//                System.out.print("Không có sinh viên nào có ID vừa nhập, vui lòng nhập lại: ");
//            } while (true);
//
//
//            marks = customer.getRoomNumberRent() * room.getRentRate() * day;
//            RoomChoice roomChoice = new RoomChoice(customer, room, customer.getRoomNumberRent(), day);
//            roomChoices[i] = roomChoice;
//            roomChoice.setMark(marks);
//        }
//        writeAndRead.writeFileRoomChoice(roomChoices);
//        writeAndRead.readFileRoomChoice();
//        return true;
//    }

    public static void markSheetGpa()
    {
        int countStudent = -1, countSubject = -1;
        countStudent = students.length;
        countSubject = subjects.length;
        if(countStudent == 0 || countSubject == 0)
        {
            System.out.println("Nhap thong tin sinh vien va mon hoc truoc!");
        }
        else {
            markSheets = new MarkSheet[countStudent];
            for (int i = 0; i < countStudent; i++) {
                boolean check = false;
                Students localStudent = students[i];
                System.out.print("Nhap so mon hoc cho sinh vien "+localStudent.getIdSV()+": ");
                int slMonHoc;
                do {
                    slMonHoc = scanner.nextInt();
                    if (slMonHoc > countSubject) {
                        System.out.print("Nhap qua so luong mon hoc!");
                        check = true;
                    } else if (slMonHoc < 1) {
                        System.out.println("Nhap it nhat mot mon hoc");
                        check = true;
                    } else
                        check = false;
                } while (check);
                Subject localSub[] = new Subject[slMonHoc];
                markSheets = new MarkSheet[slMonHoc];
                Subject[] subTemps = new Subject[slMonHoc];
                boolean check1 = true;
                for (int j = 0; j < slMonHoc; j++) {
                    Subject checksub;
                    System.out.println("Nhap ID mon hoc: ");
                    do {
                        int id = scanner.nextInt();
                        checksub = searchForSubject(subjects,id);
                        if(checksub != null)
                        {
                            subTemps[j] = checksub;
                            if(countSubjects(subTemps,id) >1)
                            {
                                System.out.println("Môn học "+id+" đã có! Nhập môn khác!");
                                check1 = true;
                            }
                            else
                            {
                                check1 = false;
                            }
                        }
                        else {
                            System.out.print("Nhap lai id: ");
                            check1 = true;
                        }
                    }while (check1);
                    System.out.println("Nhap diem:");
                    int diem = -1;
                    do {
                        diem = scanner.nextInt();
                        if(diem < 0 || diem > 10)
                            System.out.println("Vui long nhap diem trong doan [0,10] !");
                    }while (diem < 0 || diem > 10);
                    localSub[j] = checksub;
                    System.out.println("===========");
//                    markSheets[j] = diem;
                }
                markSheets[i] = new MarkSheet();
            }
            showMarks();
        }
    }

    public static void showMarks()
    {
        for (int i = 0; i < markSheets.length; i++) {
            System.out.println(markSheets[i].toString());
        }
    }

    public static Subject searchForSubject(Subject[] subjects,int name)
    {
        for (int i = 0; i < subjects.length; i++) {
            if(subjects[i].getIdSubject() == name)
                return subjects[i];
        }
        return null;
    }

    public static int countSubjects(Subject[] subjects,int id)
    {
        int count = 0;
        for (int i = 0; i < subjects.length; i++) {
            if(subjects[i] != null)
            {
                if(subjects[i].getIdSubject() == id)
                    count++;
            }
        }
        return count;
    }

    private static Students searchStudents(int studentId) {
        for (Students student : students) {
            if (student.getIdSV() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static Subject searchSubjectType(String subjectType) {
        for (Subject subject : subjects) {
            if (subject.getSubjectType().equals(subjectType)) {
                return subject;
            }
        }
        return null;
    }

    private static void createNewSubject() {
        boolean check = true;
        System.out.println("Nhập số lượng môn học muốn thêm: ");
        int countSubject = 0;
        do {
            try {
                countSubject = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countSubject <= 0 ){
                System.out.println("Số phòng phải lớn hơn 0! Nhập lại:");
                check = false;
            }
        }while (!check);

        subjects = new Subject[countSubject];
        for (int i = 0; i < subjects.length; i++) {
            Subject subject = new Subject();
            subject.inputSubjectInfo();
            subjects[i] = subject;
        }
        writeAndRead.writeFileSubject(subjects);
        writeAndRead.readFileSubject();
    }

    private static void createNewStudent() {
        boolean check = true;
        System.out.println("Nhập số lượng sinh viên muốn thêm: ");
        int countStudents = 0;
        do {
            try {
                countStudents = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countStudents <= 0 ){
                System.out.println("Số khách hàng phải lớn hơn 0! Nhập lại:");
                check = false;
            }
        }while (!check);
        students = new Students[countStudents];
        for (int i = 0; i < students.length; i++) {
            Students student = new Students();
            student.inputInfo();
            students[i] = student;
        }
        writeAndRead.writeFileStudent(students);
        writeAndRead.readFileStudent();
    }

}
