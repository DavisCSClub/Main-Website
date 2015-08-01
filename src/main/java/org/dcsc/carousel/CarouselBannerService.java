package org.dcsc.carousel;

import org.dcsc.activity.Actions;
import org.dcsc.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/31/2015.
 */
@Service
public class CarouselBannerService {
    @Autowired
    private CarouselBannerRepository carouselBannerRepository;
    @Autowired
    private ActivityService activityService;

    @Transactional(readOnly = true)
    public Optional<CarouselBanner> getCarouselById(long id) {
        return carouselBannerRepository.findCarouselBannerById(id);
    }

    @Transactional(readOnly = true)
    public List<CarouselBanner> getAllCarouselBanners() {
        return carouselBannerRepository.findAll();
    }

    public CarouselBanner save(CarouselBanner carouselBanner) {
        CarouselBanner banner =  carouselBannerRepository.save(carouselBanner);

        activityService.save("Carousel", "Created banner " + carouselBanner.getName() + ".", Actions.CREATE);

        return carouselBanner;
    }

    public boolean delete(long id) {
        boolean success = false;

        try {
            carouselBannerRepository.delete(id);
            success = true;

            activityService.save("Carousel", "Deleted banner id#" + id, Actions.DELETE);
        } catch(IllegalArgumentException e) {
            // Exception thrown when ID is invalid.
            // Swallowing Exception and Returning Success = false
            activityService.save("Carousel", "Failed to delete banner id#" + id, Actions.DELETE);
        }

        return success;
    }
}
