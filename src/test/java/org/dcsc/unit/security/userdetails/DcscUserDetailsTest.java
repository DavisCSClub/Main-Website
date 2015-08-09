package org.dcsc.unit.security.userdetails;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.Role;
import org.dcsc.security.user.details.DcscUserDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.userdetails.User;
import org.testng.Assert;

import java.util.List;

/**
 * Created by tktong on 7/26/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DcscUserDetails.class)
public class DcscUserDetailsTest {
    private static final Role role = Role.ROLE_USER;

    @Mock
    private DcscUser dcscUser;

    DcscUserDetails dcscUserDetails;

    @Test
    public void constructorAndSetUser() {
        // Suppress the super constructor
        PowerMockito.suppress(PowerMockito.constructor(User.class,String.class,String.class,List.class));

        Mockito.when(dcscUser.getRole()).thenReturn(role);

        dcscUserDetails = new DcscUserDetails(dcscUser);

        Mockito.verify(dcscUser).getUsername();
        Mockito.verify(dcscUser).getPassword();
        Mockito.verify(dcscUser).getRole();

        Assert.assertEquals(dcscUser, dcscUserDetails.getUser());
    }
}