package org.dcsc.unit.security.user;


import org.dcsc.model.user.DcscUser;
import org.dcsc.model.user.Role;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tktong on 7/27/2015.
 */
public class DcscUserTest {
    private static final long ID = 0;
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final boolean ENABLED = true;
    private static final boolean LOCKED = true;
    private static final Role ROLE = Role.ROLE_USER;

    private DcscUser dcscUser;

    @Test
    public void constructor() {
        dcscUser = new DcscUser(ID, USERNAME, EMAIL, NAME, PASSWORD, ENABLED, LOCKED, ROLE);

        Assert.assertEquals(ID, dcscUser.getId());
        Assert.assertEquals(USERNAME, dcscUser.getUsername());
        Assert.assertEquals(EMAIL, dcscUser.getEmail());
        Assert.assertEquals(NAME, dcscUser.getName());
        Assert.assertEquals(PASSWORD, dcscUser.getPassword());
        Assert.assertEquals(ENABLED, dcscUser.isEnabled());
        Assert.assertEquals(LOCKED, dcscUser.isLocked());
        Assert.assertEquals(ROLE, dcscUser.getRole());
    }

    @Test
    public void setAndGetId() {
        dcscUser = new DcscUser();
        dcscUser.setId(ID);

        Assert.assertEquals(ID, dcscUser.getId());
    }

    @Test
    public void setAndGetUsername() {
        dcscUser = new DcscUser();
        dcscUser.setUsername(USERNAME);

        Assert.assertEquals(USERNAME, dcscUser.getUsername());
    }

    @Test
    public void setAndGetName() {
        dcscUser = new DcscUser();
        dcscUser.setName(NAME);

        Assert.assertEquals(NAME, dcscUser.getName());
    }

    @Test
    public void setAndGetEmail() {
        dcscUser = new DcscUser();
        dcscUser.setEmail(EMAIL);
    }

    @Test
    public void setAndGetPassword() {
        dcscUser = new DcscUser();
        dcscUser.setPassword(PASSWORD);

        Assert.assertEquals(PASSWORD, dcscUser.getPassword());
    }

    @Test
    public void setAndGetEnabled() {
        dcscUser = new DcscUser();
        dcscUser.setEnabled(ENABLED);

        Assert.assertEquals(ENABLED, dcscUser.isEnabled());
    }

    @Test
    public void setAndGetLocked() {
        dcscUser = new DcscUser();
        dcscUser.setLocked(LOCKED);

        Assert.assertEquals(LOCKED, dcscUser.isLocked());
    }

    @Test
    public void setAndGetRole() {
        dcscUser = new DcscUser();
        dcscUser.setRole(ROLE);

        Assert.assertEquals(ROLE, dcscUser.getRole());
    }
}