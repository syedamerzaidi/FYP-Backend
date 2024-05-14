package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Result extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial",nullable = true)
    private int id;
    @Column(name = "number_of_upcoming_patients", nullable = false)
    private int numberOfUpcomingPatients;
    @Column(name = "date", nullable = true)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id", nullable = false)
    private Hospital hospital;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return id == result.id && numberOfUpcomingPatients == result.numberOfUpcomingPatients && Objects.equals(date, result.date) && Objects.equals(hospital, result.hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfUpcomingPatients, date, hospital);
    }

    public Hospital getHospital() {return hospital;}

    public void setHospital(Hospital hospital) {this.hospital = hospital;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfUpcomingPatients() {
        return numberOfUpcomingPatients;
    }

    public void setNumberOfUpcomingPatients(int numberOfUpcomingPatients) {
        this.numberOfUpcomingPatients = numberOfUpcomingPatients;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
