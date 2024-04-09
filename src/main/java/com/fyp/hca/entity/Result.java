package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
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
}
