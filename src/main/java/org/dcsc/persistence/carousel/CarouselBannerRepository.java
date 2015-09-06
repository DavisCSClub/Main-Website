package org.dcsc.persistence.carousel;

import org.dcsc.model.carousel.CarouselBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by tktong on 7/31/2015.
 */
@Repository
public interface CarouselBannerRepository extends JpaRepository<CarouselBanner, Long> {
    Optional<CarouselBanner> findCarouselBannerById(long id);
}
