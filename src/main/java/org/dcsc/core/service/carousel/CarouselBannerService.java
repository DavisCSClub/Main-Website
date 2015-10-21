package org.dcsc.core.service.carousel;

import org.dcsc.core.model.carousel.CarouselBanner;
import org.dcsc.core.model.carousel.EntityIdNotFoundException;
import org.dcsc.core.model.image.Image;
import org.dcsc.core.persistence.carousel.CarouselBannerRepository;
import org.dcsc.core.persistence.image.ImageRepository;
import org.dcsc.utilities.uploader.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class CarouselBannerService {
    @Autowired
    private CarouselBannerRepository carouselBannerRepository;
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private ImageRepository imageRepository;

    public void save(String name, String caption, MultipartFile file) throws IOException {
        Image image = imageUploadService.upload(file, name, caption);

        CarouselBanner carouselBanner = new CarouselBanner();
        carouselBanner.setCaption(caption);
        carouselBanner.setImage(image);

        save(carouselBanner);
    }

    public CarouselBanner save(CarouselBanner carouselBanner) {
        return carouselBannerRepository.save(carouselBanner);
    }

    public void delete(long id) throws EntityIdNotFoundException {
        CarouselBanner carouselBanner = carouselBannerRepository.findCarouselBannerById(id)
                .orElseThrow(() -> new EntityIdNotFoundException(id, String.format("Carousel banner #%d could not be found.", id)));

        File file = new File(carouselBanner.getImage().getAbsolutePath());
        file.delete();

        imageRepository.delete(carouselBanner.getImage());
        carouselBannerRepository.delete(carouselBanner);
    }

    @Transactional(readOnly = true)
    public Optional<CarouselBanner> getCarouselById(long id) {
        return carouselBannerRepository.findCarouselBannerById(id);
    }

    @Transactional(readOnly = true)
    public List<CarouselBanner> getAllCarouselBanners() {
        return carouselBannerRepository.findAll();
    }
}
