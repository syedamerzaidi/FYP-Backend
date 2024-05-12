package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class District extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*,columnDefinition = "serial"*/,nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id", nullable = true)
    private Division division;

    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "district")
    private Users user;

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
}
