package service;

import model.Student;
import java.util.ArrayList;
import java.io.*;

public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

 // Add student without duplicate ID
    public void addStudent(Student student) {

        // Check duplicate ID
        for (Student s : students) {

            if (s.getId() == student.getId()) {

                System.out.println("Student ID already exists.");

                return;
            }
        }

        students.add(student);

        System.out.println("Student added successfully.");
    }
    // View all students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {

            String grade = calculateGrade(s.getMarks());

            System.out.println(s +
                    ", Grade: " + grade);
        }
    }
 // Search student by ID
    public void searchStudent(int id) {

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == id) {
                System.out.println("Student Found:");
                System.out.println(s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
 // Delete student by ID
    public void deleteStudent(int id) {

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == id) {

            	students.removeIf(student -> student.getId() == id);

                System.out.println("Student deleted successfully.");

                found = true;

                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
 // Update student details
    public void updateStudent(int id, String name,
                              int age, String course,
                              double marks) {

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == id) {

                s.setName(name);
                s.setAge(age);
                s.setCourse(course);
                s.setMarks(marks);

                System.out.println("Student updated successfully.");

                found = true;

                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }
 // Calculate grade
    public String calculateGrade(double marks) {

        if (marks >= 90) {
            return "A+";
        }

        else if (marks >= 80) {
            return "A";
        }

        else if (marks >= 70) {
            return "B";
        }

        else if (marks >= 60) {
            return "C";
        }
        
        else if(marks >= 50) {
        	return "D";
        }
        
        else if(marks >= 40) {
        	return "E";
        }

        else {
            return "Fail";
        }
    }
 // Save students to file
    public void saveStudentsToFile() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter("data/students.txt"));

            for (Student s : students) {

                writer.write(
                        s.getId() + "," +
                        s.getName() + "," +
                        s.getAge() + "," +
                        s.getCourse() + "," +
                        s.getMarks()
                );

                writer.newLine();
            }

            writer.close();

            System.out.println("Student data saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving file.");

            e.printStackTrace();
        }
    }
 // Load students from file
    public void loadStudentsFromFile() {

        try {

            File file = new File("data/students.txt");

            // If file does not exist
            if (!file.exists()) {
                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {
            	
            	 // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                // Check proper data length
                if (data.length < 5) {
                    continue;
                }

                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                String course = data[3].trim();
                double marks = Double.parseDouble(data[4].trim());

                Student student =
                        new Student(id,
                                name,
                                age,
                                course,
                                marks);

                students.add(student);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error loading file.");

            e.printStackTrace();
        }catch (NumberFormatException e) {

            System.out.println("Invalid data format in file.");

            e.printStackTrace();
        }
    }
}