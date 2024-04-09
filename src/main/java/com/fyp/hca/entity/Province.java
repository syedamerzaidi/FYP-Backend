package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
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
