package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.service.ImageService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final static double MAX_IMAGE_SIZE = Math.pow(2, 17);

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(new MessageDto("image is empty"), HttpStatus.BAD_REQUEST);
        }
        if (file.getSize() > MAX_IMAGE_SIZE) {
            return new ResponseEntity<>(new MessageDto("image is too large"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(imageService.saveImage(file));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> downloadImage(
            @PathVariable("uuid") String filename
    ) {
        File file = imageService.getImage(filename);
        InputStreamResource imageStream = imageService.getImageStream(filename);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(imageStream);
    }


}
