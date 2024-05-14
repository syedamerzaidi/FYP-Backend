package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class District extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*,columnDefinition = "serial"*/,nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER,optional = true)
    @JoinColumn(name = "division_id", referencedColumnName = "id", nullable = true)
    private Division division;


    public District(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public District() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return id == district.id && name.equals(district.name) && division.equals(district.division);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, division);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
