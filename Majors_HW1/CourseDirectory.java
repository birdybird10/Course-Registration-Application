import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
//handles an arraylist of course objects and an arraylist of student objects
public class CourseDirectory implements java.io.Serializable{
	private ArrayList<Course> coursesList;
	private ArrayList<Student> studentsList;
	static Scanner myscanner= new Scanner(System.in);
	
	//constructor 
	public CourseDirectory() {
		this.coursesList = new ArrayList<Course>();
		this.studentsList = new ArrayList<Student>();
	}
	
	//getter for students
	public ArrayList<Student> getStudentsList(){
		return studentsList;
	}
	
	public void setStudentsList(ArrayList<Student> studentsList) {
		this.studentsList = studentsList;
	}

	//getter for courses list
	public ArrayList<Course> getCoursesList(){
		return coursesList;
	}
	
	// create a new course and add it to coursesList
	public void createNewCourse(Course c) {
		coursesList.add(c);
		System.out.println("The course has been added");
	}
	
	// given an id, use the id to find the course in coursesList and delete it
	public void deleteCourse(String name, String id, int sectNum) {
		for(int i=0; i<coursesList.size(); i++) {
			if(  (coursesList.get(i).getId()).equals(id) && (coursesList.get(i).getName()).equals(name) && (coursesList.get(i).getSectionNum()) == sectNum  ) {
				coursesList.remove(i);
			}
		}
		System.out.println("The course has been deleted");
	}
	
	//overloaded  method
	public void deleteCourse(String name, int sectNum) {
		for(int i=0; i<coursesList.size(); i++) {
			if(  (coursesList.get(i).getName()).equals(name) && (coursesList.get(i).getSectionNum()) == sectNum  ) {
				coursesList.remove(i);
			}
		}
	}
	
	// given an id, find the course and edit it 
	public void editCourse(String name, String id, int sectNum) {
	
		for(int i=0; i<coursesList.size(); i++) {
			if((coursesList.get(i).getId()).equals(id) && (coursesList.get(i).getName()).equals(name) && (coursesList.get(i).getSectionNum()) == sectNum ) {
				System.out.println("Please enter the maximum amount of students: ");
				int temp1 = myscanner.nextInt();
				myscanner.nextLine();
				coursesList.get(i).setMaxStudentsNum(temp1);
				System.out.println("Please enter the name of the course instructor: ");
				String temp3 = myscanner.nextLine();
				coursesList.get(i).setInstructor(temp3);
				System.out.println("Please enter the course section number: ");
				int temp4 = myscanner.nextInt();
				myscanner.nextLine();
				coursesList.get(i).setSectionNum(temp4);
				System.out.println("Please enter the course location: ");
				String temp5 = myscanner.nextLine();
				coursesList.get(i).setCourseLocation(temp5);	
			}
		}
		System.out.println("The course has been edited");
	}
	
	// display information about a specific course using id
	public void displayInfo(String name, String id, int sectNum) {
		//iterate through courses, if id matches then display the course's info
		for(int i=0; i<coursesList.size(); i++) {
			if(  (coursesList.get(i).getId()).equals(id) && (coursesList.get(i).getName()).equals(name) && (coursesList.get(i).getSectionNum()) == sectNum  ) {
				coursesList.get(i).displayCourseInfo();
				break;
			}
		}
	}
	
	// dont actually put the student in a specific class
	//basically just creating a student object and store it in the student arraylist
	public void registerStudent(Student s) {
		studentsList.add(s);
	}
	
	//admin should be able to see the course name, course id, number of students registered, 
	//and maximum number of students allowed to be registered
	public void viewAllCourses() {
		for(int i=0; i<coursesList.size(); i++) {
			coursesList.get(i).displayAllCourses();
		}
	}
	
	// view all courses that have reached their max number of students
	public void viewFullCourses() {
		System.out.println("The following courses are full: ");
		//course if full if number of registered students equals number of max students
		for(int i=0; i<coursesList.size(); i++) {
			if(  coursesList.get(i).getNumRegisteredStudents() == coursesList.get(i).getMaxStudentsNum()   ) {
				System.out.println(coursesList.get(i).getName());
			}
		}
	}
	
	// write to a file the list of courses that are full
	public void writeFullCoursesList() {
		try {
			File fullCourses = new File("FullCourses.txt");
			FileWriter fileWriter = new FileWriter(fullCourses);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			//course is full if number of registered students equals number of max students
			for(int i=0; i<coursesList.size(); i++) {
				if(  coursesList.get(i).getNumRegisteredStudents() == coursesList.get(i).getMaxStudentsNum()   ) {
					String courseInfo = "Course Name: " + coursesList.get(i).getName() + "," + " Course ID: " + coursesList.get(i).getId() + "," + " Course section number: " + coursesList.get(i).getSectionNum();
					printWriter.println(courseInfo);
					printWriter.println(" ");
				}
			}
			
			//close writer
			printWriter.close();
			System.out.println("Your file was written");
							
		} 
		
		catch(IOException e) {
				System.out.println("There was an error writing your file");
				e.printStackTrace();
		}
	}
	
	//view the names of students being registered in a specific course
	public void viewStudentsInACourse(Course c) {
		System.out.println("These are the students registered in " + c.getName() + ":");
		for(int i=0; i<c.getNumRegisteredStudents(); i++) {
			System.out.println(c.getStudentsRegistered().get(i).getFirstName() + " " + c.getStudentsRegistered().get(i).getLastName());
		}
	}
	
	//view a student's courses
	public void viewAStudentsCourses(String firstName, String lastName) {
		System.out.println("Courses for " + firstName + " " + lastName + ": ");
		//iterate through the list of students
		for(int i=0; i<studentsList.size(); i++) {
			//first and last name match input
			if(  (studentsList.get(i).getFirstName()).equals(firstName) && (studentsList.get(i).getLastName()).equals(lastName)  ) {
				for(int j=0; j<studentsList.get(i).getCoursesList().size(); j++) {
					//print the name  of  the course
					System.out.println(studentsList.get(i).getCoursesList().get(j).getName());
				}
			}
		}
	}
	
	//sort courses by number of students registered
	public void sortCourses() {
		//iterate through the coursesList
		for(int i=0; i<coursesList.size(); i++) {
			for(int j=i+1; j<coursesList.size(); j++) {
				// we want to keep finding the smallest number
				if(  (coursesList.get(i).getNumRegisteredStudents())  >  (coursesList.get(j).getNumRegisteredStudents())  ) {
					// swap the variables
					Course c = coursesList.get(i);
					coursesList.set(i, coursesList.get(j));
					coursesList.set(j, c);
				}
			}
		}
		System.out.println("The courses have been sorted");
		//display all courses
		for(int i=0; i<coursesList.size(); i++) {
			coursesList.get(i).displayAllCourses();
		}
	}
	
	
	//the following methods only apply for students, not the admin
	//let a student view all courses
	public void viewCoursesForStudent() {
		for(int i=0; i<coursesList.size(); i++) {
			coursesList.get(i).displayCoursesForStudent();
		}
	}
	
	// show student courses  that are  not full
	public void viewNotFullCourses() {
		System.out.println("The following courses are not full: ");
		for(int i=0; i<coursesList.size(); i++) {
			if(  coursesList.get(i).getNumRegisteredStudents() != coursesList.get(i).getMaxStudentsNum()  ) {
				coursesList.get(i).displayCoursesForStudent();
			}
		}
	}
	
	//register for a course
	public void registerForCourse(String courseName, int sectionNum, String fName, String lName) {
		//add student to the course's arraylist of students
		//also add course to the student's personal list of courses
		
		//first check if the course is full 
		for(int i=0; i<coursesList.size(); i++) {
			if(   (coursesList.get(i).getName()).equals(courseName)  &&  (coursesList.get(i).getSectionNum()) == sectionNum   ) {
				if(  coursesList.get(i).getNumRegisteredStudents() == coursesList.get(i).getMaxStudentsNum()   ) {
					System.out.println("Sorry, that course is full");
					
				}
				//continue registering if course is not full
				else {
					for(int j=0; j<studentsList.size(); j++) {
						//both first and last name must match
						if(   (studentsList.get(j).getFirstName()).equals(fName)  && (studentsList.get(j).getLastName()).equals(lName) ) {
							Student s = studentsList.get(j);
							//add student to the courses studentsRegistered list
							coursesList.get(i).getStudentsRegistered().add(s);
							//add course to student's personal list of courses
							s.getCoursesList().add(coursesList.get(i));
							//update number of students in the class
							int numStudents = coursesList.get(i).getNumRegisteredStudents();
							numStudents += 1;
							coursesList.get(i).setNumRegisteredStudents(numStudents);
						}
					}
				}
			}
		}
		
	}
	
	//remove a student from a course
	public void withdrawFromCourse(String courseName, String fName, String lName) {
		//remove student from course's arraylist of students
		//also remove course from the student's personal list of courses
		for(int i=0; i<coursesList.size(); i++) {
			if(   (coursesList.get(i).getName()).equals(courseName)  ) {
				//find correct student in that course's arraylist of students
				for(int j=0; j<coursesList.get(i).getStudentsRegistered().size(); j++) {
					//both first and last name must match
					if(   (coursesList.get(i).getStudentsRegistered().get(j).getFirstName()).equals(fName) && (coursesList.get(i).getStudentsRegistered().get(j).getLastName()).equals(lName)  ) {
						Student s = coursesList.get(i).getStudentsRegistered().get(j);
						//remove student from course's arraylist of students
						coursesList.get(i).getStudentsRegistered().remove(s);
						//remove courses from student's personal course list
						s.getCoursesList().remove(coursesList.get(i));
						//update number of students in the class
						int numStudents = coursesList.get(i).getNumRegisteredStudents();
						numStudents -= 1;
						coursesList.get(i).setNumRegisteredStudents(numStudents);
					}
				}
			}
		}
	}
	
	// display students personal list of registered courses
	public void viewMyCourses(String fName, String lName) {
		Student stu = new Student();
		
		//first find the correct student in studentsList
		for(int i=0; i<studentsList.size(); i++) {
			if(  (studentsList.get(i).getFirstName().equals(fName)) && (studentsList.get(i).getLastName().equals(lName)) ) {
				stu = studentsList.get(i);
			}
		}
	
		//student hasnt registered in anything
		if(stu.getCoursesList().size() == 0) {
			System.out.println("You are not registered in any courses");
		}
		//student has at least one registered course
		else {
			System.out.println("You are registered in the following courses: ");
			//iterate through the student's course list and display the courses
			for(int j=0; j<stu.getCoursesList().size(); j++) {
				stu.getCoursesList().get(j).displayCoursesForStudent();
			}
		}
	}
	
	
	
	// we need a setter for the courses arraylist since we are reading info in from a csv file
	// this method gets called at the beginning of CourseRegistrationApplication when we read from... 
	// ...the csv file and store courses in coursesList
	public void setCourses(ArrayList<Course> coursesList) {
		this.coursesList = coursesList;
	}
}
