package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.carousel.CarouselResourceAssembler;
import org.dcsc.core.carousel.CarouselBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RequestMapping("/admin/r/carousel")
@RestController("adminCarouselController2")
public class CarouselController {
    @Autowired
    private CarouselBannerService carouselBannerService;
    @Autowired
    private CarouselResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Resource> getBanners() {
        return resourceAssembler.toResources(carouselBannerService.getAllCarouselBanners());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createBanner(@RequestParam("files[]") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            carouselBannerService.save("", "", file);
        }
    }

}
