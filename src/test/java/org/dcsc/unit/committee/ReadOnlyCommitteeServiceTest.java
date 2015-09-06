package org.dcsc.unit.committee;

import org.dcsc.model.committee.Committee;
import org.dcsc.persistence.committee.CommitteeRepository;
import org.dcsc.service.committee.ReadOnlyCommitteeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;


/**
 * Created by tktong on 7/11/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Optional.class)
public class ReadOnlyCommitteeServiceTest {
    @Mock private CommitteeRepository committeeRepository;
    @Mock private Optional<Committee> optionalCommittee;
    @Mock private Committee expectedCommittee;

    @InjectMocks
    private ReadOnlyCommitteeService readOnlyCommitteeService;

    @Test
    public void getCommitteeByTag() throws Exception {
        String tag = "tag";

        Mockito.when(committeeRepository.findCommitteeByTag(tag)).thenReturn(optionalCommittee);
        Mockito.when(optionalCommittee.isPresent()).thenReturn(true);
        Mockito.when(optionalCommittee.get()).thenReturn(expectedCommittee);

        Optional<Committee> actualOptionalCommittee = readOnlyCommitteeService.getCommitteeByTag(tag);

        Assert.assertTrue(actualOptionalCommittee.isPresent());
        Assert.assertEquals(expectedCommittee, optionalCommittee.get());
    }
}