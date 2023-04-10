package evgesha.blps.lab1.service;


import evgesha.blps.lab1.dto.ImageDto;
import evgesha.blps.lab1.exception.ImageGettingException;
import evgesha.blps.lab1.exception.ImageSavingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${images.path}")
    private String storageFolderPath;

    public ImageDto saveImage(MultipartFile file){
        String uuid = UUID.randomUUID().toString();
        File savedFile = new File(Paths.get(storageFolderPath, uuid).toString());
        try {
            boolean created = savedFile.createNewFile();
            if (!created){
                throw new ImageSavingException();

            }
            FileOutputStream outputStream = new FileOutputStream(savedFile);
            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            throw new ImageSavingException();
        }
        return new ImageDto(uuid);
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

}
