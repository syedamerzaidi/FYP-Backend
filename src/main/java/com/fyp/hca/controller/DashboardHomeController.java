package com.fyp.hca.controller;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.services.DashboardHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class DashboardHomeController {
    @Autowired
    private DashboardHomeService dashboardHomeService;

    public DashboardHomeController(DashboardHomeService dashboardHomeService){this.dashboardHomeService = dashboardHomeService;}

    @GetMapping("/getTotalPopulation")
    public Long getDisease(@RequestParam Disease disease) {
        return dashboardHomeService.getTotalPopulation(disease);
    }

    @GetMapping("/getRecoveredPopulation")
    public Long getRecoveredPopulation(@RequestBody Disease disease) {
        return dashboardHomeService.getRecoveredPopulation(disease);
    }

    @GetMapping("/getDeathPopulation")
    public Long getDeathPopulation(@RequestBody Disease disease) {
        return dashboardHomeService.getDeathPopulation(disease);
    }

    @GetMapping("/getChronicPopulation")
    public Long getChronicPopulation(@RequestBody Disease disease) {
        return dashboardHomeService.getChronicPopulation(disease);
    }
}
