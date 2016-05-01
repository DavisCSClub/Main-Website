package org.dcsc.core.navigation;

import com.google.common.collect.ImmutableList;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.group.UserGroup;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class NavigationBarFactory {
    public static final NavigationLink DASHBOARD = new NavigationLink("Dashboard", "/admin/", "&#xE871;");
    public static final NavigationLink DIRECTORY = new NavigationLink("Directory", "/admin/directory", "&#xE7FB;");

    public static final NavigationLink TUTORING_DASHBOARD = new NavigationLink("Dashboard", "/admin/tutoring", "&#xE7FB;");
    public static final NavigationLink TUTORING_DIRECTORY = new NavigationLink("Directory", "/admin/directory", "&#xE7FB;");
    public static final NavigationLink TUTORING_EDIT = new NavigationLink("Edit Courses", "/admin/tutoring/tutor/edit", "&#xE7FB;");
    public static final NavigationLink TUTORING_CALENDAR = new NavigationLink("Office Hour", "/admin/tutoring/calendar", "&#xE7FB;");
    public static final NavigationLink TUTORING = new NavigationLink("Tutoring", "&#xE54B;", ImmutableList.of(TUTORING_CALENDAR, TUTORING_EDIT));

    private static final List<NavigationLink> MEMBER_LEVEL_LINKS = ImmutableList.of(DASHBOARD, DIRECTORY);
    private static final Map<String, NavigationLink> OFFICER_LINKS = new HashMap();

    @PostConstruct
    private void initialize() {
        OFFICER_LINKS.put("CAROUSEL", new NavigationLink("Carousel", "/admin/carousel", "&#xE41B;"));
        OFFICER_LINKS.put("EVENT", new NavigationLink("Events", "/admin/events", "&#xE878;"));
    }

    public NavigationBar getNavigationBar(DcscUser dcscUser, Map<String, Integer> permissionMap) {
        NavigationBar navbar = new NavigationBar();

        navbar.addNavigationLinks(MEMBER_LEVEL_LINKS);
        navbar.addNavigationLinks(getOfficerNavigationLinks(permissionMap));

        if (isTutoring(dcscUser)) {
            navbar.addNavigationLink(TUTORING);
        }

        return navbar;
    }

    private List<NavigationLink> getOfficerNavigationLinks(Map<String, Integer> permissionMap) {
        Set<Map.Entry<String, Integer>> permissionEntrySet = permissionMap.entrySet();
        List<NavigationLink> navigationLinks = new ArrayList<>();

        for (Map.Entry<String, Integer> entrySet : permissionEntrySet) {
            String category = StringUtils.toUpperCase(entrySet.getKey(), Locale.ENGLISH);
            Integer accessLevel = entrySet.getValue();

            NavigationLink navLink = OFFICER_LINKS.get(category);

            if (navLink != null && accessLevel > 0) {
                navigationLinks.add(navLink);
            }
        }

        return navigationLinks;
    }

    private boolean isTutoring(DcscUser dcscUser) {
        List<UserGroup> userGroups = dcscUser.getUserGroups();

        for (UserGroup userGroup : userGroups) {
            if ("Tutoring Committee".equals(userGroup.getGroup().getName())) {
                return true;
            }
        }

        return false;
    }
}
