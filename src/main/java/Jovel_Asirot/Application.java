package Jovel_Asirot;

import DAO.AttendanceDAO;
import DAO.EventDAO;
import DAO.LocationDAO;
import DAO.PersonDAO;
import com.github.javafaker.Faker;
import entities.Attendance;
import entities.Event;
import entities.Location;
import entities.Person;
import enums.Gender;
import enums.SateAttendance;
import enums.TypeEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("events_v2");

    public static void main(String[] args) {

        EntityManager eM = emf.createEntityManager();

        EventDAO eDAO = new EventDAO(eM);
        LocationDAO lDAO = new LocationDAO(eM);
        PersonDAO pDAO = new PersonDAO(eM);
        AttendanceDAO aDAO = new AttendanceDAO(eM);

//      People
        Supplier<Person> personSupplier = getPersonSupplier(aDAO.getAllAttendances());
        List<Person> peopleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            peopleList.add(personSupplier.get());
        }
        peopleList.forEach(pDAO::save);

//        Events
        Supplier<Event> eventSupplier = getEventSupplier(peopleList);
        List<Event> eventList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            eventList.add(eventSupplier.get());
        }

        eventList.forEach(eDAO::save);

//        Locations
        Location location1 = new Location("Lucky Pub", "Milan");
        Location location2 = new Location("Bar Nice", "Rome");
        Location location3 = new Location("Cat bar", "Venice");

        lDAO.save(location1);
        lDAO.save(location2);
        lDAO.save(location3);

        List<Location> allLocations = lDAO.getAllLocations();


        for (Event event : eventList) {
            int randomIndex = new Random().nextInt(allLocations.size());
            Location randomLocation = allLocations.get(randomIndex);
            event.setLocation(randomLocation);
        }


//        Attendance
        Supplier<Attendance> attendanceSupplier = getAttendanceSupplier(peopleList, eventList);
        for (int i = 0; i < 50; i++) {
            Attendance attendance = attendanceSupplier.get();
            aDAO.save(attendance);
        }


        emf.close();
        eM.close();
    }

    public static Supplier<Event> getEventSupplier(List<Person> people) {
        Random rdm = new Random();
        Faker faker = new Faker();
        TypeEvent[] typeEvents = TypeEvent.values();

        return () -> {

            String title = faker.esports().event();
            String description = faker.esports().game();

            LocalDate dateEvent = LocalDate.now().plusDays(rdm.nextInt(365));

            int rdmTypeEvent = rdm.nextInt(typeEvents.length);
            TypeEvent typeEvent = typeEvents[rdmTypeEvent];

            int maxParticipant = rdm.nextInt(10, 50);

            List<Person> attendees = new ArrayList<>();
            int numAttendees = rdm.nextInt(people.size());
            for (int i = 0; i < numAttendees; i++) {
                attendees.add(people.get(rdm.nextInt(people.size())));
            }

            return new Event(title, dateEvent, description, typeEvent, maxParticipant, attendees);
        };
    }

    public static Supplier<Person> getPersonSupplier(List<Attendance> eventListAttended) {
        Random rdm = new Random();
        Faker faker = new Faker();
        Gender[] genders = Gender.values();
        return () -> {

            String namePerson = faker.name().firstName();
            String surnamePerson = faker.name().lastName();

            String email = namePerson + surnamePerson + "@gmail.com";

            LocalDate date = LocalDate.parse("2002-01-01");
            LocalDate birthDate = date.plusDays(rdm.nextInt(730));

            int rdmGender = rdm.nextInt(genders.length);
            Gender gender = genders[rdmGender];

            List<Attendance> attendanceList = new ArrayList<>();
            int eventsAttended = rdm.nextInt(eventListAttended.size() + 1);
            for (int i = 0; i < eventsAttended; i++) {
                Attendance attendedEvent = eventListAttended.get(rdm.nextInt(eventListAttended.size()));
                Attendance attendance = new Attendance(attendedEvent.getPerson(), attendedEvent.getEvent(), SateAttendance.CONFIRMED);
                attendanceList.add(attendance);
            }

            return new Person(namePerson, surnamePerson, email, birthDate, gender, eventListAttended);
        };
    }

    public static Supplier<Attendance> getAttendanceSupplier(List<Person> people, List<Event> events) {
        Random rdm = new Random();
        SateAttendance[] sateAttendances = SateAttendance.values();

        return () -> {
            Person randomPerson = people.get(rdm.nextInt(people.size()));

            Event randomEvent = events.get(rdm.nextInt(events.size()));

            LocalDate attendanceDate = LocalDate.now().minusDays(rdm.nextInt(30));

            int rdmStateAttendance = rdm.nextInt(sateAttendances.length);
            SateAttendance sateAttendance = sateAttendances[rdmStateAttendance];

            return new Attendance(randomPerson, randomEvent, sateAttendance);
        };
    }

}
