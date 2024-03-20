package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Division extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",/*columnDefinition = "serial",*/nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = true)
    private Province province;

    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "division")
    private Users user;

    public Division() {
    }

    public Division(int id, String name, Province province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

    public Division(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return id == division.id && name.equals(division.name) && province.equals(division.province);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, province);
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
