package org.dcsc.admin.dto;

public class FullCalendarEventBuilder {
    private long id;
    private String title;
    private String start;
    private String end;
    private String color;

    public FullCalendarEventBuilder id(long id) {
        this.id = id;
        return this;
    }

    public FullCalendarEventBuilder title(String title) {
        this.title = title;
        return this;
    }

    public FullCalendarEventBuilder start(String start) {
        this.start = start;
        return this;
    }

    public FullCalendarEventBuilder end(String end) {
        this.end = end;
        return this;
    }

    public FullCalendarEventBuilder color(String color) {
        this.color = color;
        return this;
    }

    public FullCalendarEvent build() {
        return new FullCalendarEvent(id, title, start, end, color);
    }
}
