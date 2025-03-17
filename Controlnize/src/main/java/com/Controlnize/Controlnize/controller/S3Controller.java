package com.Controlnize.Controlnize.controller;

import com.Controlnize.Controlnize.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String keyName = file.getOriginalFilename();
            s3Service.uploadFile(keyName, file.getInputStream(), file.getSize());
            return ResponseEntity.ok("File uploaded successfully: " + keyName);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/download/{keyName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String keyName) {
        try {
            InputStream inputStream = s3Service.downloadFile(keyName);
            byte[] fileContent = inputStream.readAllBytes();
            return ResponseEntity.ok(fileContent);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{keyName}")
    public ResponseEntity<String> deleteFile(@PathVariable String keyName) {
        s3Service.deleteFile(keyName);
        return ResponseEntity.ok("File deleted successfully: " + keyName);
    }
}