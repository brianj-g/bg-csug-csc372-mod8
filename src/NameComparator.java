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
