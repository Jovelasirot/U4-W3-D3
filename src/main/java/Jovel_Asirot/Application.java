package Jovel_Asirot;

import DAO.EventDAO;
import com.github.javafaker.Faker;
import entities.Event;
import entities.Person;
import enums.Gender;
import enums.TypeEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("events_v2");

    public static void main(String[] args) {

        EntityManager eM = emf.createEntityManager();

        EventDAO eDAO = new EventDAO(eM);

//        Supplier<Event> eventSupplier = getEventSupplier();
//        List<Event> eventList = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            eventList.add(eventSupplier.get());
//        }
//
//        eventList.forEach(eDAO::save);

        emf.close();
        eM.close();
    }

    public static Supplier<Event> getEventSupplier() {
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

            return new Event(title, dateEvent, description, typeEvent, maxParticipant);
        };
    }

    public static Supplier<Person> getPersonSupplier(List<Event> eventListAttended) {
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

            return new Person(namePerson, surnamePerson, email, birthDate, gender, eventListAttended);
        };
    }
}
