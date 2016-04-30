package org.dcsc.admin.controllers;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.core.carousel.CarouselBannerService;
import org.dcsc.core.carousel.CarouselForm;
import org.dcsc.core.carousel.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RestController
public class AdminCarouselRestController {
    @Autowired
    private CarouselBannerService carouselBannerService;

    @RequestMapping(value = "/admin/r/carousel/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission('carousel','delete')")
    public RestTransactionResult deleteCarousel(@PathVariable("id") String id) {
        RestTransactionResult result = null;

        try {
            carouselBannerService.delete(Long.parseLong(id));
            result = new RestTransactionResult(true, String.format("Banner #%s successfully deleted.", id));
        } catch (EntityIdNotFoundException e) {
            result = new RestTransactionResult(false, String.format("Failed to delete banner #%s", id));
        }

        return result;
    }

    @CrossOrigin(origins = {"https://daviscsclub.org", "http://localhost:8080"}, methods = {RequestMethod.PUT})
    @RequestMapping(value = "/admin/r/carousel/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('carousel','update')")
    public boolean updateBanner(@RequestBody CarouselForm carouselForm) {
        carouselBannerService.save(carouselForm);
        return true;
    }
}
