package org.dcsc.admin.controllers;

import org.dcsc.core.navigation.NavigationLink;
import org.dcsc.core.navigation.NavigationProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private NavigationProvider navigationProvider;

    @RequestMapping(value = "/admin/r", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<NavigationLink> getNavigation(Authentication authentication) {
        return navigationProvider.getNavigation(authentication);
    }
}
