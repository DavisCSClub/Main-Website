package org.dcsc.web.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dcsc.utilities.serializer.ZonedDateTimeJsonDeserializer;
import org.springframework.social.facebook.api.CoverPhoto;
import org.springframework.social.facebook.api.FacebookObject;
import org.springframework.social.facebook.api.Place;

import java.time.ZonedDateTime;

public class FacebookEvent extends FacebookObject {
    private String id;
    private String name;
    private String description;
    private CoverPhoto cover;
    private Place place;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("cover")
    public CoverPhoto getCover() {
        return cover;
    }

    @JsonProperty("place")
    public Place getPlace() {
        return place;
    }

    @JsonProperty("start_time")
    @JsonDeserialize(using = ZonedDateTimeJsonDeserializer.class)
    public ZonedDateTime getStartTime() {
        return startTime;
    }

    @JsonProperty("end_time")
    @JsonDeserialize(using = ZonedDateTimeJsonDeserializer.class)
    public ZonedDateTime getEndTime() {
        return endTime;
    }
}
