package org.dcsc.core.tutor;

import java.io.Serializable;

public class TutorRelationId implements Serializable {
    private static final long serialVersionUID = -6347097393703895888L;

    private long tutorId;
    private long courseId;
    private long termId;
    
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
}
