package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.user.DcscUser;

import javax.persistence.*;
import java.util.List;
import java.util.TreeSet;

@Entity
@Table(name = "dcsc_tutors", schema = "dcsc_tutoring")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "is_active")
    private boolean isActive;

    
    @Column(name = "common_id")
    private long dcscId;

    @Transient
    private DcscUser dcscUser;

    @Transient
    private List<AcademicCourse> currentTermCourses;

    @Transient
    private List<OfficeHour> currentOfficeHours;

    public Tutor() {
    }

    public Tutor(DcscUser dcscUser) {
        this.id = 0;
        this.dcscUser = dcscUser;
        this.isActive = true;
    }

    public Tutor(long dcscId) {
        this.id = 0;
        this.dcscId = dcscId;
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

    public long getDcscUserId() {
        return dcscId;
    }

    public void setDcscUser(DcscUser dcscUser) {
        this.dcscUser = dcscUser;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<AcademicCourse> getCurrentTermCourses() {
        return currentTermCourses;
    }

    public TreeSet<String> getCurrentTermCourseStrings() {
        TreeSet<String> courseStrings = new TreeSet<String>();
        for (AcademicCourse a : getCurrentTermCourses()) {
            courseStrings.add(a.getCode().replaceAll("\\s+",""));
        }
        return courseStrings;
    }    

    public void setCurrentTermCourses(List<AcademicCourse> currentTermCourses) {
        this.currentTermCourses = currentTermCourses;
    }

    public List<OfficeHour> getCurrentOfficeHours() {
        return currentOfficeHours;
    }

    public void setCurrentOfficeHours(List<OfficeHour> currentOfficeHours) {
        this.currentOfficeHours = currentOfficeHours;
    }
}
