import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student {

    Scanner scan = new Scanner(System.in);
    // Student name
    String name;
    // HashMap that stores grades entered by the student for current course
    Map<String, Double> currentCourse = new HashMap<>();
    // Array of assignment types
    String[] assignments = {"Quiz", "Exam", "Homework", "Project"};
    // Index of the course student is entering grades for
    int selectedCourse;

    public Student(String name) {
        /* This function is the constructor for the student class
         * @param name Student's name
         */
        this.name = name;
        for (String assignment : assignments) {
            // Assigns grade for each assignment type to be 0.0
            currentCourse.put(assignment, 0.0);
        }
    }

    public void selectCourse() {
        /* This function allows the student to select which course they will be entering grades for
         *
         */
        System.out.println("Enter the number of the course you would like to enter grades for: ");
        int userInput;
        boolean selecting = true;
        while (selecting) {
            // While loop which loops until student has entered an appropriate value
            while (!scan.hasNextInt()) {
                // Makes sure the student entered an integer
                scan.next();
                System.out.println("Please enter an integer (1-5): ");
            }
            userInput = scan.nextInt();
            scan.nextLine();
            switch (userInput) {
                case 1: case 2: case 3: case 4: case 5:
                    selectedCourse = userInput - 1;
                    selecting = false;
                    break;
                default:
                    selecting = true;
                    // Makes sure the student entered an integer 1-5
                    break;
            }

        }

    }

    public void enterGrades() {
        /* This function allows the student to enter grades for the course they have selected
         *
         */
        for (String assignment : assignments) {
            double userInput = 1;
            int counter = -1;
            // Counts times through loop so the average for each assignment type can be found
            System.out.println("Enter your grade (0-100) for each " + assignment + ". When you are done entering" +
                    " grades for that assignment type, enter 0.");
            while (!scan.hasNextInt()) {
                // Makes sure the student entered an integer
                scan.next();
                System.out.println("Please enter an integer (0-100): ");
            }
            while (userInput != 0) {
                // Allows student to advance to next assignment type by entering 0
                userInput = scan.nextInt();
                scan.nextLine();
                if (userInput > 0 && userInput <= 100) {
                    currentCourse.put(assignment, currentCourse.get(assignment) + userInput);
                    // Adds user input to HashMap which stores the grades for the current course
                    counter++;
                } else {
                    // Makes sure the student entered a value 0-100
                    System.out.println("Please enter a value between 0-100: ");
                }
            }
            currentCourse.put(assignment, currentCourse.get(assignment) / counter);
            // Finds the average grade for each assignment type before putting it in the HashMap
        }
    }

    public boolean checkIfEntering() {
        /* This function checks to see if the student would like to continue entering grades for another class
         *
         */
        boolean entering;
        System.out.println("Would you like to enter grades for another class? (y/n): ");
        String userInput = scan.nextLine().toLowerCase();
        entering = userInput.startsWith("y");
        return entering;
    }

}
