package org.dcsc.unit.admin;

import org.dcsc.admin.DashboardController;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tktong on 7/16/15.
 */
public class DashboardControllerTest {
    private static final String EXPECTED_VIEW = "admin/dashboard";

    private DashboardController dashboardController = new DashboardController();

    @Test
    public void dashboard() {
        String view = dashboardController.dashboard();

        Assert.assertEquals(EXPECTED_VIEW, view);
    }
}
