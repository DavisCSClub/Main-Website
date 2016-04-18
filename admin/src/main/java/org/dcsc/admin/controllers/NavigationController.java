package org.dcsc.admin.controllers;

import org.dcsc.core.navigation.NavigationBar;
import org.dcsc.core.navigation.NavigationLink;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * REST endpoint for navigation in angular
 */
@RestController
public class NavigationController {
    @RequestMapping(value = "/admin/r", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<NavigationLink> getNavigation(Authentication authentication) {
        DcscUserDetails dcscUserDetails = (DcscUserDetails) authentication.getPrincipal();
        NavigationBar navigationBar = dcscUserDetails.getNavigationBar();

        for (NavigationLink link : navigationBar.getNavigationLinks()) {
            String uri = link.getLink();

            if ("/admin/events".equals(uri)) {
                link.setLink("restricted.events");
            } else if ("/admin/".equals(uri)) {
                link.setLink("restricted.dashboard");
            }
        }

        return navigationBar.getNavigationLinks();
    }
}
