package org.dcsc.unit.admin;

import org.dcsc.admin.AdminCarouselController;
import org.dcsc.carousel.CarouselBanner;
import org.dcsc.carousel.CarouselBannerService;
import org.dcsc.uploader.ImageFileUploader;
import org.dcsc.uploader.UploadResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by tktong on 8/2/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({AdminCarouselController.class, CarouselBanner.class})
public class AdminCarouselControllerTest {
    private static final String NAME = "name";
    private static final String CAPTION = "caption";
    private static final String UPLOAD_PATH = "upload path";
    private static final long ID = 0;


    @Mock
    private CarouselBannerService carouselBannerService;
    @Mock
    private ImageFileUploader imageFileUploader;

    @InjectMocks
    private AdminCarouselController adminCarouselController;

    @Mock
    private Model model;
    @Mock
    private List<CarouselBanner> carouselBannerList;
    @Mock
    private MultipartFile multipartFile;
    @Mock
    private UploadResult uploadResult;
    @Mock
    private CarouselBanner carouselBanner;
    @Mock
    private Optional<CarouselBanner> carouselBannerOptional;
    @Mock
    private File file;

    @Test
    public void carouselUploadGetRequest() {
        Mockito.when(carouselBannerService.getAllCarouselBanners()).thenReturn(carouselBannerList);

        String view = adminCarouselController.carouselUpload(model);

        Mockito.verify(model).addAttribute("banners", carouselBannerList);

        Assert.assertEquals("admin/carousel", view);
    }

    @Test
    public void carouselUploadPostRequestWithSuccess() throws Exception {
        Mockito.when(imageFileUploader.upload(multipartFile)).thenReturn(uploadResult);
        Mockito.when(uploadResult.isSuccess()).thenReturn(true);
        PowerMockito.whenNew(CarouselBanner.class).withNoArguments().thenReturn(carouselBanner);
        Mockito.when(uploadResult.getUploadPath()).thenReturn(UPLOAD_PATH);
        Mockito.when(carouselBannerService.save(carouselBanner)).thenReturn(carouselBanner);

        String redirect = adminCarouselController.carouselUpload(NAME, CAPTION, multipartFile);

        Mockito.verify(carouselBanner).setName(NAME);
        Mockito.verify(carouselBanner).setCaption(CAPTION);
        Mockito.verify(carouselBanner).setPath(UPLOAD_PATH);

        Assert.assertEquals("redirect:/admin/carousel", redirect);
    }

    @Test
    public void carouselUploadPostRequestWithFailure() {
        Mockito.when(imageFileUploader.upload(multipartFile)).thenReturn(uploadResult);
        Mockito.when(uploadResult.isSuccess()).thenReturn(false);

        String redirect = adminCarouselController.carouselUpload(NAME, CAPTION, multipartFile);

        Assert.assertEquals("redirect:/admin/carousel", redirect);
    }

    @Test
    public void deleteSuccess() throws Exception {
        Mockito.when(carouselBannerService.getCarouselById(ID)).thenReturn(carouselBannerOptional);
        Mockito.when(carouselBannerOptional.isPresent()).thenReturn(true);
        Mockito.when(carouselBannerOptional.get()).thenReturn(carouselBanner);
        Mockito.when(carouselBanner.getPath()).thenReturn(UPLOAD_PATH);
        PowerMockito.whenNew(File.class).withArguments(UPLOAD_PATH).thenReturn(file);
        Mockito.when(carouselBannerService.delete(ID)).thenReturn(true);

        String redirect = adminCarouselController.delete(ID);

        Mockito.verify(file).delete();

        Assert.assertEquals("redirect:/admin/carousel", redirect);
    }

    @Test
    public void deleteInvalidId() {
        Mockito.when(carouselBannerService.getCarouselById(ID)).thenReturn(carouselBannerOptional);
        Mockito.when(carouselBannerOptional.isPresent()).thenReturn(false);

        String redirect = adminCarouselController.delete(ID);

        Assert.assertEquals("redirect:/admin/carousel", redirect);
    }
}