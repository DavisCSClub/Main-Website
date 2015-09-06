package org.dcsc.unit.committee;

import org.dcsc.model.committee.Committee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.Time;
import java.time.DayOfWeek;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommitteeTest {
    private static final long ID = 0;
    private static final String NAME = "name";
    private static final String TAG = "tag";
    private static final String CHAIR = "chair";
    private static final String VICE_CHAIR = "vice-chair";
    private static final boolean VICE_IS_COCHAIR = true;
    private static final String DESCRIPTION = "description";
    private static final DayOfWeek DAY_OF_WEEK = DayOfWeek.SUNDAY;
    private static final String BANNER_IMAGE_PATH = "banner image path";

    @Mock
    private Time startTime;
    @Mock
    private Time endTime;

    private Committee committee;

    @Before
    public void before() {
        committee = new Committee();
    }

    @Test
    public void setAndGetId() {
        committee.setId(ID);

        Assert.assertEquals(ID, committee.getId());
    }

    @Test
    public void setAndGetName() {
        committee.setName(NAME);

        Assert.assertEquals(NAME, committee.getName());
    }

    @Test
    public void setAndGetChair() {
        committee.setChair(CHAIR);

        Assert.assertEquals(CHAIR, committee.getChair());
    }

    @Test
    public void setAndGetViceChair() {
        committee.setViceChair(VICE_CHAIR);

        Assert.assertEquals(VICE_CHAIR, committee.getViceChair());
    }

    @Test
    public void setAndGetViceChairIsCoChair() {
        committee.setViceIsCoChair(VICE_IS_COCHAIR);

        Assert.assertEquals(VICE_IS_COCHAIR, committee.isViceIsCoChair());
    }

    @Test
    public void setAndGetDescription() {
        committee.setDescription(DESCRIPTION);

        Assert.assertEquals(DESCRIPTION, committee.getDescription());
    }

    @Test
    public void setAndGetMeetingDay() {
        committee.setMeetingDay(DAY_OF_WEEK);

        Assert.assertEquals(DAY_OF_WEEK, committee.getMeetingDay());
    }

    @Test
    public void setAndGetTag() {
        committee.setTag(TAG);

        Assert.assertEquals(TAG, committee.getTag());
    }

    @Test
    public void setAndGetBannerImagePath() {
        committee.setBannerImagePath(BANNER_IMAGE_PATH);

        Assert.assertEquals(BANNER_IMAGE_PATH, committee.getBannerImagePath());
    }

    @Test
    public void setAndGetStartTime() {
        committee.setStartTime(startTime);

        Assert.assertEquals(startTime, committee.getStartTime());
    }

    @Test
    public void setAndGetEndTime() {
        committee.setEndTime(endTime);

        Assert.assertEquals(endTime, committee.getEndTime());
    }
}