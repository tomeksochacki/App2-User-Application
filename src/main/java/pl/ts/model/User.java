package pl.ts.model;

public class User implements Comparable<User> {
    private String name;
    private String surname;
    private String age;
    private String phoneNumber;

    public User(String name, String surname, String age, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String converToDbRecord(){
        StringBuilder sb = new StringBuilder();

        sb.append(this.name)
                .append(";")
                .append(this.surname)
                .append(";")
                .append(this.age)
                .append(";")
                .append(this.phoneNumber)
                .append(";");
        return sb.toString();
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }
}
