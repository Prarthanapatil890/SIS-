import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Student {
    private int id;
    private int moddleid;
    private String name;
    private int rollno; // Replaced "age" with "rollno"
    private String course;
    private int sem;

    public Student(int id, int moddleid, String name, int rollno, String course, int sem) {
        this.id = id;
        this.moddleid = moddleid;
        this.name = name;
        this.rollno = rollno;
        this.course = course;
        this.sem = sem;
    }

    public int getId() {
        return id;
    }

    public int getModdleId() {
        return moddleid;
    }

    public String getName() {
        return name;
    }

    public int getRollno() { // Replaced "getAge" with "getRollno"
        return rollno;
    }

    public String getCourse() {
        return course;
    }

    public int getSem() {
        return sem;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Moddle ID: " + moddleid + ", NAME: " + name + ", Rollno: " + rollno + ", Course: " + course + ", SEM: " + sem;
    }
}

public class StudentInformationSystem {
    private static List<Student> studentList = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nA.P. SHAH INSTITUTE OF TECHNOLOGY, THANE");
            System.out.println("\nStudent Information System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Export to Excel File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    exportToCSV();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student moddle id: ");
        int moddleid = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student rollno: "); // Changed prompt from "age" to "rollno"
        int rollno = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student course: ");
        String course = scanner.nextLine();
        System.out.print("Enter Semester No: ");
        int sem = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = new Student(nextId++, moddleid, name, rollno, course, sem);
        studentList.add(student);
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        System.out.println("\nList of Students:");
        System.out.format("%-5s %-15s %-25s %-5s %-15s %-10s\n", "Sr.NO", "Moddle ID", "Name", "Rollno", "Course", "Semester");

        for (Student student : studentList) {
            System.out.format("%-5d %-15d %-25s %-5d %-15s %-10d\n",
                    student.getId(), student.getModdleId(), student.getName(), student.getRollno(), student.getCourse(), student.getSem());
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter the ID of the student to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.print("Enter new student moddle id: ");
                int newmoddleid = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new rollno: "); // Changed prompt from "age" to "rollno"
                int newRollno = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.print("Enter new course: ");
                String newCourse = scanner.nextLine();
                System.out.print("Enter new Semester No: ");
                int newsem = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                studentList.remove(student);
                student = new Student(id, newmoddleid, newName, newRollno, newCourse, newsem);
                studentList.add(student);

                System.out.println("Student updated successfully.");
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the ID of the student to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (Student student : studentList) {
            if (student.getId() == id) {
                studentList.remove(student);
                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student with ID " + id + " not found.");
    }

    private static void exportToCSV() {
        // Specify the file path
        String filePath = "StudentData.csv";

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write headers
            String[] headers = {"ID", "Moddle ID", "Name", "Rollno", "Course", "Semester"};
            for (int i = 0; i < headers.length; i++) {
                writer.append(headers[i]);
                if (i < headers.length - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");

                    // Write data rows
            for (Student student : studentList) {
                String[] data = {
                    String.valueOf(student.getId()),
                    String.valueOf(student.getModdleId()),
                    student.getName(),
                    String.valueOf(student.getRollno()), // Replaced "getAge" with "getRollno"
                    student.getCourse(),
                    String.valueOf(student.getSem())
                };
                for (int i = 0; i < data.length; i++) {
                    writer.append(data[i]);
                    if (i < data.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }

            System.out.println("Data exported to " + filePath + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
