package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Patient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*, columnDefinition = "serial",*/ ,nullable = true)
    private int id;
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;
    @Column(name = "cnic", nullable = true, length = 255)
    private String cnic;
    @Column(name = "gender", nullable = true, length = 255)
    private String gender;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "blood", nullable = false, length = 255)
    private Boolean blood;
    @Column(name = "chronicdisease", nullable = false, length = 255)
    private Boolean chronicdisease;
    @Column(name = "diabetes", nullable = true)
    private Boolean diabetes;
    @Column(name = "high_Fever", nullable = true)
    private Boolean highFever;
    @Column(name = "fever", nullable = true)
    private Boolean fever;
    @Column(name = "hypertension", nullable = true)
    private Boolean hypertension;
    @Column(name = "cardiac", nullable = true)
    private Boolean cardiac;
    @Column(name = "weakness_pain", nullable = true)
    private Boolean weaknessPain;
    @Column(name = "respiratory", nullable = true)
    private Boolean respiratory;
    @Column(name = "cancer", nullable = true)
    private Boolean cancer;
    @Column(name = "thyroid", nullable = true)
    private Boolean thyroid;
    @Column(name = "prostate", nullable = true)
    private Boolean prostate;
    @Column(name = "kidney", nullable = true)
    private Boolean kidney;
    @Column(name = "neuro", nullable = true)
    private Boolean neuro;
    @Column(name = "nausea", nullable = true)
    private Boolean nausea;
    @Column(name = "asymptomatic", nullable = true)
    private Boolean asymptomatic;
    @Column(name = "gastrointestinal", nullable = true)
    private Boolean gastrointestinal;
    @Column(name = "ortho", nullable = true)
    private Boolean ortho;
    @Column(name = "respiratory_CD", nullable = true)
    private Boolean respiratoryCD;
    @Column(name = "cardiacs_cd", nullable = true)
    private Boolean cardiacsCD;
    @Column(name = "kidney_CD", nullable = true)
    private Boolean kidneyCD;
    @Column(name = "admission_date", nullable = true)
    private Date admissionDate;
    @Column(name = "death_binary", nullable = true)
    private Boolean deathBinary;

    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id", nullable = true)
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "id")
    private Disease disease;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && age == patient.age && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(cnic, patient.cnic) && Objects.equals(gender, patient.gender) && Objects.equals(blood, patient.blood) && Objects.equals(chronicdisease, patient.chronicdisease) && Objects.equals(diabetes, patient.diabetes) && Objects.equals(highFever, patient.highFever) && Objects.equals(fever, patient.fever) && Objects.equals(hypertension, patient.hypertension) && Objects.equals(cardiac, patient.cardiac) && Objects.equals(weaknessPain, patient.weaknessPain) && Objects.equals(respiratory, patient.respiratory) && Objects.equals(cancer, patient.cancer) && Objects.equals(thyroid, patient.thyroid) && Objects.equals(prostate, patient.prostate) && Objects.equals(kidney, patient.kidney) && Objects.equals(neuro, patient.neuro) && Objects.equals(nausea, patient.nausea) && Objects.equals(asymptomatic, patient.asymptomatic) && Objects.equals(gastrointestinal, patient.gastrointestinal) && Objects.equals(ortho, patient.ortho) && Objects.equals(respiratoryCD, patient.respiratoryCD) && Objects.equals(cardiacsCD, patient.cardiacsCD) && Objects.equals(kidneyCD, patient.kidneyCD) && Objects.equals(admissionDate, patient.admissionDate) && Objects.equals(deathBinary, patient.deathBinary) && Objects.equals(hospital, patient.hospital) && Objects.equals(disease, patient.disease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, cnic, gender, age, blood, chronicdisease, diabetes, highFever, fever, hypertension, cardiac, weaknessPain, respiratory, cancer, thyroid, prostate, kidney, neuro, nausea, asymptomatic, gastrointestinal, ortho, respiratoryCD, cardiacsCD, kidneyCD, admissionDate, deathBinary, hospital, disease);
    }
}