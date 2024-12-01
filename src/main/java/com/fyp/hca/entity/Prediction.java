package com.fyp.hca.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prediction")
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @Column(name = "date", nullable = false, length = 255)
    private String date;
    @Column(name = "predicted_patients", nullable = false, length = 255)
    private double predictedPatients;
    @Column(name = "ventilators_required", nullable = false, length = 255)
    private double ventilatorsRequired;
    @Column(name = "oxygen_cylinders_required", nullable = false, length = 255)
    private double oxygenCylindersRequired;
}
