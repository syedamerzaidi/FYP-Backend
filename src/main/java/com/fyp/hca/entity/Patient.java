package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Patient extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial",nullable = true)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "cnic", nullable = true, length = 255)
    private String cnic;
    @Column(name = "gender", nullable = true, length = 255)
    private String gender;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "blood", nullable = false, length = 255)
    private String blood;
    @Column(name = "chronicdisease", nullable = false, length = 255)
    private String chronicdisease;
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
    @Column(name = "others", nullable = true, length = 255)
    private String others;
    @Column(name = "admission_date", nullable = true)
    private Date admissionDate;


    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id", nullable = true)
    private Hospital hospital;
    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "id")
    private Disease disease;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCnic() {return cnic;}

    public void setCnic(String cnic) {this.cnic = cnic;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public String getBlood() {return blood;}

    public void setBlood(String blood) {this.blood = blood;}

    public String getChronicdisease() {return chronicdisease;}

    public void setChronicdisease(String chronicdisease) {this.chronicdisease = chronicdisease;}

    public Boolean getDiabetes() {return diabetes;}

    public void setDiabetes(Boolean diabetes) {this.diabetes = diabetes;}

    public Boolean getHighFever() {return highFever;}

    public void setHighFever(Boolean highFever) {this.highFever = highFever;}

    public Boolean getFever() {return fever;}

    public void setFever(Boolean fever) {this.fever = fever;}

    public Boolean getHypertension() {return hypertension;}

    public void setHypertension(Boolean hypertension) {this.hypertension = hypertension;}

    public Boolean getCardiac() {return cardiac;}

    public void setCardiac(Boolean cardiac) {this.cardiac = cardiac;}

    public Boolean getWeaknessPain() {return weaknessPain;}

    public void setWeaknessPain(Boolean weaknessPain) {this.weaknessPain = weaknessPain;}

    public Boolean getRespiratory() {return respiratory;}

    public void setRespiratory(Boolean respiratory) {this.respiratory = respiratory;}

    public Boolean getCancer() {return cancer;}

    public void setCancer(Boolean cancer) {this.cancer = cancer;}

    public Boolean getThyroid() {return thyroid;}

    public void setThyroid(Boolean thyroid) {this.thyroid = thyroid;}

    public Boolean getProstate() {return prostate;}

    public void setProstate(Boolean prostate) {this.prostate = prostate;}

    public Boolean getKidney() {return kidney;}

    public void setKidney(Boolean kidney) {this.kidney = kidney;}

    public Boolean getNeuro() {return neuro;}

    public void setNeuro(Boolean neuro) {this.neuro = neuro;}

    public Boolean getNausea() {return nausea;}

    public void setNausea(Boolean nausea) {this.nausea = nausea;}

    public Boolean getAsymptomatic() {return asymptomatic;}

    public void setAsymptomatic(Boolean asymptomatic) {this.asymptomatic = asymptomatic;}

    public Boolean getGastrointestinal() {return gastrointestinal;}

    public void setGastrointestinal(Boolean gastrointestinal) {this.gastrointestinal = gastrointestinal;}

    public String getOthers() {return others;}

    public void setOthers(String others) {this.others = others;}

    public Date getAdmissionDate() {return admissionDate;}

    public void setAdmissionDate(Date admissionDate) {this.admissionDate = admissionDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && age == patient.age && Objects.equals(name, patient.name) && Objects.equals(cnic, patient.cnic) && Objects.equals(gender, patient.gender) && Objects.equals(blood, patient.blood) && Objects.equals(chronicdisease, patient.chronicdisease) && Objects.equals(diabetes, patient.diabetes) && Objects.equals(highFever, patient.highFever) && Objects.equals(fever, patient.fever) && Objects.equals(hypertension, patient.hypertension) && Objects.equals(cardiac, patient.cardiac) && Objects.equals(weaknessPain, patient.weaknessPain) && Objects.equals(respiratory, patient.respiratory) && Objects.equals(cancer, patient.cancer) && Objects.equals(thyroid, patient.thyroid) && Objects.equals(prostate, patient.prostate) && Objects.equals(kidney, patient.kidney) && Objects.equals(neuro, patient.neuro) && Objects.equals(nausea, patient.nausea) && Objects.equals(asymptomatic, patient.asymptomatic) && Objects.equals(gastrointestinal, patient.gastrointestinal) && Objects.equals(others, patient.others) && Objects.equals(admissionDate, patient.admissionDate) && Objects.equals(hospital, patient.hospital) && Objects.equals(disease, patient.disease);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnic, gender, age, blood, chronicdisease, diabetes, highFever, fever, hypertension, cardiac, weaknessPain, respiratory, cancer, thyroid, prostate, kidney, neuro, nausea, asymptomatic, gastrointestinal, others, admissionDate, hospital, disease);
    }

    public Hospital getHospital() {return hospital;}

    public void setHospital(Hospital hospital) {this.hospital = hospital;}

    public Disease getDisease() {return disease;}

    public void setDisease(Disease disease) {this.disease = disease;}
}
