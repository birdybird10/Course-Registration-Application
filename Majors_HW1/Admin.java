import java.util.Scanner;
//inherit from abstract User class and implement all AdminInterface methods
public class Admin extends User implements AdminInterface{
	Scanner myscanner= new Scanner(System.in);
	//constructor with assigned username and password
	public Admin() {
		//call upon User constructor
		super();
		this.username = "Admin";
		this.password = "Admin001";
		this.directory = new CourseDirectory();
	}
	
	
	@Override
	//get the course's info from the user and add it to the course directory
	public void createCourse() {
	
		System.out.println("Please enter the course name: ");
		String temp1 = myscanner.nextLine();
		System.out.println("Please enter the course ID: ");
		String temp2 = myscanner.nextLine();
		System.out.println("Please enter the maximum number of students that can be registered in the course: ");
		int temp3 = myscanner.nextInt();
		myscanner.nextLine();
		System.out.println("Please enter the course instructor's name: ");
		String temp4 = myscanner.nextLine();
		System.out.println("Please enter the course section number: ");
		int temp5 = myscanner.nextInt();
		myscanner.nextLine();
		System.out.println("Please enter the course location: ");
		String temp6 = myscanner.nextLine();
		Course newCourse = new Course(temp1, temp2, temp3, 0, null, temp4, temp5, temp6);
		directory.createNewCourse(newCourse);
	}

	@Override
	// get the course id from the user and delete the course from the course directory
	public void deleteCourse() {
		
		System.out.println("Please enter the name of the course you want to delete: ");
		String temp1 = myscanner.nextLine();
		System.out.println("Please enter the ID of the course you want to delete: ");
		String temp2 = myscanner.nextLine();
		System.out.println("Please enter the section number of the course you want to delete: ");
		int temp3 = myscanner.nextInt();
		myscanner.nextLine();
		directory.deleteCourse(temp1,temp2,temp3);
	}

	@Override
	//get the course ID from the user and edit that course
	public void editCourse() {
		
		System.out.println("Please enter the name of the course you want to edit: ");
		String temp1 = myscanner.nextLine();
		System.out.println("Please enter the ID of the course you want to edit: ");
		String temp2 = myscanner.nextLine();
		System.out.println("Please enter the section number of the course you want to edit: ");
		int temp3 = myscanner.nextInt();
		myscanner.nextLine();
		directory.editCourse(temp1,temp2,temp3);
	}

	@Override
	//get course ID from user and display that course's information
	public void displayCourseInfo() {
		
		System.out.println("Please enter the name of the course you want to display: ");
		String temp1 = myscanner.nextLine();
		System.out.println("Please enter the ID of the course you want to display: ");
		String temp2 = myscanner.nextLine();
		System.out.println("Please enter the section number of the course you want to display: ");
		int temp3 = myscanner.nextInt();
		myscanner.nextLine();
		directory.displayInfo(temp1,temp2,temp3);
		
	}

	@Override
	//add a student without assigning to a course
	//get student's first and last name and username and password
	//add the student to the directory's studentsList
	public void registerStudent() {
		
		System.out.println("Please enter the student's first name: ");
		String fName = myscanner.nextLine();
		System.out.println("Please enter the student's last name: ");
		String lName = myscanner.nextLine();
		System.out.println("Please enter the student's username: ");
		String user = myscanner.nextLine();
		System.out.println("Please enter the student's password: ");
		String pw = myscanner.nextLine();
		Student s = new Student(fName, lName, user, pw);
		directory.registerStudent(s);
	}

	@Override
	//view all courses
	public void viewCourses() {
		directory.viewAllCourses();
		
	}

	@Override
	//view full courses
	public void viewFullCourses() {
		directory.viewFullCourses();
		
	}

	@Override
	//write to a file the list of courses that are full
	public void writeFullCourses() {
		directory.writeFullCoursesList();
		
	}

	@Override
	//get the specific course from the user
	public void viewStudentsInSpecificCourse() {
		
		Course c = null;
		System.out.println("Please enter the course name: ");
		String name = myscanner.nextLine();
		System.out.println("Please enter the course ID: ");
		String id = myscanner.nextLine();
		System.out.println("Please enter the course section number: ");
		int num = myscanner.nextInt();
		myscanner.nextLine();
		for(int i=0; i<directory.getCoursesList().size(); i++) {
			//see if name,id, and section number match
			if(    (directory.getCoursesList().get(i).getId()).equals(id) &&  (directory.getCoursesList().get(i).getName()).equals(name) && (directory.getCoursesList().get(i).getSectionNum())==num ) {
				c = directory.getCoursesList().get(i);
				break;
			}
		}
		
		directory.viewStudentsInACourse(c);
	}

	@Override
	public void viewAStudentsCourses() {
		
		//get first and last name from user
		System.out.println("Please enter the student's first name: ");
		String a = myscanner.nextLine();
		System.out.println("Please enter the student's last name: ");
		String b = myscanner.nextLine();
		directory.viewAStudentsCourses(a, b);
		
	}

	@Override
	//sort all courses
	public void sortCourses() {
		directory.sortCourses();
		
	}
	
	@Override
	//have an overridden method
	public void printMyInfo() {
		System.out.println("I am an administrator");
		System.out.println("My name is: " + firstName);
	}

}
