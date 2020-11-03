import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileHandler {

    // List that contains each line in the file
    List<String> grades = new ArrayList<>();

    public FileHandler() {}

    public List<String> readFile() {
        /* This function reads the file and returns a list containing each line in the file
         *
         */
        try {
            FileReader reader = new FileReader("src/gradebook.txt");
            BufferedReader bReader = new BufferedReader(reader);
            String line;
            while ((line = bReader.readLine()) != null) {
                grades.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // Creates a new file if one is not found
            createFile();
            readFile();
        } catch (IOException e) {
            System.out.println("Error. Problem parsing file.");
            e.printStackTrace();
        }
        return grades;
    }

    public void createFile() {
        /* This function creates a new file 'gradebook.txt' if it does not exist yet
         *
         */
        File newFile = new File("gradebook.txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Problem parsing file.");
            e.printStackTrace();
        }
    }

    public void writeToFile(String studentName, Map<String, Double> courseGrades) throws IOException {
        /* This function writes the student name, course, grade, and letter grade to 'gradebook.txt'
         * @param studentName Name of the student
         * @param courseGrades HashMap containing the name of the course and the student's grade in the course
         */
        BufferedWriter bWriter = new BufferedWriter(new FileWriter("src/gradebook.txt", true));
        StringBuilder text = new StringBuilder();
        for (String key : courseGrades.keySet()) {
            if (courseGrades.get(key) != 0) {
                // Only writes courses for which a grade has been entered
                text.append(String.format(", %s: %s (%s)", key, courseGrades.get(key).toString(),
                        determineLetterGrade(courseGrades.get(key))));
            }
        }
        bWriter.write(studentName + text + "\n");
        bWriter.close();
    }

    public String determineLetterGrade(double courseGrade) {
        /* This function determines the student's letter grade given their grade as a double
         * @param courseGrade Student's grade in a course
         */
        if (courseGrade >= 90.0) {
            return "A";
        } else if (courseGrade >= 80.0) {
            return "B";
        } else if (courseGrade >= 70.0) {
            return "C";
        } else if (courseGrade >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

}
