package StudentGPAManagement.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Subject {
    public static int AUTO_ID = 100;

    public final static String TYPE_1 = "Đại cương";
    public final static String TYPE_2 = "Cơ sở ngành";
    public final static String TYPE_3 = "Chuyên ngành";

    private int idSubject;
    private String nameSubject;
    private int totalLesson;
    private String typeSubject;

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public String getTypeSubject() {
        return typeSubject;
    }

    public void setTypeSubject(String typeSubject) {
        this.typeSubject = typeSubject;
    }

    public void inputInfo() {
        this.idSubject = Subject.AUTO_ID;
        Subject.AUTO_ID++;

        System.out.println("Nhập tên môn học: ");
        this.nameSubject = new Scanner(System.in).nextLine();

        System.out.println("Nhập tên số đơn vị học trình: ");
        int tempTotalLesson = -1;
        do {
            try {
                tempTotalLesson = new Scanner(System.in).nextInt();
                if (tempTotalLesson > 0) {
                    this.totalLesson = tempTotalLesson;
                    break;
                }
                System.out.print("Tổng số tiết phải là số nguyên, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Tổng số tiết phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);

        System.out.println("Nhập loại môn: ");
        System.out.println("1. Đại cương");
        System.out.println("2. Cơ sở ngành");
        System.out.println("3. Chuyên ngành");
        System.out.print("Vui lòng chọn: ");
        do {
            try {
                int choice = new Scanner(System.in).nextInt();
                if (choice > 0 && choice < 4) {
                    switch (choice) {
                        case 1:
                            this.typeSubject = Subject.TYPE_1;
                            break;
                        case 2:
                            this.typeSubject = Subject.TYPE_2;
                            break;
                        case 3:
                            this.typeSubject = Subject.TYPE_3;
                            break;
                    }
                    break;
                }
                System.out.print("Loại môn là một số nguyên từ 1 tới 3, vui lòng chọn lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Loại môn chọn phải là một số nguyên, vui lòng nhập lại: ");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "idSubject=" + idSubject +
                ", nameSubject='" + nameSubject + '\'' +
                ", totalLesson=" + totalLesson +
                ", typeSubject='" + typeSubject + '\'' +
                '}';
    }
}
