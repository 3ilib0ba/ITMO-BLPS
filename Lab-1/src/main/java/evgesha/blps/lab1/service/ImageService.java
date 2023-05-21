package evgesha.blps.lab1.service;


import evgesha.blps.lab1.dto.ImageDto;
import evgesha.blps.lab1.exception.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${images.path}")
    private String storageFolderPath;

    private final String[] allowedFormats = {
            "jpeg", "jpg", "png", "bmp", "gif"
    };

    private final static double MAX_IMAGE_SIZE = Math.pow(2, 17);

    public ImageDto saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ImageEnterEmptyException();
        }
        if (file.getSize() > MAX_IMAGE_SIZE) {
            throw new ImageTooBigException();
        }

        String fullFormat = file.getContentType(); // here format like 'image/jpeg'
        String format = getShortImageFormat(fullFormat);
        String uuid = UUID.randomUUID().toString();
        String fullName = (uuid + "." + format);

        File savedFile = new File(Paths.get(storageFolderPath, fullName).toString());
        try {
            boolean created = savedFile.createNewFile();
            if (!created) {
                throw new ImageSavingException();
            }
            FileOutputStream outputStream = new FileOutputStream(savedFile);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            throw new ImageSavingException();
        }
        return new ImageDto(fullName);
    }

    public File getImage(String uuid) {
        return new File(Paths.get(storageFolderPath, uuid).toString());
    }

    public InputStreamResource getImageStream(String uuid) {
        try {
            return new InputStreamResource(new FileInputStream(Paths.get(storageFolderPath, uuid).toString()));
        } catch (FileNotFoundException e) {
            throw new ImageGettingException();
        }
    }

    private String getShortImageFormat(String fullFormat) {
        String[] splittedFormat = fullFormat.split("/");
        if (splittedFormat.length != 2) {
            throw new ImageSavingException();
        }

        String format = splittedFormat[1];
        checkFormatForAllowedType(format);

        return format;
    }

    private boolean checkFormatForAllowedType(String format) {
        Optional<String> typeOf = Arrays.stream(allowedFormats)
                .filter(type -> type.equals(format))
                .findAny();
        if (typeOf.isPresent()) {
            return true;
        }

        throw new ImageNotAllowedFileTypeException();
    }

}
