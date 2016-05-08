package org.dcsc.admin.calendar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.dcsc.utilities.serializer.LocalDateTimeJsonDeserializer;

import java.time.LocalDateTime;

public class TutorOfficeHourTransaction {
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    private LocalDateTime start;
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    private LocalDateTime end;
    private boolean repeat;

    public TutorOfficeHourTransaction() {

    }

    public TutorOfficeHourTransaction(LocalDateTime start, LocalDateTime end, boolean repeat) {
        this.start = start;
        this.end = end;
        this.repeat = repeat;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
