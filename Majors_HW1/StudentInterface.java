//define what a student can/should do
public interface StudentInterface {
	
	//view all courses
	public void viewCourses();
	
	//view courses that are not full
	public void viewNotFullCourses();
	
	//student registers for a class
	public void register();
	
	// student withdraws from a class
	public void withdraw();
	
	//view courses that a student is registered in
	public void viewRegisteredCourses();
	
	//add a course to the student's list of courses
	public void addACourse(Course c);
}
