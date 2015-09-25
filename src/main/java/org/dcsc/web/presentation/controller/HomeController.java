package org.dcsc.web.presentation.controller;

import org.dcsc.web.presentation.constants.ModelAttributeNames;
import org.dcsc.web.presentation.constants.ViewNames;
import org.dcsc.core.service.carousel.CarouselBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/7/2015.
 */
@Controller
public class HomeController {
    @Autowired
    private CarouselBannerService carouselBannerService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute(ModelAttributeNames.CAROUSEL, carouselBannerService.getAllCarouselBanners());

        return ViewNames.HOME;
    }
}
