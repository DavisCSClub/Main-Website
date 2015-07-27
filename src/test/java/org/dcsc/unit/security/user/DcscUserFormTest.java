package org.dcsc.unit.security.user;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class DcscUserFormTest {
    private static final long DEFAULT_ID = -1;
    private static final String DEFAULT_PASSWORD = "";

    private static final long ID = 0;
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirm_password";

    @Mock
    private DcscUser dcscUser;

    private DcscUserForm dcscUserForm;

    @Test
    public void constructorWithUserParameter() {
        Mockito.when(dcscUser.getId()).thenReturn(ID);
        Mockito.when(dcscUser.getUsername()).thenReturn(USERNAME);
        Mockito.when(dcscUser.getEmail()).thenReturn(EMAIL);
        Mockito.when(dcscUser.getName()).thenReturn(NAME);

        dcscUserForm = new DcscUserForm(dcscUser);

        Assert.assertEquals(ID, dcscUserForm.getId());
        Assert.assertEquals(USERNAME, dcscUserForm.getUsername());
        Assert.assertEquals(EMAIL, dcscUserForm.getEmail());
        Assert.assertEquals(NAME, dcscUserForm.getName());
        Assert.assertEquals(DEFAULT_PASSWORD, dcscUserForm.getPassword());
    }

    @Test
    public void defaultId() {
        dcscUserForm = new DcscUserForm();

        Assert.assertEquals(DEFAULT_ID, dcscUserForm.getId());
    }

    @Test
    public void setAndGetId() {
        dcscUserForm = new DcscUserForm();
        dcscUserForm.setId(ID);

        Assert.assertEquals(ID, dcscUserForm.getId());
    }

    @Test
    public void setAndGetUsername() {
        dcscUserForm = new DcscUserForm();
        dcscUserForm.setUsername(USERNAME);

        Assert.assertEquals(USERNAME, dcscUserForm.getUsername());
    }

    @Test
    public void setAndGetEmail() {
        dcscUserForm = new DcscUserForm();
        dcscUserForm.setEmail(EMAIL);

        Assert.assertEquals(EMAIL, dcscUserForm.getEmail());
    }

    @Test
    public void setAndGetName() {
        dcscUserForm = new DcscUserForm();
        dcscUserForm.setName(NAME);

        Assert.assertEquals(NAME, dcscUserForm.getName());
    }

    @Test
    public void setAndGetPassword() {
        dcscUserForm = new DcscUserForm();
        dcscUserForm.setPassword(PASSWORD);

        Assert.assertEquals(PASSWORD, dcscUserForm.getPassword());
    }

    @Test
    public void setAndGetConnfirmPassword() {
        dcscUserForm = new DcscUserForm();
        dcscUserForm.setConfirmPassword(CONFIRM_PASSWORD);

        Assert.assertEquals(CONFIRM_PASSWORD, dcscUserForm.getConfirmPassword());
    }
}