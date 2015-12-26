package org.dcsc.utilities.upload;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@Profile("production")
public class StandardFileUploader implements FileUploader {
    @Override
    public void upload(MultipartFile file, String destination) throws IOException {
        File directory = new File(destination);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File uploadFile = new File(destination + File.separator + file.getOriginalFilename());
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));

        stream.write(file.getBytes());
        stream.close();
    }
}
