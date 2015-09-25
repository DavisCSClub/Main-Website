package org.dcsc.unit.admin;

import org.dcsc.core.model.activity.Activity;
import org.dcsc.core.service.activity.ActivityService;
import org.dcsc.compound.presentation.controller.AdminProfileController;
import org.dcsc.core.model.user.DcscUser;
import org.dcsc.core.model.user.details.DcscUserDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminProfileControllerTest {
    private static final long ID = 0;
    private static final String EXPECTED_VIEW = "admin/profile";

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private AdminProfileController adminProfileController;

    @Mock
    private Authentication authentication;
    @Mock
    private Model model;
    @Mock
    private DcscUserDetails dcscUserDetails;
    @Mock
    private DcscUser dcscUser;
    @Mock
    private List<Activity> expectedList;

    @Test
    public void profile() {
        Mockito.when(authentication.getPrincipal()).thenReturn(dcscUserDetails);
        Mockito.when(dcscUserDetails.getUser()).thenReturn(dcscUser);
        Mockito.when(dcscUser.getId()).thenReturn(ID);
        Mockito.when(activityService.getAllActivities(ID)).thenReturn(expectedList);

        String actualView = adminProfileController.profile(authentication, model);

        Mockito.verify(model).addAttribute("activities", expectedList);

        Assert.assertEquals(EXPECTED_VIEW, actualView);
    }
}