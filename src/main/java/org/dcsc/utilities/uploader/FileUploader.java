package org.dcsc.utilities.uploader;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploader {
    void upload(MultipartFile file, String destination) throws IOException;
}
