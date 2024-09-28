/**
 * Student object contains information about a single student
 */
public class Student {
	private String name;
	private String address;
	private double GPA;
	
	/**
	 * Default constructor for list population
	 */
	public Student(String name, String address, double GPA) {
		this.name = name;
		this.address = address;
		this.GPA = GPA;
	}
	
	/**
	 * @return the student's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the student's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the student's address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the student's address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the student's GPA
	 */
	public double getGPA() {
		return GPA;
	}
	/**
	 * @param GPA the student's GPA
	 */
	public void setGPA(double GPA) {
		this.GPA = GPA;
	}
	/**
	 * Print the student information to console
	 */
	public void printInfo() {
		System.out.println("Name: " + this.name);
		System.out.println("Address: " + this.address);
		System.out.println("GPA: " + this.GPA);
	}
	
}
