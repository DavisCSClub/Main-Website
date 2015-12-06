package org.dcsc.admin.controllers;

import org.dcsc.core.carousel.CarouselBannerService;
import org.dcsc.core.carousel.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(value = "admin/carousel")
public class AdminCarouselController {
    @Autowired
    private CarouselBannerService carouselBannerService;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasPermission('CAROUSEL',read)")
    public String carouselUpload(Model model) {
        model.addAttribute("banners", carouselBannerService.getAllCarouselBanners());

        return "admin/carousel";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String carouselUpload(@RequestParam("name") String name, @RequestParam("caption") String caption, @RequestParam("file") MultipartFile file) {
        try {
            carouselBannerService.save(name, caption, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/admin/carousel";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") long id) {
        try {
            carouselBannerService.delete(id);
        } catch (EntityIdNotFoundException e) {
            // redirect to error page
        }

        return "redirect:/admin/carousel";
    }
}
