package org.dcsc.core.course;

import org.dcsc.core.tutor.TutorRelation;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dcsc_courses", schema = "dcsc_tutoring")
public class AcademicCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Example: "ECS30"
    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "academicCourse", fetch = FetchType.EAGER)
    List<TutorRelation> tutorRelations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TutorRelation> getTutorRelations() {
        return tutorRelations;
    }

    public void setTutorRelations(List<TutorRelation> tutorRelations) {
        this.tutorRelations = tutorRelations;
    }
}
