package org.dcsc.unit.activity;

import org.dcsc.activity.Actions;
import org.dcsc.activity.Activity;
import org.dcsc.activity.ActivityRepository;
import org.dcsc.activity.ActivityService;
import org.dcsc.security.user.DcscUser;
import org.dcsc.security.userdetails.DcscUserDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ActivityService.class, SecurityContextHolder.class})
public class ActivityServiceTest {
    private static final long ID = 0;
    private static final long TIME = 0;
    private static final String TARGET = "target";
    private static final String DESCRIPTION = "description";
    private static final Actions ACTION = Actions.CREATE;

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityService activityService;

    @Mock
    private List<Activity> expectedActivityList;
    @Mock
    private Activity expectedActivity;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    @Mock
    private DcscUserDetails dcscUserDetails;
    @Mock
    private DcscUser user;
    @Mock
    private Calendar calendar;
    @Mock
    private Date date;
    @Mock
    private java.sql.Date expectedDate;
    @Mock
    private Time expectedTime;

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

    @Test
    public void saveWithActivity() {
        Mockito.when(activityRepository.save(expectedActivity)).thenReturn(expectedActivity);

        activityService.save(expectedActivity);

        Mockito.verify(activityRepository).save(expectedActivity);
    }

    @Test
    public void saveWithExpandedParameters() throws Exception {
        PowerMockito.mockStatic(SecurityContextHolder.class);
        PowerMockito.mockStatic(Calendar.class);

        Mockito.when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(dcscUserDetails);
        Mockito.when(dcscUserDetails.getUser()).thenReturn(user);
        Mockito.when(Calendar.getInstance()).thenReturn(calendar);
        Mockito.when(calendar.getTime()).thenReturn(date);
        Mockito.when(date.getTime()).thenReturn(TIME);

        PowerMockito.whenNew(java.sql.Date.class).withArguments(TIME).thenReturn(expectedDate);
        PowerMockito.whenNew(Time.class).withArguments(TIME).thenReturn(expectedTime);
        PowerMockito.whenNew(Activity.class).withNoArguments().thenReturn(expectedActivity);

        Mockito.when(activityRepository.save(expectedActivity)).thenReturn(expectedActivity);

        activityService.save(TARGET, DESCRIPTION, ACTION);

        Mockito.verify(expectedActivity).setUser(user);
        Mockito.verify(expectedActivity).setTarget(TARGET);
        Mockito.verify(expectedActivity).setDescription(DESCRIPTION);
        Mockito.verify(expectedActivity).setAction(ACTION);
        Mockito.verify(expectedActivity).setDate(expectedDate);
        Mockito.verify(expectedActivity).setTime(expectedTime);
    }
}