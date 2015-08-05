package org.dcsc.admin;

import org.dcsc.carousel.CarouselBanner;
import org.dcsc.carousel.CarouselBannerService;
import org.dcsc.uploader.ImageFileUploader;
import org.dcsc.uploader.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

/**
 * Created by tktong on 7/31/2015.
 */
@Controller
public class CarouselController {
    @Autowired
    private CarouselBannerService carouselBannerService;
    @Autowired
    private ImageFileUploader imageFileUploader;

    @RequestMapping(value = "/admin/carousel", method = RequestMethod.GET)
    public String carouselUpload(Model model) {
        model.addAttribute("banners", carouselBannerService.getAllCarouselBanners());

        return "admin/carousel";
    }

    @RequestMapping(value = "/admin/carousel", method = RequestMethod.POST)
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

    @RequestMapping(value = "/admin/carousel/delete")
    public String delete(@RequestParam("id") long id) {
        Optional<CarouselBanner> carouselBanner = carouselBannerService.getCarouselById(id);

        if(carouselBanner.isPresent()) {
            File file  = new File(carouselBanner.get().getPath());

            file.delete();

            carouselBannerService.delete(id);
        }

        return "redirect:/admin/carousel";
    }
}
