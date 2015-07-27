package org.dcsc.unit.admin;

import org.dcsc.admin.DashboardController;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by tktong on 7/16/15.
 */
public class DashboardControllerTest {
    private DashboardController dashboardController = new DashboardController();

    @Test
    public void dashboard() {
        String view = dashboardController.dashboard();

        Assert.assertEquals("admin/dashboard", view);
    }
}
