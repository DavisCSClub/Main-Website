package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.carousel.CarouselResourceAssembler;
import org.dcsc.core.carousel.CarouselBanner;
import org.dcsc.core.carousel.CarouselBannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarouselControllerTest {
    @Mock
    private CarouselBannerService carouselBannerService;
    @Mock
    private CarouselResourceAssembler resourceAssembler;

    @InjectMocks
    private CarouselController carouselController;

    @Mock
    private List<CarouselBanner> banners;
    @Mock
    private List<Resource> resources;
    @Mock
    private MultipartFile file;


    @Test
    public void getBanners() {
        when(carouselBannerService.getAllCarouselBanners()).thenReturn(banners);
        when(resourceAssembler.toResources(banners)).thenReturn(resources);
        assertEquals(resources, carouselController.getBanners());
    }

    @Test
    public void uploadBanner() throws Exception {
        MultipartFile[] files = {file};
        carouselController.createBanner(files);
        verify(carouselBannerService).save("", "", file);
    }
}