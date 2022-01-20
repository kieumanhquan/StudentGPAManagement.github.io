import java.io.*;
import java.util.Scanner;

public class Subject implements Serializable {
    private int idSubject;
    private String nameSubject;
    private int quantity;
    private String subjectType;

    public final static String TYPE_1 = "Đại cương";
    public final static String TYPE_2 = "Cơ sở ngành";
    public final static String TYPE_3 = "Chuyên ngành";

    private static int AUTO_IdSubject = 100;

    public Subject() {
    }

    public Subject(int idSubject, String nameSubject, int quantity, String subjectType) {
        this.idSubject = idSubject;
        this.nameSubject = nameSubject;
        this.quantity = quantity;
        this.subjectType = subjectType;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public static String getType1() {
        return TYPE_1;
    }

    public static String getType2() {
        return TYPE_2;
    }

    public static String getType3() {
        return TYPE_3;
    }

    public void inputSubjectInfo(){
        this.setIdSubject(Subject.AUTO_IdSubject);

        System.out.println("Nhập tên môn học: ");
        this.nameSubject = new Scanner(System.in).nextLine();

        boolean check = true;

        System.out.println("Nhập số đơn vị học trình: ");
        do {
            try {
                this.quantity = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (this.quantity <= 0 ){
                System.out.println("Số đơn vị học phần phải lớn hơn 0! Nhập lại:");
                check = false;
            }
        }while (!check);

        System.out.println("Nhập loại môn: ");
        System.out.println("1. Đại cương");
        System.out.println("2. Cơ sở ngành");
        System.out.println("3. Chuyên ngành");
        do {
            int choice = new Scanner(System.in).nextInt();
            if (choice <= 0 || choice > 3) {
                System.out.print("Nhập số từ 1 đến 3! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    this.setSubjectType(Subject.TYPE_1);
                    System.out.println("Đại cương");
                    check = true;
                    break;
                case 2:
                    this.setSubjectType(Subject.TYPE_2);
                    System.out.println("Cơ sở ngành");
                    check = true;
                    break;
                case 3:
                    this.setSubjectType(Subject.TYPE_3);
                    System.out.println("Chuyên ngành");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập từ 1 đến 3!");
                    check = false;
                    break;
            }
        } while (!check);

        Subject.AUTO_IdSubject++;
    }

    @Override
    public String toString() {
        return "Subject {" +
                " idSubject = " + idSubject +
                ", nameSubject = '" + nameSubject + '\'' +
                ", quantity = " + quantity +
                ", subjectType = " + subjectType +
                '}';
    }
}
