import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


//here is the actual application 
public class CourseRegistrationApplication {

	public static void main(String[] args) {
		Scanner myscanner = new Scanner(System.in);
	
		//create an admin
		Admin admin1 = new Admin();
		
		//read from csv file
		ArrayList<Course> coursesFileInfo = readCourseData();
		admin1.getDirectory().setCourses(coursesFileInfo); 
		
		
		//keep track if a directory has been serialized yet or not
		boolean serialized = false;
		//keep track of count how many times program is run
		int count=0;
		int answer = 0;
		do {	
			System.out.println("\nWelcome to the Course Registration Application!");
			System.out.println("Please select an option: ");
			System.out.println("1. Administrator");
			System.out.println("2. Student");
			System.out.println("3. Exit\n");
			answer = myscanner.nextInt();
			myscanner.nextLine();
			//user selects admin
			if (answer == 1) {
				if (count > 0 && serialized) {
					// read from serialized file
					CourseDirectory myDirec = deserializeCourseDirectory();
					admin1.setDirectory(myDirec);
				}
				//ask admin for username and password
				System.out.print("Please enter your username: ");
				String u = myscanner.nextLine();
				System.out.print("Please enter your password: ");
				String p = myscanner.nextLine();
				if (u.equals(admin1.getUsername()) && p.equals(admin1.getPassword())) {
					System.out.println("\nWelcome administrator");
					int result = 0;
					while(true) {
						//ask admin what they want to do
						System.out.println("Please select what you would like to work with: ");
						System.out.println("1. Courses Management");
						System.out.println("2. Reports");
						System.out.println("3. Exit");
						result = myscanner.nextInt();
						myscanner.nextLine();
						//admin chooses courses management
						if (result == 1) {
							int choice=0;
							do {
								//display courses managaement menu
								choice = coursesManagementAdminMenu();
								switch (choice) {
								case 1:
									admin1.createCourse();
									break;
								case 2:
									admin1.deleteCourse();
									break;
								case 3:
									admin1.editCourse();
									break;
								case 4:
									admin1.displayCourseInfo();
									break;
								case 5:
									admin1.registerStudent();
									break;
								default:
									break;
								}
							//user decides to exit
							} while (choice != 6);
						// admin chooses reports
						} else if (result == 2) {
							int action = 0;
							do {
								//display reports admin menu
								action = reportsAdminMenu();
								switch (action) {
								case 1:
									admin1.viewCourses();
									break;
								case 2:
									admin1.viewFullCourses();
									break;
								case 3:
									admin1.writeFullCourses();
									break;
								case 4:
									admin1.viewStudentsInSpecificCourse();
									break;
								case 5:
									admin1.viewAStudentsCourses();
									break;
								case 6:
									admin1.sortCourses();
									break;
								default:
									break;
								}
							//admin chooses to exit
							} while (action != 7);
						}
						//admin completely exits the program
						else if(result==3) {
							//serialize the admin's coursedirectory
							serializeCourseDirectory(admin1.getDirectory());
							serialized = true;
							break;
						}
						
					}
				//admin entered wrong username and password
				} else {
					System.out.println("Sorry, wrong username or password");
				}

			//user chose student option
			} else if (answer == 2) {
				// read from serialized or csv file- we will only need the admin until we find the exact student
				if(serialized) {
					CourseDirectory myDir = deserializeCourseDirectory();
					admin1.setDirectory(myDir);
				}
						
				// a student must have been created by the admin
				if (  (admin1.getDirectory().getStudentsList().size() == 0) || (count==0)  ) {
					System.out.println("There are no students yet");
				}
				// the admin has created a student
				else {
					System.out.print("Please enter your username: ");
					String user = myscanner.nextLine();
					System.out.print("Please enter your password: ");
					String pw = myscanner.nextLine();
					
					//boolean variable to see if the student has been found or not
					boolean found = false;
					//count variable to see if we have gone through the entire studentsList yet
					int counting = 0;

					// iterate through all students
					for (int i = 0; i < admin1.getDirectory().getStudentsList().size(); i++) {
						// make sure username and password match
						if (user.equals(admin1.getDirectory().getStudentsList().get(i).getUsername())
								&& pw.equals(admin1.getDirectory().getStudentsList().get(i).getPassword())) {
							String stuFirstname = admin1.getDirectory().getStudentsList().get(i).getFirstName();
							String stuLastname = admin1.getDirectory().getStudentsList().get(i).getLastName();
							found = true;
							System.out.println("\nWelcome " + stuFirstname + " " + stuLastname);
							//label the exact student we are dealing with
							Student student1 = admin1.getDirectory().getStudentsList().get(i);
							
							//deserialize the coursedirectory for the student
							CourseDirectory stuDirect = deserializeCourseDirectory();
							student1.setDirectory(stuDirect);
							
							int response = 0;
							while(true) {
								//display student menu options
								response = studentMenu();
								
								if(response==1) {
									student1.viewCourses();
								}
								else if(response==2) {
									student1.viewNotFullCourses();
								}
								else if(response==3) {
									student1.register();
								}
								else if(response==4) {
									student1.withdraw();
								}
								else if(response==5) {
									student1.viewRegisteredCourses();
								}
								//student chose to exit
								else if(response==6) {
									//serialize the student's coursedirectory and break
									serializeCourseDirectory(student1.getDirectory());
									serialized = true;
									break;
									
								}
								
						
							}
							break;
							
						} 
						
						//keep track of how many times we have looped through the studentsList
						counting++;
					}
					
					// couldnt find a student with the username and password entered by the user
					if(found==false) {
						System.out.println("Sorry, wrong username or password");
					}

				}

			} 
		
			
			//we have gone through the program +1 times
			count++;
			
		// completely exit the program
		} while (answer != 3);
		
		myscanner.close();

	}
	
	//serialization method
	//take in a coursedirectory object
	public static void serializeCourseDirectory(CourseDirectory data) {
		try {
			FileOutputStream fos = new FileOutputStream("CourseDirectory.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			
			oos.close();
			fos.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//deserialization method
	public static CourseDirectory deserializeCourseDirectory() {
		CourseDirectory directory = null;
		try {
			FileInputStream fis = new FileInputStream("CourseDirectory.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			directory = (CourseDirectory)ois.readObject();
			
			ois.close();
			fis.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		
		catch(ClassNotFoundException ex){
			ex.printStackTrace();
			
		}
		
		return directory;
	}
	
	//menu options for admin courses management
	public static int coursesManagementAdminMenu() {
		Scanner s = new Scanner(System.in);
		int result = 0;
		
		System.out.println("Courses Management");
		System.out.println("	1. Create a new course");
		System.out.println("	2. Delete a course");
		System.out.println("	3. Edit a course");
		System.out.println("	4. Display information for a given course");
		System.out.println("	5. Register a student");
		System.out.println("	6. Go back");
		
		result = s.nextInt();
		return result;
	}
	
	// menu options for admin reports
	public static int reportsAdminMenu() {
		Scanner s = new Scanner(System.in);
		int result = 0;
		
		System.out.println("Reports");
		System.out.println("	1. View all courses");
		System.out.println("	2. View all courses that are full");
		System.out.println("	3. Write to a file the list of courses that are full");
		System.out.println("	4. View the names of the students being registered in a specific course");
		System.out.println("	5. View the list of courses that a given student is being registered in");
		System.out.println("	6. Sort courses based on the current number of student registers");
		System.out.println("	7. Go back");
		
		result = s.nextInt();
		return result;
	}
	
	// menu options for student course management
	public static int studentMenu() {
		Scanner s = new Scanner(System.in);
		int result = 0;
		
		System.out.println("Course Management");
		System.out.println("	1. View all courses");
		System.out.println("	2. View all courses that are not full");
		System.out.println("	3. Register on a course");
		System.out.println("	4. Withdraw from a course");
		System.out.println("	5. View all courses that the current student is being registered in");
		System.out.println("	6. Exit");
		
		result = s.nextInt();
		return result;
	}
	
	// read in CSV file data
	public static ArrayList<Course> readCourseData(){
		String csvFile = "MyUniversityCourses.txt";
		// file info will get stored in here
		ArrayList<Course> courses = new ArrayList<Course>();
		// for reading each line of the file
		String lineReader = "";
		// we also need an array to store each line in
		String[] myArray = new String[7];
		
		try {
			FileReader fReader = new FileReader(csvFile);
			BufferedReader bReader = new BufferedReader(fReader);
			int i=0;
			while((lineReader=bReader.readLine())!=null) {
				//skip over 1st line of csv file
				if(i==0) {
					i++;
					continue;
				}
				
				//first need to spilt the line at the commas
				myArray = lineReader.split(",");
				//now add the line as a course object in the courses arraylist
				courses.add(new Course(myArray[0], myArray[1], Integer.parseInt(myArray[2]), Integer.parseInt(myArray[3]), new ArrayList<Student>(), myArray[5], Integer.parseInt(myArray[6]), myArray[7]));
			}
			
			//close file reader
			bReader.close();
		}
		catch(FileNotFoundException exception) {
			System.out.println("Sorry, can't find that file");
			exception.printStackTrace();
		}
		catch(IOException exception){
			System.out.println("You have an IO exception");
			exception.printStackTrace();
		}
		
		return courses;
		
	}
		

}
