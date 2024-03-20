package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
public class Hospital extends BaseEntity{
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id"/*,columnDefinition = "serial"*/,nullable = false)
    private int id;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "code", nullable = false, length = 255)
    private String code;
    @Column(name = "address", nullable = false, length = 255)
    private String address;
    @Column(name = "hospital_type", nullable = false, length = 255)
    private String hospitalType;

    @ManyToOne
    @JoinColumn(name = "tehsil_id", referencedColumnName = "id", nullable = true)
    private Tehsil tehsil;
    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "hospital")
    private Users user;

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getHospitalType() {return hospitalType;}

    public void setHospitalType(String hospitalType) {this.hospitalType = hospitalType;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return id == hospital.id && name.equals(hospital.name) && code.equals(hospital.code) && address.equals(hospital.address) && hospitalType.equals(hospital.hospitalType) && tehsil.equals(hospital.tehsil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, address, hospitalType, tehsil);
    }

    public Tehsil getTehsil() {return tehsil;}

    public void setTehsil(Tehsil tehsil) {this.tehsil = tehsil;}
}
