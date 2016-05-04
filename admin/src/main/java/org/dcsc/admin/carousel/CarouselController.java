package org.dcsc.admin.carousel;

import org.dcsc.core.carousel.CarouselBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasPermission('carousel', 'read')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Resource> getBanners() {
        return resourceAssembler.toResources(carouselBannerService.getAllCarouselBanners());
    }

    @PreAuthorize("hasPermission('carousel', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createBanner(@RequestParam("files[]") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            carouselBannerService.save("", "", file);
        }
    }

}
