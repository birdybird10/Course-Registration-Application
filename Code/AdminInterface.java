//define what an admin can/should do
public interface AdminInterface {
	
	//create a new course
	public void createCourse();
	
	//delete a course
	public void deleteCourse();
	
	//edit a course
	public void editCourse();
	
	//display information  about a certain course
	public void displayCourseInfo();
	
	//register a student, meaning create a student
	public void registerStudent();
	
	//view all the courses
	public void viewCourses();
	
	//view courses that are  full
	public void viewFullCourses();
	
	//write the courses that are full to a file
	public void writeFullCourses();
	
	//view the students in a certain course
	public void viewStudentsInSpecificCourse();
	
	// view a students courses
	public void viewAStudentsCourses();
	
	//sort courses based on  current number of student registers
	public void sortCourses();
}
