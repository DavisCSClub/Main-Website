package org.dcsc.core.navigation;

import java.util.ArrayList;
import java.util.List;

public class NavigationBar {
    private List<NavigationLink> navigationLinks;

    public NavigationBar() {
        navigationLinks = new ArrayList<>();
    }

    public NavigationBar(List<NavigationLink> navigationLinks) {
        this.navigationLinks = navigationLinks;
    }

    public void addNavigationLink(NavigationLink navigationLink) {
        navigationLinks.add(navigationLink);
    }

    public void addNavigationLinks(List<NavigationLink> navigationLinkList) {
        navigationLinks.addAll(navigationLinkList);
    }

    public List<NavigationLink> getNavigationLinks() {
        return navigationLinks;
    }

    public void setNavigationLinks(List<NavigationLink> navigationLinks) {
        this.navigationLinks = navigationLinks;
    }
}
