package org.dcsc.admin.dto;


public class TutorManagementChangeSet {
    private int userId;
    private boolean remove;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
