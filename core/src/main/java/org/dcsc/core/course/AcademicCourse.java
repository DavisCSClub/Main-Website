package org.dcsc.core.course;

import javax.persistence.*;

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
}
