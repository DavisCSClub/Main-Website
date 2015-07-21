package org.dcsc.event;

import org.dcsc.security.user.DcscUser;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by tktong on 7/20/2015.
 */
@Entity
@Table(name = "event_plan", schema = "administration")
public class EventPlanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "location")
    private String location;

    @Column(name = "image_path")
    private String imagePath;


    @ManyToMany
    @JoinTable(name = "event_coordinators", schema = "administration",
                joinColumns = {
                        @JoinColumn(name = "event_id", referencedColumnName = "id")
                },
                inverseJoinColumns = {
                        @JoinColumn(name = "user_id", referencedColumnName = "id")
                })
    private List<DcscUser> coordinators;

    @Column(name = "published_to_website")
    private boolean publishedToWebsite;

    @Column(name = "published_to_facebook")
    private boolean publishedToFacebook;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isPublishedToWebsite() {
        return publishedToWebsite;
    }

    public void setPublishedToWebsite(boolean publishedToWebsite) {
        this.publishedToWebsite = publishedToWebsite;
    }

    public boolean isPublishedToFacebook() {
        return publishedToFacebook;
    }

    public void setPublishedToFacebook(boolean publishedToFacebook) {
        this.publishedToFacebook = publishedToFacebook;
    }
}
