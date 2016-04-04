package org.dcsc.athena.objects;

import org.dcsc.utilities.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.dcsc.core.tutor.Tutor;

@Entity
@Table(name = "tutoring_sessions", schema = "dcsc_tutoring")
public class TutoringSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "start_timestamp")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startDateTime;

    @Column(name = "end_timestamp")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime endDateTime;

	@Column(name = "tutor_id")
	private long tutorId;

	@Transient
    private Tutor tutor;

    public TutoringSession() {
    }

    public TutoringSession(LocalDateTime startDateTime, Tutor tutor) {
    	this.id = 0;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.tutorId = tutor.getId();
        this.tutor = tutor;
    }

    public TutoringSession(LocalDateTime startDateTime, LocalDateTime endDateTime, Tutor tutor) {
        this.id = 0;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.tutorId = tutor.getId();
        this.tutor = tutor;
    }

    @Override
    public boolean equals(Object obj) {
    	if (obj == null) 
            return false;
        final TutoringSession other = (TutoringSession) obj;
        if (other == null)
        	return false;
        return other.id == this.id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}