package org.dcsc.admin.dto;

public class TutorOfficeHourDelete {
    private int id;
    private boolean deleteFuture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleteFuture() {
        return deleteFuture;
    }

    public void setDeleteFuture(boolean deleteFuture) {
        this.deleteFuture = deleteFuture;
    }
}
