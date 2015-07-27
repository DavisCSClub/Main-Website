package org.dcsc.unit.activity;

import org.dcsc.activity.Activity;
import org.dcsc.activity.ActivityRepository;
import org.dcsc.activity.ActivityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceTest {
    private static final long ID = 0;

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private List<Activity> expectedActivityList;

    @Test
    public void getAllActivitiesById() {
        Mockito.when(activityRepository.findAllActivityByUserId(ID)).thenReturn(expectedActivityList);

        Assert.assertEquals(expectedActivityList, activityService.getAllActivities(ID));
    }

    @Test
    public void getAllActivities() {
        Mockito.when(activityRepository.findAll()).thenReturn(expectedActivityList);

        Assert.assertEquals(expectedActivityList, activityService.getAllActivities());
    }
}