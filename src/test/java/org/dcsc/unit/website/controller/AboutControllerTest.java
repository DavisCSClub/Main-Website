package org.dcsc.unit.website.controller;

import org.dcsc.controllers.mainwebsite.AboutController;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by tktong on 7/8/2015.
 */
public class AboutControllerTest {
    private AboutController aboutController = new AboutController();

    @Test
    public void about() {
        String view = aboutController.about();

        Assert.assertEquals("main/about", view);
    }
}