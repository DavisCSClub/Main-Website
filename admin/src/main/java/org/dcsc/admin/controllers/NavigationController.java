package org.dcsc.admin.controllers;

import org.dcsc.core.navigation.NavigationBar;
import org.dcsc.core.navigation.NavigationLink;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST endpoint for navigation in angular
 */
@RestController
public class NavigationController {
    private static final Map<String, String> angularLinkMap = new HashMap<>();

    @PostConstruct
    private void initialize() {
        angularLinkMap.put("/admin/events", "restricted.events");
        angularLinkMap.put("/admin/directory", "restricted.directory");
        angularLinkMap.put("/admin/carousel", "restricted.carousel");
        angularLinkMap.put("/admin/", "restricted.dashboard");
    }

    @RequestMapping(value = "/admin/r", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<NavigationLink> getNavigation(Authentication authentication) {
        DcscUserDetails dcscUserDetails = (DcscUserDetails) authentication.getPrincipal();
        NavigationBar navigationBar = dcscUserDetails.getNavigationBar();

        for (NavigationLink link : navigationBar.getNavigationLinks()) {
            String uri = link.getLink();
            Optional.ofNullable(angularLinkMap.get(uri)).ifPresent(link::setLink);
        }

        return navigationBar.getNavigationLinks();
    }
}
