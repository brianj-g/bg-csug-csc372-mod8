import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentGradeBook {

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
                    System.out.println("Invalid input. Please enter a value between 0.0 and 4.0.");
                } else {
                	// Use the BigDecimal class to perform rounding to 2 decimals
                	BigDecimal roundingCheck = new BigDecimal(gpa);
                	gpa = roundingCheck.setScale(4, RoundingMode.HALF_UP).doubleValue();
                    student.setGPA(gpa);
                }	
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a decimal value between 0.0 and 4.0 (e.g., 3.5).");
				s.nextLine();
			}
		}
		s.nextLine();
		
		System.out.println();
		
		return student;
	}
	
	public static void menu() {
		System.out.println("*** Student Management Menu ***");
		System.out.println("1. Add student information");
		System.out.println("2. Display students (sorted)");
		System.out.println("3. Save to file (sorted)");
		System.out.println("4. Exit");
		System.out.print("Please enter a number 1 - 4 and press Enter/Return: ");
	}
	
	public static void main(String[] args) {
		// TODO: Instantiate Linked List; Input loop; Sort list; Output to file;
				LinkedList<Student> studentList = new LinkedList<Student>();
				
				int menuOption = 0;
				Scanner s = new Scanner(System.in);
				
				do {
					menu();
					try {
						menuOption =  s.nextInt();
					} catch (InputMismatchException e) {
						s.nextLine();
						System.out.println("Invalid input");
					}

					System.out.println();
					switch (menuOption){
						case 1:
							// Add student
							Student addedStudent = addStudent();
							studentList.add(addedStudent);
							
							System.out.println();
							break;
						
						case 2:
							// Sort the list and display to console
							for (int i = 0; i < studentList.size(); ++i) {
								studentList.get(i).printInfo();
								System.out.println();
							}
							break;
						
						case 3:
							// Sort the list and save to a text file
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
		// Strictly for testing
		MSort.testMain();
		
		s.close();

	}

}
