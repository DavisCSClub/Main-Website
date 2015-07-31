package org.dcsc.unit.website.controller;


import org.dcsc.website.controller.HomeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @InjectMocks
    private HomeController homeController;

    @Test
    public void home() {
        String view = homeController.home();

        Assert.assertEquals("main/home", view);
    }
}