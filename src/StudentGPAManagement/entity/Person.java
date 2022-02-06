package StudentGPAManagement.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Person {

    protected String fullName;
    protected String address;
    protected Number phone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Number getPhone() {
        return phone;
    }

    public void setPhone(Number phone) {
        this.phone = phone;
    }

    public void inputInfo() {
        System.out.println("Nhập tên sinh viên: ");
        this.fullName = new Scanner(System.in).nextLine();
        System.out.println("Nhập tên địa chỉ sinh viên: ");
        this.address = new Scanner(System.in).nextLine();
        System.out.println("Nhập số điện thoại sinh viên: ");
        int tempphone = -1;
        do {
            try {
                tempphone = new Scanner(System.in).nextInt();
                if (tempphone > 0) {
                    this.phone = tempphone;
                    break;
                }
                System.out.print("Các kí tự nhập vào phải là số nguyên, vui lòng nhập lại! ");
            } catch (InputMismatchException ex) {
                System.out.print("Các kí tự nhập vào phải là số nguyên, vui lòng nhập lại! ");
            }
        }while (true);
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", adress='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
