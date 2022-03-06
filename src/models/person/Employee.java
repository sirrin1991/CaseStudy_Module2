package models.person;

public class Employee extends Person {
    private String education;
    private String position;
    private double salary;

    public Employee() {
    }

    public Employee(String id, String name, String dayOfBirth, String gender, String identityCardNumber,
                    String numberPhone, String email, String education, String position, double salary) {
        super(id, name, dayOfBirth, gender, identityCardNumber, numberPhone, email);
        this.education = education;
        this.position = position;
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString() +
                ", education: " + education +
                ", position:" + position +
                ", salary: " + salary +
                '}';
    }
}
