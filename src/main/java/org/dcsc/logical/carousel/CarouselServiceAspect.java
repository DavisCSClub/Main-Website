package org.dcsc.logical.carousel;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.dcsc.model.activity.Action;
import org.dcsc.service.activity.ActivityService;
import org.dcsc.model.carousel.CarouselBanner;
import org.dcsc.model.carousel.EntityIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tktong on 8/8/2015.
 */
@Aspect
@Component
public class CarouselServiceAspect {
    @Autowired
    private ActivityService activityService;

    @AfterReturning(
            pointcut = "execution(* org.dcsc.service.carousel.CarouselBannerService.save(org.dcsc.model.carousel.CarouselBanner))",
            returning = "result"
    )
    public void saveCarousel(JoinPoint joinPoint, Object result) {
        CarouselBanner carouselBanner = (CarouselBanner) result;

        logActivity(String.format("Carousel banner #%d (%s) created.", carouselBanner.getId(), carouselBanner.getName()), Action.CREATE);
    }

    @AfterReturning(
            pointcut = "execution(* org.dcsc.model.carousel.CarouselBannerService.delete(long))",
            returning = "result"
    )
    public void deleteCarouselSuccess(JoinPoint joinPoint, Object result) {
        CarouselBanner carouselBanner = (CarouselBanner) result;

        logActivity(String.format("Carousel banner #%d (%s) deleted.", carouselBanner.getId(), carouselBanner.getName()), Action.DELETE);
    }

    @AfterThrowing(
            pointcut = "execution(* org.dcsc.model.carousel.CarouselBannerService.delete(long))",
            throwing = "exception"
    )
    public void deleteCarouselFailure(JoinPoint joinPoint, Throwable exception) {
        EntityIdNotFoundException e = (EntityIdNotFoundException) exception;

        logActivity(String.format("Carousel banner #%d could not be deleted. Id does not exists.", e.getId()), Action.DELETE);
    }

    private void logActivity(String description, Action action) {
        activityService.save("Carousel", description, action);
    }
}
