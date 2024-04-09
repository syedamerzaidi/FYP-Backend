package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Disease extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial",nullable = true)
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Column(name = "symptoms", nullable = true, length = -1)
    private String symptoms;
    @Column(name = "causes", nullable = true, length = -1)
    private String causes;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return id == disease.id && Objects.equals(name, disease.name) && Objects.equals(description, disease.description) && Objects.equals(symptoms, disease.symptoms) && Objects.equals(causes, disease.causes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, symptoms, causes);
    }
}
