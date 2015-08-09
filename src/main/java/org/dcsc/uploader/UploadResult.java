package org.dcsc.uploader;

/**
 * Created by tktong on 7/31/2015.
 *
 * Provides the results of all file uploads onto the server.
 *
 * Only callable by static methods {@link #success(String)} and {@link #fail()}. In case of success, must also provide location of which file was
 * uploaded to.
 */
public class UploadResult {
    private static final UploadResult FAIL = new UploadResult(null, false);

    private final String uploadPath;
    private final boolean success;

    /**
     * Returns a new UploadResult with the destination of uploaded file.
     *
     * @param uploadPath    Destination of upload file
     * @return              Result of upload (boolean) and destination of uploaded file
     */
    public static UploadResult success(String uploadPath) {
        return new UploadResult(uploadPath, true);
    }

    /**
     * Returns a constant result of failed upload
     *
     * @return  Static instance - {@link #FAIL}
     */
    public static UploadResult fail() {
        return FAIL;
    }

    private UploadResult(String uploadPath, boolean success) {
        this.uploadPath = uploadPath;
        this.success = success;
    }

    /**
     * Should call {@link #isSuccess() isSuccess} method before calling this
     *
     * @return Destination of uploaded file
     */
    public String getUploadPath() {
        return uploadPath;
    }

    /**
     * Always call this function before attempting to move forward with the upload.
     *
     * @return  Boolean value representing whether the upload was successful or not
     */
    public boolean isSuccess() {
        return success;
    }
}
