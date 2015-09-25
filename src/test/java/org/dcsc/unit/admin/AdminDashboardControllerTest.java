package org.dcsc.unit.admin;

import org.dcsc.compound.presentation.controller.AdminDashboardController;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tktong on 7/16/15.
 */
public class AdminDashboardControllerTest {
    private static final String EXPECTED_VIEW = "admin/dashboard";

    private AdminDashboardController adminDashboardController = new AdminDashboardController();

    @Test
    public void dashboard() {
        String view = adminDashboardController.dashboard();

        Assert.assertEquals(EXPECTED_VIEW, view);
    }
}
