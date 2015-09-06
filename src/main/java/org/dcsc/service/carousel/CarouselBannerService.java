package org.dcsc.service.carousel;

import org.dcsc.model.carousel.EntityIdNotFoundException;
import org.dcsc.model.carousel.CarouselBanner;
import org.dcsc.persistence.carousel.CarouselBannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/31/2015.
 */
@Service
public class CarouselBannerService {
    @Autowired
    private CarouselBannerRepository carouselBannerRepository;

    public CarouselBanner save(CarouselBanner carouselBanner) {
        return carouselBannerRepository.save(carouselBanner);
    }

    public void delete(long id) throws EntityIdNotFoundException {
        CarouselBanner carouselBanner = carouselBannerRepository.findCarouselBannerById(id)
                .orElseThrow(() -> new EntityIdNotFoundException(id, String.format("Carousel banner #%d could not be found.", id)));

        File file = new File(carouselBanner.getPath());
        file.delete();

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
