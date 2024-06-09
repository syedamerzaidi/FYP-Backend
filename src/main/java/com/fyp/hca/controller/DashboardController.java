package com.fyp.hca.controller;

import com.fyp.hca.model.FiltersRequestModel;
import com.fyp.hca.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping("/getDashboardData/{userId}/{diseaseId}")
    public ResponseEntity<?> getDashboardData(@PathVariable Integer userId,
                                              @PathVariable Integer diseaseId,
                                              @RequestBody(required = false) FiltersRequestModel filters) {
        try {
            return ResponseEntity.ok(dashboardService.getDashboardData(userId, diseaseId, filters));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}