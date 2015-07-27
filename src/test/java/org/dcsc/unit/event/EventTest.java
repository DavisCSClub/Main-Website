package org.dcsc.unit.event;

import org.dcsc.event.Event;
import org.dcsc.security.user.DcscUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventTest {
    private static final long ID = 0;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOCATION = "location";
    private static final String IMAGE_PATH = "image path";
    private static final String TAG = "tag";
    private static final boolean PUBLISHED = true;

    @Mock
    private Date date;
    @Mock
    private Time startTime;
    @Mock
    private Time endTime;
    @Mock
    private List<DcscUser> coordinators;

    private Event event;

    @Before
    public void before() {
        event = new Event();
    }

    @Test
    public void setAndGetId() {
        event.setId(ID);

        Assert.assertEquals(ID, event.getId());
    }

    @Test
    public void setAndGetName() {
        event.setName(NAME);

        Assert.assertEquals(NAME, event.getName());
    }

    @Test
    public void setAndGetDescription() {
        event.setDescription(DESCRIPTION);

        Assert.assertEquals(DESCRIPTION, event.getDescription());
    }

    @Test
    public void setAndGetLocation() {
        event.setLocation(LOCATION);

        Assert.assertEquals(LOCATION, event.getLocation());
    }

    @Test
    public void setAndGetImagePath() {
        event.setImagePath(IMAGE_PATH);

        Assert.assertEquals(IMAGE_PATH, event.getImagePath());
    }

    @Test
    public void setAndGetTag() {
        event.setTag(TAG);

        Assert.assertEquals(TAG, event.getTag());
    }

    @Test
    public void setAndGetPublished() {
        event.setPublished(PUBLISHED);

        Assert.assertEquals(PUBLISHED, event.isPublished());
    }

    @Test
    public void setAndGetDate() {
        event.setDate(date);

        Assert.assertEquals(date, event.getDate());
    }

    @Test
    public void setAndGetStartTime() {
        event.setStartTime(startTime);

        Assert.assertEquals(startTime, event.getStartTime());
    }

    @Test
    public void setAndGetEndTime() {
        event.setEndTime(endTime);

        Assert.assertEquals(endTime, event.getEndTime());
    }

    @Test
    public void setAndGetCoordinators() {
        event.setCoordinators(coordinators);

        Assert.assertEquals(coordinators, event.getCoordinators());
    }
}