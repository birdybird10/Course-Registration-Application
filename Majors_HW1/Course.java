import java.util.ArrayList;
//template for a course object
public class Course implements java.io.Serializable{
	
	//declare variables
	private String name;
	private String id;
	private int maxStudentsNum;
	private int numRegisteredStudents;
	private ArrayList<Student> studentsRegistered;
	private String instructor;
	private int sectionNum;
	private String courseLocation;
	
	//default constructor
	public Course() {
		this.studentsRegistered = new ArrayList<Student>();
	}
	
	// constructor that takes all parameters
	public Course(String name, String id, int maxStudentsNum, int numRegisteredStudents, ArrayList<Student> studentsRegistered, String instructor, int sectionNum, String courseLocation) {
		this.name = name;
		this.id = id;
		this.maxStudentsNum = maxStudentsNum;
		this.numRegisteredStudents = numRegisteredStudents;
		this.studentsRegistered = studentsRegistered;
		this.instructor = instructor;
		this.sectionNum = sectionNum;
		this.courseLocation = courseLocation;
	}

	//all getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMaxStudentsNum() {
		return maxStudentsNum;
	}

	public void setMaxStudentsNum(int maxStudentsNum) {
		this.maxStudentsNum = maxStudentsNum;
	}

	public int getNumRegisteredStudents() {
		return numRegisteredStudents;
	}

	public void setNumRegisteredStudents(int numRegisteredStudents) {
		this.numRegisteredStudents = numRegisteredStudents;
	}

	public ArrayList<Student> getStudentsRegistered() {
		return studentsRegistered;
	}
	
	//method for adding a student to studentsRegistered
	public void addStudent(Student s) {
		studentsRegistered.add(s);
	}

	public void setStudentsRegistered(ArrayList<Student> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}
	
	// display course info to user
	public void displayCourseInfo() {
		System.out.println("\nCourse name: " + this.name);
		System.out.println("Course ID: " + this.id);
		System.out.println("Maximum number of students registered: " + this.maxStudentsNum);
		System.out.println("Current number of registered students: " + this.numRegisteredStudents);
		//display students if they exist
		if(studentsRegistered != null) {
			System.out.print("Names of students registered:  ");
			for(int i=0; i<studentsRegistered.size(); i++) {
				if(i==studentsRegistered.size()-1) {
					System.out.print(studentsRegistered.get(i).getFirstName() + " " + studentsRegistered.get(i).getLastName());
				}
				else {
					System.out.print(studentsRegistered.get(i).getFirstName() + " " + studentsRegistered.get(i).getLastName() + ", ");
				}
			}
			System.out.println();
		}
		System.out.println("Course instructor: " + this.instructor);
		System.out.println("Course section number: " + this.sectionNum);
		System.out.println("Course location: " + this.courseLocation);
		System.out.println("--------------------------------------------------------------");
	}
	
	// this method is specifically made for the admin viewCourses method in CourseDirectory
	//admin should be able to see the course name, course id, number of students registered, 
	//and maximum number of students allowed to be registered
	public void displayAllCourses() {
		System.out.println("Course name: " + this.name);
		System.out.println("Course ID: " + this.id);
		System.out.println("Number of students registered: " + this.numRegisteredStudents);
		System.out.println("Maximum number of students allowed to be registered: " + this.maxStudentsNum);
		System.out.println("----------------------------------------------------------------------------");
	}
	
	//this method is specifically for a  student so they view only  what they need to see
	public void displayCoursesForStudent() {
		System.out.println("Course name: " + this.name);
		System.out.println("Course ID: " + this.id);
		System.out.println("Course instructor: " + this.instructor);
		System.out.println("Course section number: " + this.sectionNum);
		System.out.println("Course location: " + this.courseLocation);
		System.out.println("--------------------------------------------------");
	}
	
}
