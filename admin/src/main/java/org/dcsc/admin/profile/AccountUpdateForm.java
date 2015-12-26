package org.dcsc.admin.profile;

/**
 * Created by tktong on 12/25/2015.
 */
public class AccountUpdateForm {
    private String title;
    private String role;
    private boolean isActive;
    private boolean isLocked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }
}
