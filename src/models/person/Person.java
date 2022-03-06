package models.person;

public abstract class Person {
    private String id;
    private String name;
    private String dayOfBirth;
    private String gender;
    private String identityCardNumber;
    private String numberPhone;
    private String email;

    public Person() {
    }

    public Person(String id, String name, String dayOfBirth, String gender,
                  String identityCardNumber, String numberPhone, String email) {
        this.id = id;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.gender = gender;
        this.identityCardNumber = identityCardNumber;
        this.numberPhone = numberPhone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", name: " + name +
                ", dayOfBirth: " + dayOfBirth +
                ", gender: " + gender +
                ", identityCardNumber: " + identityCardNumber +
                ", numberPhone: " + numberPhone +
                ", email: " + email;
    }
}
