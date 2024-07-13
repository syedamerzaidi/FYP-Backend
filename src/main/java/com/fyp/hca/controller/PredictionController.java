package com.fyp.hca.controller;

import com.fyp.hca.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/predictions")
    public List<PredictionService.Prediction> getPredictions(@RequestParam("folder") String folder) {
        return predictionService.fetchPredictions(folder);
    }
}
