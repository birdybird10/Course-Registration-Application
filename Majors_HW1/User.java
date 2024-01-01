//template for a user object
public abstract class User implements java.io.Serializable{
	
	//declare variables
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	// a user needs to be have a course directory that they are managing
	protected CourseDirectory directory;
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//getter and setter for directory
	public CourseDirectory getDirectory() {
		return directory;
	}
		
	public void setDirectory(CourseDirectory d) {
		this.directory = d;
	}
	
	//have a method that gets overridden in student and admin
	public void printMyInfo() {
		System.out.println("My name is: " + firstName);
	}
	
	//another method that is overridden in student and admin
	public void viewCourses() {
		directory.getCoursesList();
	}
	
	
}
