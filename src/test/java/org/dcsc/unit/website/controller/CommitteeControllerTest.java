package org.dcsc.unit.website.controller;

import org.dcsc.committee.Committee;
import org.dcsc.committee.ReadOnlyCommitteeService;
import org.dcsc.website.controller.CommitteeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import java.util.Optional;

/**
 * Created by tktong on 7/26/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Optional.class)
public class CommitteeControllerTest {
    private static final String EXPECTED_VIEW = "main/committee";
    private static final String EXPECTED_REDIRECT = "redirect:/error/404";
    private static final String TAG = "";

    @Mock private ReadOnlyCommitteeService readOnlyCommitteeService;
    @Mock private Model model;
    @Mock private Optional<Committee> expectedOptional;
    @Mock private Committee expectedCommittee;

    @InjectMocks
    private CommitteeController committeeController;

    @Test
    public void validCommitteePage() {
        Mockito.when(readOnlyCommitteeService.getCommitteeByTag(TAG)).thenReturn(expectedOptional);
        ReflectionTestUtils.setField(expectedOptional, "value", expectedCommittee);

        String actualView = committeeController.tutoring(TAG, model);

        Mockito.verify(model).addAttribute("committee", expectedCommittee);

        Assert.assertEquals(EXPECTED_VIEW, actualView);
    }

    @Test
    public void invalidCommitteePage() {
        Mockito.when(readOnlyCommitteeService.getCommitteeByTag(TAG)).thenReturn(expectedOptional);
        ReflectionTestUtils.setField(expectedOptional, "value", null);

        String actualView = committeeController.tutoring(TAG, model);

        Assert.assertEquals(EXPECTED_REDIRECT, actualView);
    }
}