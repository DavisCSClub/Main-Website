package org.dcsc.utilities.uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageUploadService {
    public static final String IMAGE_UPLOAD_DIRECTORY = new File(System.getProperty("user.home")).getAbsolutePath()
            + File.separator + "webapps" + File.separator + "media_dcsc" + File.separator + "upload"
            + File.separator + "img";
    public static final String IMAGE_RELATIVE_DIRECTORY = "/media/upload/upload/img";

    @Autowired
    private FileUploader fileUploader;

    public void upload(MultipartFile file) throws IOException {
        fileUploader.upload(file, IMAGE_UPLOAD_DIRECTORY);
    }
}
