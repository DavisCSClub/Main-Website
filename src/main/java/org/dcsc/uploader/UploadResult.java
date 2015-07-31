package org.dcsc.uploader;

/**
 * Created by tktong on 7/31/2015.
 */
public class UploadResult {
    private final String uploadPath;
    private final boolean success;

    public UploadResult(String uploadPath, boolean success) {
        this.uploadPath = uploadPath;
        this.success = success;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public boolean isSuccess() {
        return success;
    }
}
