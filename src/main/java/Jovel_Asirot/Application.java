package Jovel_Asirot;

import com.github.javafaker.Faker;
import entities.Event;
import enums.TypeEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("events_v2");

    public static void main(String[] args) {

        EntityManager eM = emf.createEntityManager();

        System.out.println("Hello World!");
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
}
