package com.fyp.hca.controller;

import com.fyp.hca.services.UploadDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile; // Import statement should be here
import java.io.IOException;

@Controller
public class UploadDataController {

    private final UploadDataService uploadDataService;

    public UploadDataController(UploadDataService uploadDataService) {
        this.uploadDataService = uploadDataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String message = uploadDataService.uploadFile(file);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
