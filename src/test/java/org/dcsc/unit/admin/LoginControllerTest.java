package org.dcsc.unit.admin;

import org.dcsc.presentation.controllers.portal.LoginController;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tktong on 7/16/15.
 */
public class LoginControllerTest {
    private LoginController loginController = new LoginController();

    @Test
    public void login() {
        String view = loginController.login();

        Assert.assertEquals("admin/login", view);
    }

}
