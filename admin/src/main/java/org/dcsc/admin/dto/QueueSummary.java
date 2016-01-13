package org.dcsc.admin.dto;

public class QueueSummary {
    private String course;
    private int size;

    public QueueSummary() {
        
    }

    public QueueSummary(String course, int size) {
        this.course = course;
        this.size = size;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
