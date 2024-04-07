package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Province extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*,columnDefinition = "serial"*/,nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "province")
    private Users user;

    public Province() {
    }
    public Province(int id, String name) {
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
        Province province = (Province) o;
        return id == province.id && Objects.equals(name, province.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
