package DAO;

import entities.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LocationDAO {
    private final EntityManager em;

    public LocationDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Location location) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(location);
        transaction.commit();
        System.out.println(location.getNameLocation() + " Location saved correctly.");
    }

    public List<Location> getAllLocations() {
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
        return query.getResultList();
    }

    public Location findById(long id) {
        return em.find(Location.class, id);
    }

    public void findByIdAndDelete(long id) {

        Location foundLocation = em.find(Location.class, id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundLocation);
        transaction.commit();
        System.out.println(foundLocation.getNameLocation() + " was eliminated correctly");

    }
}
