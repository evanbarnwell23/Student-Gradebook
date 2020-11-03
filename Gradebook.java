import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Gradebook {

    // Array of courses students can enter grades for
    final String[] COURSES = {"ECO 3203", "MAC 2313", "COP 2006", "ECO 3101", "IDS 3920"};
    // HashMap that stores grades entered by the student for all courses
    Map<String, Double> courseGrades = new HashMap<>();
    Scanner scan = new Scanner(System.in);

    public Gradebook() {}

    public void printPastEntries(List<String> students, String studentName) {
        /* This function asks the student if they would like to see their grades after entering grades
         * @param students List of strings which are the lines of the text file
         * @param studentName The name of the student looking for their grades
         */
        System.out.println("Would you like to see your grades, including the ones you just entered? (y/n): ");
        String userInput = scan.nextLine();
        if (userInput.toLowerCase().startsWith("y")) {
            printStudent(students, studentName);
        }
    }

    public void printStudent(List<String> students, String studentName) {
        /* This function prints out a student's grades from 'gradebook.txt'
         * @param students List of strings which are the lines of the text file
         * @param studentName The name of the student looking for their grades
         */
        if (students.size() == 0) {
            // This prints out an error message if the file is empty
            System.out.println("Error. No grades found.");
        } else {
            System.out.println("Here are " + studentName + "'s grades: ");
            for (String student : students) {
                if (student.toLowerCase().contains(studentName.toLowerCase())) {
                    // This prints out every grade associated with the student currently entering data
                    System.out.println(student);
                } else {
                    // This prints an error message if the student has no grades on record
                    System.out.println(studentName + " has no grades on record.");
                }
            }
        }
    }

    public void printCourses() {
        /* This function prints out the courses to select from
         *
         */
        for (int i = 0; i < COURSES.length; i++) {
            System.out.println((i + 1) + ") " + COURSES[i]);
        }
    }

    public void calculateGrades(Map<String, Double> currentCourse, int selectedCourse) {
        /* This function calculates the student's grades in the class using hard-coded grade weight
         * @param currentCourse HashMap which contains the student's average grade for each assignment type in the
         *  current course
         * @param selectedCourse Stores the index value of whichever course the student is entering grades for
         */
        courseGrades.put(COURSES[selectedCourse], (currentCourse.get("Quiz") * .2) +
                (currentCourse.get("Exam") * .3) + (currentCourse.get("Homework") * .25) +
                (currentCourse.get("Project") * .25));
    }

}
