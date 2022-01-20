import java.io.*;

public class WriteAndRead {
    public static void writeFileStudent(Students[] students){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("StudentList.txt", true))) {
            outputStream.writeObject(students);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void readFileStudent(){
        Students[] studentArrayList = new Students[0];
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("StudentList.txt"))) {
            studentArrayList = (Students[]) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for (Students students : studentArrayList) {
            System.out.println(students);
        }
    }

    public void writeFileMarkSheet(MarkSheet[] markSheet){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("GPATableList.txt", true))) {
            outputStream.writeObject(markSheet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFileMarkSheet(){
        MarkSheet[] markSheetList = new MarkSheet[0];
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("GPATableList.txt"))) {
            markSheetList = (MarkSheet[]) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for (MarkSheet markSheet : markSheetList) {
            System.out.println(markSheet);
        }
    }

    public static void writeFileSubject(Subject[] subjects){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("SubjectList.txt", true))) {
            outputStream.writeObject(subjects);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void readFileSubject(){
        Subject[] subjects = new Subject[0];
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("SubjectList.txt"))) {
            subjects = (Subject[]) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for (Subject roomChoice : subjects) {
            System.out.println(subjects);
        }
    }
}
