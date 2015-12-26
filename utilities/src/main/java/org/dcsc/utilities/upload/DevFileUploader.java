package org.dcsc.utilities.upload;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@Profile("dev")
public class DevFileUploader implements FileUploader {
    private static final String TEMP_DIRECTORY_PROPERTY = "java.io.tmpdir";

    @Override
    public void upload(MultipartFile file, String destination) throws IOException {
        upload(file);
    }

    private void upload(MultipartFile file) throws IOException {
        String tempDir = System.getProperty(TEMP_DIRECTORY_PROPERTY);

        if (!tempDir.endsWith(File.separator)) {
            tempDir += File.separator;
        }

        String destination = tempDir + file.getName();

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
