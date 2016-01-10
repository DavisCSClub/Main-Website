package org.dcsc.core.navigation;

public interface NavigationLink {
    String getIcon();

    String getLink();

    String getText();

    boolean isNested();
}