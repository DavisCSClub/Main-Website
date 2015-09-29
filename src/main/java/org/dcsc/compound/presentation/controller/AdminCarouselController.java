package org.dcsc.compound.presentation.controller;

import org.dcsc.core.model.carousel.CarouselBanner;
import org.dcsc.core.service.carousel.CarouselBannerService;
import org.dcsc.core.model.carousel.EntityIdNotFoundException;
import org.dcsc.utilities.uploader.ImageFileUploader;
import org.dcsc.utilities.uploader.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tktong on 7/31/2015.
 */
@Controller
@RequestMapping(value = "admin/carousel")
public class AdminCarouselController {
    @Autowired
    private CarouselBannerService carouselBannerService;
    @Autowired
    private ImageFileUploader imageFileUploader;

    @RequestMapping(method = RequestMethod.GET)
    public String carouselUpload(Model model) {
        model.addAttribute("banners", carouselBannerService.getAllCarouselBanners());

        return "admin/carousel";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String carouselUpload(@RequestParam("name") String name, @RequestParam("caption") String caption, @RequestParam("file") MultipartFile file) {
        UploadResult result = imageFileUploader.upload(file);

        if(result.isSuccess()) {
            CarouselBanner carouselBanner = new CarouselBanner();
            carouselBanner.setName(name);
            carouselBanner.setPath(result.getUploadPath());
            carouselBanner.setCaption(caption);
            carouselBannerService.save(carouselBanner);
        }

        return "redirect:/admin/carousel";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") long id) {
        try {
            carouselBannerService.delete(id);
        } catch(EntityIdNotFoundException e) {
            // redirect to error page
        }

        return "redirect:/admin/carousel";
    }
}
