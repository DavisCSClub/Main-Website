package org.dcsc.admin.navigation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NavigationLink {
    private String title;
    private String icon;
    private String link;
    private Collection<NavigationLink> submenu;


    public NavigationLink(String title, String link, String icon) {
        this.title = title;
        this.link = link;
        this.icon = icon;
    }

    public NavigationLink(String title, String icon, Collection<NavigationLink> submenu) {
        this.title = title;
        this.icon = icon;
        this.submenu = submenu;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setSubmenu(Collection<NavigationLink> submenu) {
        this.submenu = submenu;
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

    public Collection<NavigationLink> getSubmenu() {
        return submenu;
    }
}