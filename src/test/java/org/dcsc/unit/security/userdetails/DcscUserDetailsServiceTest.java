package org.dcsc.unit.security.userdetails;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserService;
import org.dcsc.security.userdetails.DcscUserDetails;
import org.dcsc.security.userdetails.DcscUserDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DcscUserDetailsService.class)
public class DcscUserDetailsServiceTest {
    private static final String USERNAME = "";

    @Mock
    private DcscUserService dcscUserService;
    @Mock
    private Optional<DcscUser> expectedOptional;
    @Mock
    private DcscUserDetails expectedUserDetails;
    @Mock
    private DcscUser expectedUser;

    @InjectMocks
    private DcscUserDetailsService dcscUserDetailsService;

    @Test
    public void loadUserByUsername() throws Exception {
        Mockito.when(dcscUserService.getUserByUsername(USERNAME)).thenReturn(expectedOptional);
        Mockito.when(expectedOptional.isPresent()).thenReturn(true);
        Mockito.when(expectedOptional.get()).thenReturn(expectedUser);

        PowerMockito.whenNew(DcscUserDetails.class).withAnyArguments().thenReturn(expectedUserDetails);
        Mockito.when(expectedUserDetails.getUser()).thenReturn(expectedUser);

        DcscUserDetails dcscUserDetails = (DcscUserDetails) dcscUserDetailsService.loadUserByUsername(USERNAME);

        Assert.assertEquals(expectedUser, dcscUserDetails.getUser());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void userNotFound() {
        Mockito.when(dcscUserService.getUserByUsername(USERNAME)).thenReturn(expectedOptional);
        Mockito.when(expectedOptional.isPresent()).thenReturn(false);

        dcscUserDetailsService.loadUserByUsername(USERNAME);
    }
}