package org.dcsc.core.navigation;

import java.util.List;

public class NestedNavigationLink implements NavigationLink {
    private static final String NESTED_LINK = "#";

    private String text;
    private String icon;
    List<NavigationLink> navigationLinks;

    public NestedNavigationLink(String text, String icon, List<NavigationLink> navigationLinks) {
        this.text = text;
        this.icon = icon;
        this.navigationLinks = navigationLinks;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getLink() {
        return NESTED_LINK;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isNested() {
        return true;
    }

    public static String getNestedLink() {
        return NESTED_LINK;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<NavigationLink> getNavigationLinks() {
        return navigationLinks;
    }

    public void setNavigationLinks(List<NavigationLink> navigationLinks) {
        this.navigationLinks = navigationLinks;
    }
}
