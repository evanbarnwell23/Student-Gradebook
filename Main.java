import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        Gradebook gradebook = new Gradebook();
        // Initializes instances of FileHandler and Gradebook classes

        System.out.println("Welcome to Student Gradebook!");
        System.out.println("Enter your name to begin: ");
        // Takes student name as user input
        Student student = new Student(scan.nextLine());
        // Initializes instances of Student class

        boolean running = true;
        while (running) {
            // While loop that runs while student is viewing old grades or entering new grades
            System.out.println("Would you like to search for old grades or enter new? (o/n): ");
            // Determines if student would like to view old grades or enter new grades
            String userInput = scan.nextLine().toLowerCase();
            if (userInput.startsWith("o")) {
                // Reads file and prints out student's existing grades
                gradebook.printStudent(fileHandler.readFile(), student.name);
                running = false;
            } else if (userInput.startsWith("n")) {
                // Reads file and prints courses to select from
                fileHandler.readFile();
                gradebook.printCourses();
                boolean entering = true;
                while (entering) {
                    // While loop that runs while student is entering new grades
                    student.selectCourse();
                    gradebook.courseGrades.put(gradebook.COURSES[student.selectedCourse], 0.0);
                    student.enterGrades();
                    gradebook.calculateGrades(student.currentCourse, student.selectedCourse);
                    // Determines if student would like to continue entering grades for other classes
                    entering = student.checkIfEntering();
                }
                // Writes grades which were just entered to file
                fileHandler.writeToFile(student.name, gradebook.courseGrades);
                // Determines if student would like to see previous grades
                gradebook.printPastEntries(fileHandler.readFile(), student.name);
                running = false;
            } else {
                // Gets student to input again if 'o' or 'n' are not the input
                System.out.println("Error. Input not recognized.");
            }
        }

    }

}
