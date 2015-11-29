package org.dcsc.admin.controllers;

import org.dcsc.core.carousel.CarouselBannerService;
import org.dcsc.core.carousel.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCarouselRestController {
    @Autowired
    private CarouselBannerService carouselBannerService;

    @RequestMapping(value = "/admin/r/carousel/{id}", method = RequestMethod.DELETE)
    public boolean deleteCarousel(@PathVariable("id") String id) {
        boolean success = false;

        try {
            carouselBannerService.delete(Long.parseLong(id));
            success = true;
        } catch (EntityIdNotFoundException e) {
            // swallow exception
        }

        return success;
    }
}
