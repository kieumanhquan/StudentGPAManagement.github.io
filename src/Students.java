import java.io.Serializable;
import java.util.Scanner;

public class Students implements Serializable  {
    private int idSV;
    private String name;
    private String address;
    private String phoneNumber;
    private String classRoom;

    private static int AUTO_IdSV = 10000;

    public Students(){
    }

    public Students(int idSV, String name, String address, String phoneNumber, String classRoom) {
        this.idSV = idSV;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classRoom = classRoom;
    }

    public int getIdSV() {
        return idSV;
    }

    public void setIdSV(int idSV) {
        this.idSV = idSV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }


    public void inputInfo(){
        this.setIdSV(Students.AUTO_IdSV);
        System.out.println("Nhập họ tên: ");
        this.name = new Scanner(System.in).nextLine();
        System.out.println("Nhập địa chỉ: ");
        this.address = new Scanner(System.in).nextLine();
        System.out.println("Nhập số điện thoại: ");
        this.phoneNumber = new Scanner(System.in).nextLine();
        System.out.println("Nhập lớp: ");
        this.classRoom = new Scanner(System.in).nextLine();
    }

    @Override
    public String toString() {
        return "Students { " +
                " idSV = '" + idSV + '\'' +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", classRoom = '" + classRoom + '\'' +
                '}';
    }
}
