package DAO;

import entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;

public class PersonDAO {
    private final EntityManager em;

    public PersonDAO(EntityManager em) {
        this.em = em;
    }


    public void save(Person person) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(person);
        transaction.commit();
        System.out.println("The event " + person.getName() + " " + person.getSurname() + " has been saved correctly");
    }

    public Person findById(long personId) {
        Person person = em.find(Person.class, personId);
        if (person == null) throw new EntityNotFoundException(String.valueOf(personId));
        return person;
    }

    public void deleteById(long personId) {

        Person personFound = this.findById(personId);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(personFound);

        transaction.commit();
        System.out.println("Person with id: " + personFound.getId() + " was successfully deleted");


    }
}
