package org.dcsc.uploader;

/**
 * Created by tktong on 7/31/2015.
 */
public class UploadResult {
    private static final UploadResult FAIL = new UploadResult(null, false);

    private final String uploadPath;
    private final boolean success;

    public static UploadResult success(String uploadPath) {
        return new UploadResult(uploadPath, true);
    }

    public static UploadResult fail() {
        return FAIL;
    }

    private UploadResult(String uploadPath, boolean success) {
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
