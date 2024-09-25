package ru.webapp.vinogradiya.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Value("${SPRING_DATASOURCE_STATIC}")
    private String STATIC_DIRECTORY;
    @GetMapping()
    public List<String> getImages(Model model) {
        File folder = new File(STATIC_DIRECTORY + "/images");
        File[] listOfFiles = folder.listFiles();
        List<String> imageNames = new ArrayList<>();

        Optional.ofNullable(listOfFiles).orElseThrow(() -> new NullPointerException(""));
        for (File file : listOfFiles) {
            if (file.isFile() && !file.getName().equals("default.png")) {
                imageNames.add(file.getName());
            }
        }
        return imageNames;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image")MultipartFile file)
    {
        try {
            Path fileNameAndPath = Paths.get(STATIC_DIRECTORY + "/images",
                    file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            return ResponseEntity.ok("Изображение успешно загружено: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при загрузке изображения");
        }
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException e) {
        return new ResponseEntity<>("File not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
