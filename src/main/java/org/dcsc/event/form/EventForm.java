package org.dcsc.event.form;

import org.dcsc.event.Event;

/**
 * Created by tktong on 7/22/2015.
 */
public class EventForm {
    private long id = -1;
    private String name;
    private String description;
    private String date;
    private String startTime;
    private String endTime;

    private String location;
    private boolean published;

    public EventForm() {
    }

    public EventForm(Event event) {
        id = event.getId();
        name = event.getName();
        description = event.getDescription();
        date = event.getDate().toString();
        startTime = event.getStartTime().toString();
        endTime = event.getEndTime().toString();
        location = event.getLocation();
        published = event.isPublished();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
