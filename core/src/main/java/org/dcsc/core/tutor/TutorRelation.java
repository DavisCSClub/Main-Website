package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.time.AcademicTerm;

import javax.persistence.*;

@Entity
@IdClass(TutorRelationId.class)
@Table(schema = "dcsc_tutoring", name = "dcsc_tutoring")
public class TutorRelation {
    @Id
    @Column(name = "tutor_id")
    private long tutorId;

    @Id
    @Column(name = "course_id")
    private long courseId;

    @Id
    @Column(name = "term_id")
    private long termId;

    @Transient
    private Tutor tutor;
    @Transient
    private AcademicCourse academicCourse;
    @Transient
    private AcademicTerm academicTerm;

    public TutorRelation() {
    }

    public TutorRelation(long tutorId, long courseId, long termId) {
        this.tutorId = tutorId;
        this.courseId = courseId;
        this.termId = termId;
    }

    public TutorRelation(long tutorId, long courseId, long termId, Tutor tutor, AcademicCourse academicCourse, AcademicTerm academicTerm) {
        this.tutorId = tutorId;
        this.courseId = courseId;
        this.termId = termId;
        this.tutor = tutor;
        this.academicCourse = academicCourse;
        this.academicTerm = academicTerm;
    }

    public long getTutorId() {
        return tutorId;
    }

    public void setTutorId(long tutorId) {
        this.tutorId = tutorId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
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