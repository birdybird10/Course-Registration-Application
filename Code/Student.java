import java.util.Scanner;
import java.util.ArrayList;
//inherit from User abstract class and implement all StudentInterface methods
public class Student extends User implements StudentInterface, java.io.Serializable{

	private ArrayList<Course> myCourses;
	static Scanner myscanner= new Scanner(System.in);
	
	//constructor with assigned username and password
	public Student() {
		super();
		this.username = "";
		this.password = "";
		//this arraylist handles the courses a particular student has registered for
		this.myCourses = new ArrayList<Course>();
		this.directory = new CourseDirectory();
		
	}
	
	//constructor with 2 parameters
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.myCourses = new ArrayList<Course>();
		this.directory = new CourseDirectory();
	}
	
	// constructor with all parameters
	public Student(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.myCourses = new ArrayList<Course>();
		this.directory = new CourseDirectory();
	}
	
	//getter for courseList
	public ArrayList<Course> getCoursesList(){
		return this.myCourses;
	}
	
	@Override
	//view all courses
	public void viewCourses() {
		directory.viewCoursesForStudent();
		
	}

	@Override
	//view courses that are not full
	public void viewNotFullCourses() {
		directory.viewNotFullCourses();
		
	}

	@Override
	//register student for a specific course
	//get course name, section number, first, and last name from user
	public void register() {
		Scanner myscanner = new Scanner(System.in);
		System.out.println("Please enter course name: ");
		String cName = myscanner.nextLine();
		System.out.println("Please enter the course section number: ");
		int num = myscanner.nextInt();
		myscanner.nextLine();
		System.out.println("Please enter your first name: ");
		String firstName = myscanner.nextLine();
		System.out.println("Please enter your last name: ");
		String lastName = myscanner.nextLine();
				
		directory.registerForCourse(cName, num, firstName, lastName);
		
	
		
	}

	@Override
	//withdraw a student from a course
	//get course name and students full name from user
	public void withdraw() {
		Scanner myscanner = new Scanner(System.in);
		System.out.println("Please enter course name: ");
		String cName = myscanner.nextLine();
		System.out.println("Please enter your first name: ");
		String firstName = myscanner.nextLine();
		System.out.println("Please enter your last name: ");
		String lastName = myscanner.nextLine();
		
		directory.withdrawFromCourse(cName, firstName, lastName);
	}

	@Override
	//view the courses a student is registered in
	//get full name from user
	public void viewRegisteredCourses() {
		Scanner myscanner = new Scanner(System.in);
		System.out.println("Please enter your first name: ");
		String firstName = myscanner.nextLine();
		System.out.println("Please enter your last name: ");
		String lastName = myscanner.nextLine();
		directory.viewMyCourses(firstName, lastName);
	}
	
	@Override
	//allows student to add a course to their myCourses
	public void addACourse(Course c) {
		myCourses.add(c);
	}
	
	@Override
	//have an overridden method
	public void printMyInfo() {
		System.out.println("I am a student");
		System.out.println("My name is: " + firstName);
	}

}
