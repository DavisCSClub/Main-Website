package org.dcsc.admin.dto;

import java.util.Collection;

public class Navigation {
    private int id;
    private String title;
    private String icon;
    private String link;
    private Collection<Navigation> submenu;

    public Navigation(int id, String title, String icon, String link) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public Navigation(int id, String title, String icon, Collection<Navigation> submenu) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.submenu = submenu;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public String getLink() {
        return link;
    }

    public Collection<Navigation> getSubmenu() {
        return submenu;
    }
}
