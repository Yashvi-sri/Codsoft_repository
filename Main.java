import java.util.*;
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        } else {
            return false;
        }
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description + 
               "\nSchedule: " + schedule + "\nAvailable Slots: " + getAvailableSlots();
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerForCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            return true;
        } else {
            return false;
        }
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size();
    }
}

class RegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public RegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void listCourses() {
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("-----------------------------");
        }
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.registerForCourse(course)) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Course is full!");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public void removeStudentFromCourse(String studentId, String courseCode) {
        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            if (student.dropCourse(course)) {
                System.out.println("Course removed successfully!");
            } else {
                System.out.println("Student is not enrolled in this course.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Adding some courses
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "MWF 10-11AM"));
        system.addCourse(new Course("MATH101", "Calculus I", "Introduction to Calculus", 25, "TTh 9-10:30AM"));

        // Adding some students
        system.addStudent(new Student("S1001", "Alice"));
        system.addStudent(new Student("S1002", "Bob"));

        while (true) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. List available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    system.listCourses();
                    break;
                case 2:
                    System.out.print("Enter your student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudentForCourse(studentId, courseCode);
                    break;
                case 3:
                    System.out.print("Enter your student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.nextLine();
                    system.removeStudentFromCourse(studentId, courseCode);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
