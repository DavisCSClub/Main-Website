package org.dcsc.committee;

import javax.persistence.*;
import java.sql.Time;
import java.time.DayOfWeek;

/**
 * Created by tktong on 7/9/2015.
 */
@Entity
@Table(name = "dcsc_committees", schema = "public")
public class Committee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;

    @Column(name = "chair")
    private String chair;

    @Column(name = "vice_chair")
    private String viceChair;

    @Column(name = "is_cochair")
    private boolean viceIsCoChair;

    @Column(name = "description")
    private String description;

    @Column(name = "meeting_day")
    @Enumerated(EnumType.STRING)
    private DayOfWeek  meetingDay;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "banner")
    private String bannerImagePath;

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

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getViceChair() {
        return viceChair;
    }

    public void setViceChair(String viceChair) {
        this.viceChair = viceChair;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DayOfWeek getMeetingDay() {
        return meetingDay;
    }

    public void setMeetingDay(DayOfWeek meetingDay) {
        this.meetingDay = meetingDay;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBannerImagePath() {
        return bannerImagePath;
    }

    public void setBannerImagePath(String bannerImagePath) {
        this.bannerImagePath = bannerImagePath;
    }

    public boolean isViceIsCoChair() {
        return viceIsCoChair;
    }

    public void setViceIsCoChair(boolean viceIsCoChair) {
        this.viceIsCoChair = viceIsCoChair;
    }
}
