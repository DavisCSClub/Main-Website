package org.dcsc.core.navigation;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Component
public class NavigationBarFactory {
    public static final NavigationLink CAROUSEL = new NavigationLink("Carousel", "/admin/carousel", "&#xE41B;");
    public static final NavigationLink DASHBOARD = new NavigationLink("Dashboard", "/admin", "&#xE871;");
    public static final NavigationLink DIRECTORY = new NavigationLink("Directory", "/admin/directory", "&#xE7FB;");
    public static final NavigationLink EVENT_LIST = new NavigationLink("Events", "/admin/events", "&#xE878;");

    private static final Map<String, NavigationLink> navigationLinks = new HashMap<>();

    @PostConstruct
    private void initialize() {
        navigationLinks.put("CAROUSEL", CAROUSEL);
        navigationLinks.put("USER", DIRECTORY);
        navigationLinks.put("EVENT", EVENT_LIST);
    }

    public NavigationBar getNavigationBar(Map<String, Integer> permissionMap) {
        NavigationBar navbar = new NavigationBar();
        navbar.addNavigationLinks(DASHBOARD);

        Set<Map.Entry<String, Integer>> permissionEntrySet = permissionMap.entrySet();

        for (Map.Entry<String, Integer> entrySet : permissionEntrySet) {
            String category = StringUtils.toUpperCase(entrySet.getKey(), Locale.ENGLISH);
            Integer accessLevel = entrySet.getValue();

            NavigationLink navLink = navigationLinks.get(category);

            if (navLink != null && accessLevel > 0) {
                navbar.addNavigationLinks(navLink);
            }
        }

        return navbar;
    }
}
