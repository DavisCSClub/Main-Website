package org.dcsc.core.tutor;

import org.dcsc.core.time.AcademicTerm;
import org.dcsc.utilities.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dcsc_office_hours", schema = "dcsc_tutoring")
public class OfficeHour {
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

    @OneToOne
    @JoinColumn(name = "term_id")
    private AcademicTerm academicTerm;

    @OneToOne
    @JoinColumn(name = "child_id")
    private OfficeHour childOfficeHour;

    @OneToOne
    @JoinTable(schema = "dcsc_tutoring", name = "has_office_hours",
            joinColumns = {
                    @JoinColumn(name = "office_hour_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
            }
    )
    private Tutor tutor;

    public OfficeHour() {
    }

    public OfficeHour(LocalDateTime startDateTime, LocalDateTime endDateTime, AcademicTerm academicTerm, Tutor tutor) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.academicTerm = academicTerm;
        this.tutor = tutor;
    }

    public OfficeHour(LocalDateTime startDateTime, LocalDateTime endDateTime, AcademicTerm academicTerm, OfficeHour childOfficeHour, Tutor tutor) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.academicTerm = academicTerm;
        this.childOfficeHour = childOfficeHour;
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

    public AcademicTerm getAcademicTerm() {
        return academicTerm;
    }

    public void setAcademicTerm(AcademicTerm academicTerm) {
        this.academicTerm = academicTerm;
    }

    public OfficeHour getChildOfficeHour() {
        return childOfficeHour;
    }

    public void setChildOfficeHour(OfficeHour childOfficeHour) {
        this.childOfficeHour = childOfficeHour;
    }


    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
