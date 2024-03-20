package entities;

import enums.Gender;
import jakarta.persistence.*;

import java.util.List;

public class Person {
    @Id
    @GeneratedValue
    @Column(name = "person_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "birth_date")
    private int birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "list_attendance")
    private List<Event> eventAttendedList;

    public Person(String name, String surname, String email, int birthDate, Gender gender, List<Event> eventAttendedList) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.eventAttendedList = eventAttendedList;
    }

    public Person() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Event> getEventAttendedList() {
        return eventAttendedList;
    }

    public void setEventAttendedList(List<Event> eventAttendedList) {
        this.eventAttendedList = eventAttendedList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                '}';
    }
}
