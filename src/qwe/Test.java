package qwe;

class Person{
    final protected String name;
    final protected String address;
    final protected String pnumber;
    final protected String email;
    public Person(){
        name = null;
        address = null;
        pnumber = null;
        email = null;
    }

    public Person(String name, String address, String pnumber, String email) {
        this.name = name;
        this.address = address;
        this.pnumber = pnumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

class Student extends Person{
    final private String grade;

    public Student() {
        super();
        grade = null;
    }

    public Student(String name, String address, String pnumber, String email, String grade) {
        super(name, address, pnumber, email);
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class MyDate{
    private int year;
    private int month;
    private int day;
    public MyDate(){
        year = 1;
        month = 1;
        day = 1;
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}

class Employee extends Person{
    protected MyDate myDate;

    public Employee() {
        myDate = new MyDate();
    }

    public Employee(String name, String address, String pnumber, String email, MyDate myDate) {
        super(name, address, pnumber, email);
        this.myDate = myDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", email='" + email + '\'' +
                ", myDate=" + myDate +
                '}';
    }
}

class Faculty extends Employee{
    private String office_hours;
    private String level;

    public Faculty() {
        super();
        office_hours = null;
        level = null;
    }

    public Faculty(String name, String address, String pnumber, String email, MyDate myDate, String office_hours, String level) {
        super(name, address, pnumber, email, myDate);
        this.office_hours = office_hours;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", email='" + email + '\'' +
                ", myDate=" + myDate +
                ", office_hours='" + office_hours + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

class Staff extends Employee{
    private String title;

    public Staff() {
        title = null;
    }

    public Staff(String name, String address, String pnumber, String email, MyDate myDate, String title) {
        super(name, address, pnumber, email, myDate);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", email='" + email + '\'' +
                ", myDate=" + myDate +
                ", title='" + title + '\'' +
                '}';
    }
}
public class Test{
    public static void main(String[] args)
    {
        Person person = new Person("aaa","aaa-a" ,"158aaa" ,"aa@.com" );
        Student student = new Student("bbb", "bbb-b", "177bbb", "bb@.com", "2");
        Employee employee = new Employee("ddd", "ddd-d", "133ddd", "dd@.com", new MyDate(2018, 5, 5));
        Faculty faculty = new Faculty("eee", "eee-e", "199eee", "ee@.com", new MyDate(2017, 9, 9), "10:15-19:00", "3");
        Staff staff = new Staff("ccc", "ccc-c", "199ccc", "cc@.com", new MyDate(), "ccccc");
        System.out.println(person);
        System.out.println(student);
        System.out.println(employee);
        System.out.println(faculty);
        System.out.println(staff);
    }
}