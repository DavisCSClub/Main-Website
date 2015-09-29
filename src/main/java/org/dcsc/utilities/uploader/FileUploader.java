package org.dcsc.utilities.uploader;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by tktong on 7/31/2015.
 */
public interface FileUploader {
    UploadResult upload(MultipartFile file);
}
