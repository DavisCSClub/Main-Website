package org.dcsc.utilities.uploader;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by tktong on 7/31/2015.
 */
@Component
public class ImageFileUploader implements FileUploader {
    private static final String UPLOAD_DIRECTORY_PATH = new File(System.getProperty("user.home")).getAbsolutePath()
            + File.separator + "webapps" + File.separator + "media_dcsc"
            + File.separator + "upload" + File.separator + "img";

    @Override
    public UploadResult upload(MultipartFile file) {
        UploadResult uploadResult = null;

        if(file.isEmpty()) {
            uploadResult =  UploadResult.fail();
        }
        else {
            try {
                File directory = new File(UPLOAD_DIRECTORY_PATH);

                if(!directory.exists()) {
                    directory.mkdirs();
                }

                String uploadPath = UPLOAD_DIRECTORY_PATH + File.separator + file.getOriginalFilename();

                File fileToUpload = new File(uploadPath);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileToUpload));

                byte[] bytes = file.getBytes();
                stream.write(bytes);
                stream.close();

                uploadResult = UploadResult.success(uploadPath);
            } catch(Exception e) {
                e.printStackTrace();

                uploadResult = UploadResult.fail();
            }
        }

        return uploadResult;
    }
}
