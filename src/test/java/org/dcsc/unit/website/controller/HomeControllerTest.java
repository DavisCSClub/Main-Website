package org.dcsc.unit.website.controller;


import org.dcsc.carousel.CarouselBanner;
import org.dcsc.carousel.CarouselBannerService;
import org.dcsc.website.controller.HomeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.List;


/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @Mock
    private CarouselBannerService carouselBannerService;

    @InjectMocks
    private HomeController homeController;

    @Mock
    private Model model;
    @Mock
    private List<CarouselBanner> carouselBannersList;

    @Test
    public void home() {
        Mockito.when(carouselBannerService.getAllCarouselBanners()).thenReturn(carouselBannersList);

        String view = homeController.home(model);

        Mockito.verify(model).addAttribute("carousel", carouselBannersList);

        Assert.assertEquals("main/home", view);
    }
}