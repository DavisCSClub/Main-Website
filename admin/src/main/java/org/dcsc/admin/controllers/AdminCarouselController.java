package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.admin.constants.ViewNames;
import org.dcsc.core.carousel.CarouselBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Deprecated
@Controller
@RequestMapping(value = "/admin/carousel")
public class AdminCarouselController {
    @Autowired
    private CarouselBannerService carouselBannerService;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('carousel','read')")
    public String carouselUpload(Model model) {
        model.addAttribute(AttributeNames.BANNERS, carouselBannerService.getAllCarouselBanners());

        return ViewNames.ADMIN_CAROUSEL;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasPermission('carousel','create')")
    public String carouselUpload(@RequestParam("name") String name, @RequestParam("caption") String caption, @RequestParam("file") MultipartFile file) {
        try {
            carouselBannerService.save(name, caption, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/admin/carousel";
    }
}
