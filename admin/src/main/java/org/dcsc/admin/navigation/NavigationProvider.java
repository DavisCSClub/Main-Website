package org.dcsc.admin.navigation;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.details.DcscUserDetails;
import org.dcsc.core.user.group.Group;
import org.dcsc.core.user.group.UserGroup;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class NavigationProvider {
    public static final NavigationLink DASHBOARD = new NavigationLink("Dashboard", "restricted.dashboard", "&#xE871;");
    public static final NavigationLink DIRECTORY = new NavigationLink("Directory", "restricted.directory", "&#xE7FB;");
    public static final NavigationLink TUTORING_EDIT = new NavigationLink("Edit Courses", "restricted.tutoringCourses", "&#xE7FB;");
    public static final NavigationLink TUTORING_CALENDAR = new NavigationLink("Office Hour", "restricted.tutoringCalendar", "&#xE7FB;");
    public static final NavigationLink TUTORING_MAIN = new NavigationLink("Find Tutee", "findTutee", "&#xE7FB;");
    public static final NavigationLink TUTORING = new NavigationLink("Tutoring", "&#xE54B;", ImmutableList.of(TUTORING_MAIN, TUTORING_CALENDAR, TUTORING_EDIT));
    public static final NavigationLink CAROUSEL = new NavigationLink("Carousel", "restricted.carousel", "&#xE41B;");
    public static final NavigationLink EVENT = new NavigationLink("Events", "restricted.events", "&#xE878;");

    private static final Map<String, NavigationLink> OFFICER_LINKS = new HashMap();

    @PostConstruct
    @VisibleForTesting
    public void initialize() {
        OFFICER_LINKS.put("CAROUSEL", CAROUSEL);
        OFFICER_LINKS.put("EVENT", EVENT);
    }

    public Collection<NavigationLink> getNavigation(Authentication authentication) {
        Collection<NavigationLink> navigationLinks = new ArrayList<>();

        addDefaultLinks(navigationLinks);
        addOfficerLinks(navigationLinks, authentication);
        addTutoringLinks(navigationLinks, authentication);

        return navigationLinks;
    }

    private void addDefaultLinks(Collection<NavigationLink> navigationLinks) {
        navigationLinks.add(DASHBOARD);
        navigationLinks.add(DIRECTORY);
    }

    private void addOfficerLinks(Collection<NavigationLink> navigationLinks, Authentication authentication) {
        DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();
        Map<String, Integer> permissions = userDetails.getPermissions();

        for (Map.Entry<String, Integer> entrySet : permissions.entrySet()) {
            String category = entrySet.getKey().toUpperCase();
            Integer accessLevel = entrySet.getValue();

            NavigationLink navLink = OFFICER_LINKS.get(category);

            if (navLink != null && accessLevel > 0) {
                navigationLinks.add(navLink);
            }
        }
    }

    private void addTutoringLinks(Collection<NavigationLink> navigationLinks, Authentication authentication) {
        DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();
        DcscUser user = userDetails.getUser();

        boolean isTutoring = user.getUserGroups().stream()
                .map(UserGroup::getGroup)
                .map(Group::getName)
                .anyMatch("Tutoring Committee"::equals);

        if (isTutoring) {
            navigationLinks.add(TUTORING);
        }
    }
}
