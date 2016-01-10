package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.user.DcscUser;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dcsc_tutors", schema = "dcsc_tutoring")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "common_id")
    private DcscUser dcscUser;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<TutorRelation> tutorRelations;

    public Tutor() {
    }

    public Tutor(DcscUser dcscUser) {
        this.id = 0;
        this.dcscUser = dcscUser;
        this.isActive = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DcscUser getDcscUser() {
        return dcscUser;
    }

    public void setDcscUser(DcscUser dcscUser) {
        this.dcscUser = dcscUser;
    }

    public List<TutorRelation> getTutorRelations() {
        return tutorRelations;
    }

    public void setTutorRelations(List<TutorRelation> tutorRelations) {
        this.tutorRelations = tutorRelations;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addTutorRelation(TutorRelation tutorRelation) {
        tutorRelations.add(tutorRelation);
    }

    public List<AcademicCourse> getCourses(String academicTermCode) {
        List<AcademicCourse> courses = null;

        if (academicTermCode != null) {
            List<TutorRelation> termedTutorRelations = getTutorRelations(academicTermCode);
            courses = termedTutorRelations.stream().filter(r -> academicTermCode.equals(r.getAcademicTerm().getCode()))
                    .map(r -> r.getAcademicCourse()).collect(Collectors.toList());
        }

        return courses;
    }

    public void removeAcademicCourse(AcademicCourse course) {
        tutorRelations.removeIf(relation -> relation.getAcademicCourse().getCode().equals(course.getCode()));
    }

    public void removeTutorRelations(String academicTermCode) {
        tutorRelations.removeAll(getTutorRelations(academicTermCode));
    }

    public List<TutorRelation> getTutorRelations(String academicTermCode) {
        return tutorRelations.stream().filter(tutorRelation -> academicTermCode.equals(tutorRelation.getAcademicTerm().getCode()))
                .collect(Collectors.toList());
    }
}
