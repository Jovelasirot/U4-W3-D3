package DAO;

import entities.Attendance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AttendanceDAO {
    private final EntityManager em;

    public AttendanceDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Attendance attendance) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(attendance);
        transaction.commit();
        System.out.println("New attendance was saved correctly.");
    }

    public Attendance findById(long id) {
        return em.find(Attendance.class, id);
    }

    public void findByIdAndDelete(long id) {

        Attendance foundAttendance = em.find(Attendance.class, id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(foundAttendance);
        transaction.commit();
        System.out.println("The attendance with id: " + foundAttendance.getId() + " was eliminated correctly");

    }

    public List<Attendance> getAllAttendances() {
        TypedQuery<Attendance> query = em.createQuery("SELECT a FROM Attendance a", Attendance.class);
        return query.getResultList();
    }
}
