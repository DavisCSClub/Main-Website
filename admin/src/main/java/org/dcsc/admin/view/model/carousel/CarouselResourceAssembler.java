package org.dcsc.admin.view.model.carousel;

import org.dcsc.admin.controllers.CarouselController;
import org.dcsc.core.carousel.CarouselBanner;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CarouselResourceAssembler extends ResourceAssemblerSupport<CarouselBanner, Resource> {
    public CarouselResourceAssembler() {
        super(CarouselController.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends CarouselBanner> banners) {
        List<Resource> resources = new ArrayList<>();
        banners.forEach(banner -> resources.add(toResource(banner)));
        return resources;
    }

    @Override
    public Resource toResource(CarouselBanner banner) {
        return new Resource(banner, getRestEndpoint(banner));
    }

    private Link getRestEndpoint(CarouselBanner banner) {
        return linkTo(CarouselController.class).slash(banner.getId()).withSelfRel();
    }
}
