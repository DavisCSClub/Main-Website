package org.dcsc.core.navigation;

public class StandardNavigationLink implements NavigationLink {
    private String name;
    private String link;
    private String icon;

    public StandardNavigationLink(String name, String link, String icon) {
        this.name = name;
        this.link = link;
        this.icon = icon;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public String getText() {
        return name;
    }

    @Override
    public boolean isNested() {
        return false;
    }
}
