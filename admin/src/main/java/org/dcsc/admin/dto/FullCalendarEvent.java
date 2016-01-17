package org.dcsc.admin.dto;

public class FullCalendarEvent {
    private String title;
    private String start;
    private String end;
    private String color;

    public FullCalendarEvent(String title, String start, String end, String color) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
