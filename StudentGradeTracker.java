import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int[] marks = new int[5];

    // Constructor
    Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    // Calculate total marks
    int getTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    // Calculate average
    double getAverage() {
        return getTotal() / 5.0;
    }

    // Find highest mark
    int getHighest() {
        int max = marks[0];
        for (int mark : marks) {
            if (mark > max) 
            max = mark;
        }
        return max;
    }

    // Find lowest mark
    int getLowest() {
        int min = marks[0];
        for (int mark : marks) {
            if (mark < min) min = mark;
        }
        return min;
    }

    // Grade based on average
    char getGrade() {
        double avg = getAverage();
        if (avg >= 90) return 'A';
        else if (avg >= 80) return 'B';
        else if (avg >= 70) return 'C';
        else if (avg >= 60) return 'D';
        else return 'F';
    }

    // Print student report
    void printReport() {
        System.out.println("\n--- Student Report ---");
        System.out.println("Name: " + name);
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
        }
        System.out.println("Total Marks: " + getTotal());
        System.out.println("Average Marks: " + getAverage());
        System.out.println("Highest Marks: " + getHighest());
        System.out.println("Lowest Marks: " + getLowest());
        System.out.println("Grade: " + getGrade());
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\nStudent Grade Tracker");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Student Reports");
            System.out.println("3. Display Summary Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    int[] marks = new int[5];
                    for (int i = 0; i < 5; i++) {
                        System.out.print("Enter marks for Subject " + (i + 1) + ": ");
                        marks[i] = scanner.nextInt();
                    }

                    Student student = new Student(name, marks);
                    students.add(student);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student data available.");
                    } else {
                        for (Student s : students) {
                            s.printReport();
                        }
                    }
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No data to summarize.");
                    } else {
                        generateSummary(students);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using Student Grade Tracker. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to generate summary report
    public static void generateSummary(ArrayList<Student> students) {
        double totalClassAverage = 0;
        double highestAvg = Double.MIN_VALUE;
        double lowestAvg = Double.MAX_VALUE;

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (Student s : students) {
            double avg = s.getAverage();
            totalClassAverage += avg;

            if (avg > highestAvg) highestAvg = avg;
            if (avg < lowestAvg) lowestAvg = avg;

            switch (s.getGrade()) {
                case 'A': countA++; break;
                case 'B': countB++; break;
                case 'C': countC++; break;
                case 'D': countD++; break;
                case 'F': countF++; break;
            }
        }

        int totalStudents = students.size();
        double classAvg = totalClassAverage / totalStudents;

        System.out.println("\nSummary Report");
        System.out.println("Total Students: " + totalStudents);
        System.out.printf("Class Average: %.2f\n", classAvg);
        System.out.printf("Highest Average: %.2f\n", highestAvg);
        System.out.printf("Lowest Average: %.2f\n", lowestAvg);

        System.out.println("\n--- Grade Distribution ---");
        System.out.println("A: " + countA);
        System.out.println("B: " + countB);
        System.out.println("C: " + countC);
        System.out.println("D: " + countD);
        System.out.println("F: " + countF);
    }
}
