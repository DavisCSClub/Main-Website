package org.dcsc.utilities.uploader;

import org.dcsc.core.model.image.Image;
import org.dcsc.core.model.image.ImageBuilder;
import org.dcsc.core.persistence.image.ImageRepository;
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
    @Autowired
    private ImageRepository imageRepository;

    public Image upload(MultipartFile file, String name, String description) throws IOException {
        fileUploader.upload(file, IMAGE_UPLOAD_DIRECTORY);

        String fileName = file.getOriginalFilename();
        String absolutePath = IMAGE_UPLOAD_DIRECTORY + File.separator + fileName;
        String relativePath = IMAGE_RELATIVE_DIRECTORY + File.separator + fileName;

        Image image = new ImageBuilder().setName(name).setDescription(description).setAbsolutePath(absolutePath)
                .setRelativePath(relativePath).build();

        return imageRepository.save(image);
    }
}
