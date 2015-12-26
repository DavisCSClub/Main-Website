package org.dcsc.admin.profile;

/**
 * Created by tktong on 12/7/2015.
 */
public class AccountForm {
    private String username;
    private String name;
    private String title;
    private String role;
    private String password;
    private String confirmPassword;
    private boolean isActive;
    private boolean isUnlocked;

    private boolean readOnlyPassword = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setIsUnlocked(boolean isUnlocked) {
        this.isUnlocked = isUnlocked;
    }

    public boolean isReadOnlyPassword() {
        return readOnlyPassword;
    }

    public void setReadOnlyPassword(boolean readOnlyPassword) {
        this.readOnlyPassword = readOnlyPassword;
    }
}
