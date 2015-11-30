package org.dcsc.core.carousel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarouselBannerRepository extends JpaRepository<CarouselBanner, Long> {
    Optional<CarouselBanner> findCarouselBannerById(long id);
}
