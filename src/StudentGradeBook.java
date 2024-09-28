import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeBook {

	public static Student newStudent(int c) {
		Scanner s = new Scanner(System.in);
		
		Student student = new Student();
		Double gpa = null;
		String name = "";
		String address = "";

		System.out.println("Entering Student Information");
		
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
                	gpa = roundingCheck.setScale(2, RoundingMode.HALF_UP).doubleValue();
                    student.setGPA(gpa);
                }	
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a decimal value between 0.0 and 4.0 (e.g., 3.5).");
				s.nextLine();
			}
		}
		s.nextLine();

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
		
		System.out.println();
		
		return student;
	}
	
	public static void main(String[] args) {
		// TODO: Instantiate Linked List; Input loop; Sort list; Output to file;
		
		// Strictly for testing
		MSort.testMain();

	}

}
