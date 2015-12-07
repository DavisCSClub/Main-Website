package org.dcsc.admin.controllers;

import org.dcsc.core.carousel.CarouselBannerService;
import org.dcsc.core.carousel.CarouselForm;
import org.dcsc.core.carousel.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminCarouselRestController {
    @Autowired
    private CarouselBannerService carouselBannerService;

    @RequestMapping(value = "/admin/r/carousel/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission('carousel',delete)")
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

    @CrossOrigin(origins = {"https://daviscsclub.org", "http://localhost:8080"}, methods = {RequestMethod.PUT})
    @RequestMapping(value = "/admin/r/carousel/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('carousel',update)")
    public boolean updateBanner(@RequestBody CarouselForm carouselForm) {
        carouselBannerService.save(carouselForm);
        return true;
    }
}
