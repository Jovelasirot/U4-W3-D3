package entities;

import enums.TypeEvent;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {

    @Column(name = "event_date")
    protected LocalDate eventDate;
    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "type_event")
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;

    @Column(name = "max_participant")
    private int maxParticipant;
    @ManyToOne
    @JoinColumn(name = "location_id") // Assuming you have a column named location_id in your Event table
    private Location location;

    @Column(name = "attendees")
    @OneToMany(mappedBy = "event")
    private List<Attendance> attendees = new ArrayList<>();

    public Event() {
    }

    public Event(String title, LocalDate eventDate, String description, TypeEvent typeEvent, int maxParticipant, List<Person> attendee) {
        this.title = title;
        this.eventDate = eventDate;
        this.description = description;
        this.typeEvent = typeEvent;
        this.maxParticipant = maxParticipant;
        this.attendees = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant) {
        this.maxParticipant = maxParticipant;
    }


    public void setAttendances(List<Attendance> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", typeEvent=" + typeEvent +
                ", maxParticipant=" + maxParticipant +
                '}';
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
