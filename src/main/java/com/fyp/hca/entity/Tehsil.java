package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Tehsil extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*,columnDefinition = "serial"*/,nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER,optional = true)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = true)
    private District district;

    public Tehsil() {
    }

    public Tehsil(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tehsil tehsil = (Tehsil) o;
        return id == tehsil.id && name.equals(tehsil.name) && district.equals(tehsil.district);
    }

    @Override
    public int hashCode() {return Objects.hash(id, name, district);}

    public District getDistrict() {return district;}

    public void setDistrict(District district) {this.district = district;}
}
