package org.dcsc.unit.activity;

import org.dcsc.core.model.activity.Action;
import org.dcsc.core.model.activity.Activity;
import org.dcsc.core.model.user.DcscUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityTest {
    private static final long ID = 0;
    private static final String TARGET = "target";
    private static final String DESCRIPTION = "description";
    private static final Action ACTION = Action.CREATE;

    @Mock
    private DcscUser dcscUser;
    @Mock
    private Date date;
    @Mock
    private Time time;

    private Activity activity;

    @Before
    public void before() {
        activity = new Activity();
    }

    @Test
    public void setAndGetId() {
        activity.setId(ID);

        Assert.assertEquals(ID, activity.getId());
    }

    @Test
    public void setAndGetUser() {
        activity.setUser(dcscUser);

        Assert.assertEquals(dcscUser, activity.getUser());
    }

    @Test
    public void setAndGetAction() {
        activity.setAction(ACTION);

        Assert.assertEquals(ACTION, activity.getAction());
    }

    @Test
    public void setAndGetTarget() {
        activity.setTarget(TARGET);

        Assert.assertEquals(TARGET, activity.getTarget());
    }

    @Test
    public void setAndGetDescription() {
        activity.setDescription(DESCRIPTION);

        Assert.assertEquals(DESCRIPTION, activity.getDescription());
    }

    @Test
    public void setAndGetDate() {
        activity.setDate(date);

        Assert.assertEquals(date, activity.getDate());
    }

    @Test
    public void setAndGetTime() {
        activity.setTime(time);

        Assert.assertEquals(time, activity.getTime());
    }
}