/*
 * Portfolio Final Program Option 1: Student Grades
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * October 6, 2024
 * 
 */
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Provides a user interface to store student data in a linked list, with options to display or save the sorted list.
 */
public class StudentGPAList {

	/**
	 * Prompts the user for input to populate a student object with name, address, and GPA
	 * @return the populated student object
	 */
	public static Student addStudent() {
		Scanner s = new Scanner(System.in);
		
		Student student = new Student();
		Double gpa = null;
		String name = "";
		String address = "";

		System.out.println("Entering Student Information");
		
		while (name.isEmpty()) {
			System.out.print("Name: ");
			name = s.nextLine();
			if (name.isEmpty()) {
				System.out.println("Name is empty. Please enter the student's name.");
			}	
		}
		student.setName(name);
		
		while (address.isEmpty()) {
			System.out.print("Address: ");
			address = s.nextLine();
			if (address.isEmpty()) {
				System.out.println("Address is empty. Please enter the student's address.");
			}
		}
		student.setAddress(address);
		
		// This model of GPA assumes a scale from 0.0 to 4.0
		System.out.print("GPA: ");
		while (gpa == null || gpa < 0.0 || gpa > 4.0) {
			try {
				gpa = s.nextDouble();
				if (gpa < 0.0 || gpa > 4.0) {
                    System.out.print("Invalid input. Please enter a value between 0.0 and 4.0: ");
                    s.nextLine();
                } else {
                	// Use the BigDecimal class to perform truncation to 3 decimals
                	BigDecimal roundingCheck = BigDecimal.valueOf(gpa);
                	gpa = roundingCheck.setScale(3, RoundingMode.DOWN).doubleValue();
                    student.setGPA(gpa);
                }	
			} catch (InputMismatchException e) {
				System.out.print("Invalid input. Please enter a decimal value between 0.0 and 4.0 (e.g., 3.5): ");
				s.nextLine();
			}
		}
		s.nextLine();
		
		System.out.println();
		
		return student;
	}
	
	/**
	 * A simple user interface menu
	 */
	public static void menu() {
		System.out.println("*** Student Management Menu ***");
		System.out.println("1. Add student information");
		System.out.println("2. Display students (sorted)");
		System.out.println("3. Save to file (sorted)");
		System.out.println("4. Exit");
		System.out.print("Please enter a number 1 - 4 and press Enter/Return: ");
	}
	
	/**
	 * Main method instantiates a linked list and triggers the user interface loop
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		LinkedList<Student> studentList = new LinkedList<Student>();
		FileOutputStream fileStream = null;
		PrintWriter outFS = null;
		String outputFilename = "student_list.txt";
		
		String input;
		int menuOption = 0;
		Scanner s = new Scanner(System.in);
		
		// Prompt user with menu options until the exit case is entered
		do {
			menu();
			input =  s.nextLine().trim();
			// Check for empty input
			if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid menu option.");
                System.out.println();
                continue;
			}
			// Check to ensure the the menu option can be parsed as integer
            try {
                menuOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.println();
                continue;
            }

            // Check for integer menu options 1-4
			System.out.println();
			switch (menuOption){
				case 1:
					// Add student
					Student addedStudent = addStudent();
					studentList.add(addedStudent);
					System.out.println("Successfully added student: ");
					addedStudent.printInfo();
					System.out.println();
					break;
				
				case 2:
					// Sort the list and display to console
					MSort.sort(studentList, 0, studentList.size() - 1);
					for (int i = 0; i < studentList.size(); ++i) {
						studentList.get(i).printInfo();
						System.out.println();
					}
					break;
				
				case 3:
					// Sort the list and save to a text file
					MSort.sort(studentList, 0, studentList.size() - 1);
					try {
						fileStream = new FileOutputStream(outputFilename);
					} catch (FileNotFoundException e) {
						System.out.println("Error: Could not open the file " + outputFilename);
						break;
					}
					outFS = new PrintWriter(fileStream);
					for (int i = 0; i < studentList.size(); ++i) {
						outFS.println("Name: " + studentList.get(i).getName());
						outFS.println("Address: " + studentList.get(i).getAddress());
						outFS.println("GPA: " + studentList.get(i).getGPA());
						outFS.println();
					}
					outFS.flush();
					outFS.close();
					System.out.println("Student list was saved to " + outputFilename);
					System.out.println();
					break;
					
				case 4:
					// Exit message
					System.out.println("Exiting.");
					break;
				
				default:
					System.out.println("You must enter a number 1 - 4");
					System.out.println();
					break;
			}
		} 
		// Exit the program if the user enters '4'
		while (menuOption != 4);
		
		s.close();
	}
}
