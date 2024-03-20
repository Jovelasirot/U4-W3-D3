package entities;

import enums.SateAttendance;
import jakarta.persistence.*;


@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "state_attendance")
    @Enumerated(EnumType.STRING)
    private SateAttendance stateAttendance;

    public Attendance() {
    }

    public Attendance(Person person, Event event, SateAttendance stateAttendance) {
        this.person = person;
        this.event = event;
        this.stateAttendance = stateAttendance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public SateAttendance getStateAttendance() {
        return stateAttendance;
    }

    public void setStateAttendance(SateAttendance stateAttendance) {
        this.stateAttendance = stateAttendance;
    }


    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", person=" + person +
                ", event=" + event +
                ", stateAttendance=" + stateAttendance +
                '}';
    }
}
