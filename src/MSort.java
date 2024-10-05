/*
 * Portfolio Final Program Option 1: Student Grades
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * October 6, 2024
 * 
 */

import java.util.LinkedList;
import java.util.Comparator;

/**
 * Contains static methods to perform merge sort
 */
public class MSort {
	// Instantiate a static NameComparator that can be passed to the recursive method
	private static Comparator<Student> nameCompare = new NameComparator();
	
	/**
	 * Merge method for the merge sort recursive algorithm
	 * 
	 * @param studentGrades
	 * @param nameCompare
	 * @param i
	 * @param j
	 * @param k
	 */
	public static void merge(LinkedList<Student> studentGrades, Comparator<Student> nameCompare, int i, int j, int k) {
		LinkedList<Student> tempList = new LinkedList<Student>(studentGrades);
		int mergedSize = k - i + 1;
		int position;
		int left;
		int right;
		
		// Set initial position to zero
		position = 0;
		
		// Set left position to i and right position to one greater than j
		left = i;
		right = j + 1;
		
		// Compare left and right and add the smallest value to the temporary list until the end of one of the partitions is reached
		while (left <= j && right <= k) {
			if (nameCompare.compare(studentGrades.get(left), studentGrades.get(right)) < 0){
				tempList.set(position, studentGrades.get(left));
				++left;
			} else {
				tempList.set(position, studentGrades.get(right));
				++right;
			}
			++position;
		}
		
		// Add any remaining values from left partition to the temp list (if on left side)
		while (left <= j) {
			tempList.set(position, studentGrades.get(left));
			++left;
			++position;
		}
		
		// Add any remaining values from the right partition to the temp list (if on right side)
		while (right <= k) {
			tempList.set(position, studentGrades.get(right));
			++right;
			++position;
		}
		
		// Transfer the values from temporary list back to the main list
		for (position = 0; position < mergedSize; ++position) {
			studentGrades.set(i + position, tempList.get(position));
		}
	}
	
	/**
	 * Merge sort recursive method
	 * 
	 * @param studentGrades
	 * @param i
	 * @param k
	 */
	public static void sort(LinkedList<Student> studentGrades, int i, int k) {
		int j;
		
		// Base case check: Recursion ends when i is no longer less than k
		if (i < k) {
			// Find the center point (floored)
			j = (i + k) / 2;
			
			// Sort left side
			sort(studentGrades, i, j);
			
			// Sort right side
			sort(studentGrades, j + 1, k);
			
			// Finally, the partitions can be merged
			merge(studentGrades, nameCompare, i, j, k);
		}
	}
	
	/**
	 * A method for testing the sort
	 */
	public static void testMain() {
        // Create a LinkedList to store Student objects
        LinkedList<Student> studentList = new LinkedList<>();
        
        // Create and add 10 Student objects to the list (names and addresses randomly generated)
        studentList.add(new Student("Eve", "202 Birch St", 3.9));
        studentList.add(new Student("Judy", "707 Spruce St", 3.0));
        studentList.add(new Student("Charlie", "789 Pine Rd", 3.2));
        studentList.add(new Student("David", "101 Maple St", 3.7));
        studentList.add(new Student("Grace", "404 Walnut St", 3.1));
        studentList.add(new Student("Alice", "123 Main St", 3.5));
        studentList.add(new Student("Frank", "303 Cedar Ln", 2.5));
        studentList.add(new Student("Heidi", "505 Elm St", 2.9));
        studentList.add(new Student("Ivan", "606 Willow Rd", 3.6));
        studentList.add(new Student("Bob", "456 Oak Ave", 2.8));
        
        sort(studentList, 0, studentList.size() - 1);
        
        for (Student student : studentList) {
        	student.printInfo();
        	System.out.println();
        }
        
	}
}
