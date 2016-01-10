package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.time.AcademicTerm;

import javax.persistence.*;

@Entity
@IdClass(TutorRelationId.class)
@Table(schema = "dcsc_tutoring", name = "dcsc_tutoring")
public class TutorRelation {
    @Id
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private AcademicCourse academicCourse;

    @Id
    @ManyToOne
    @JoinColumn(name = "term_id")
    private AcademicTerm academicTerm;

    public TutorRelation() {
    }

    public TutorRelation(Tutor tutor, AcademicCourse academicCourse, AcademicTerm academicTerm) {
        this.tutor = tutor;
        this.academicCourse = academicCourse;
        this.academicTerm = academicTerm;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public AcademicCourse getAcademicCourse() {
        return academicCourse;
    }

    public void setAcademicCourse(AcademicCourse academicCourse) {
        this.academicCourse = academicCourse;
    }

    public AcademicTerm getAcademicTerm() {
        return academicTerm;
    }

    public void setAcademicTerm(AcademicTerm academicTerm) {
        this.academicTerm = academicTerm;
    }
}
