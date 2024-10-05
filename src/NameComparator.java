/*
 * Portfolio Final Program Option 1: Student Grades
 * Brian Gunther
 * CSC372: Programming II
 * Colorado State University Global
 * Dr. Vanessa Cooper
 * October 6, 2024
 * 
 */
import java.util.Comparator;

/**
 * Comparator class to be used in comparing Student names
 */
public class NameComparator implements Comparator<Student> {
	// Override the Comparator class "compare()" method to return the result of comparing two strings
	@Override
	public int compare(Student first, Student second) {
		return first.getName().compareTo(second.getName());
	}
}
