package com.fyp.hca.services;

import com.fyp.hca.entity.Disease;
import com.fyp.hca.repositories.DashboardHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardHomeService {

    @Autowired
    private DashboardHomeRepository dashboardHomeRepository;

    public DashboardHomeService(DashboardHomeRepository dashboardHomeRepository) {
        this.dashboardHomeRepository = dashboardHomeRepository;
    }

    public Long getTotalPopulation(Disease disease) {
        return dashboardHomeRepository.getTotalPopulation(disease);
    }

    public Long getRecoveredPopulation(Disease disease) {
        return dashboardHomeRepository.getRecoveredPopulation(disease);
    }

    public Long getDeathPopulation(Disease disease) {
        return dashboardHomeRepository.getDeathPopulation(disease);
    }

    public Long getChronicPopulation(Disease disease) {
        return dashboardHomeRepository.getChronicPopulation(disease);
    }
}
