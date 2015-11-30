package org.dcsc.core.attendees;

import org.dcsc.core.event.Event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dcsc_event_attendees", schema = "public")
public class EventAttendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private long id;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dcsc_attended", schema = "public",
            joinColumns = {
                    @JoinColumn(name = "attendee_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "event_id", referencedColumnName = "id")
            }
    )
    private List<Event> attendedEvents;

    public EventAttendee() {
        id = 0;
        email = null;
        name = null;
        attendedEvents = null;
    }

    public EventAttendee(String email) {
        this.email = email;
        this.name = "";
        this.attendedEvents = new ArrayList<Event>();
    }

    public EventAttendee(String email, String name, List<Event> attendedEvents) {
        this.email = email;
        this.name = name;
        this.attendedEvents = attendedEvents;
    }

    public EventAttendee(long id, String email, String name, List<Event> attendedEvents) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.attendedEvents = attendedEvents;
    }

    public void addEvent(Event event) {
        if (!attendedEvents.contains(event)) {
            attendedEvents.add(event);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getAttendedEvents() {
        return attendedEvents;
    }

    public void setAttendedEvents(List<Event> attendedEvents) {
        this.attendedEvents = attendedEvents;
    }
}
