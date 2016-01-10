package org.dcsc.admin.dto;

import java.util.List;

public class TutorCourseUpdateForm {
    private List<String> courses;
    
    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
