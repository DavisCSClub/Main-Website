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
    @Column(name = "id")
    private long id;

    @Column(name = "start_timestamp")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startDateTime;

    @Column(name = "end_timestamp")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime endDateTime;

	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;

    public TutoringSession() {
    }

    public TutoringSession(LocalDateTime startDateTime, Tutor tutor) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.tutor = tutor;
    }

    public TutoringSession(LocalDateTime startDateTime, LocalDateTime endDateTime, Tutor tutor) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.tutor = tutor;
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