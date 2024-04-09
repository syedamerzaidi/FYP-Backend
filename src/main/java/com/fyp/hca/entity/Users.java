package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial",nullable = true)
    private int id;
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;
    @Column(name = "usertype", nullable = false, length = 255)
    private String usertype;
    @Column(name = "contact", nullable = false, length = 255)
    private String contact;
    @Column(name = "cnic", nullable = false, length = 255)
    private String cnic;
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @OneToOne(optional = true)
    @JoinColumn(name = "province_id", referencedColumnName = "id", nullable = true, unique = false)
    private Province province;
    @OneToOne(optional = true)
    @JoinColumn(name = "division_id", referencedColumnName = "id", nullable = true, unique = false)
    private Division division;
    @OneToOne(optional = true)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = true, unique = false)
    private District district;
    @OneToOne(optional = true)
    @JoinColumn(name = "tehsil_id", referencedColumnName = "id",nullable = true, unique = false)
    private Tehsil tehsil;
    @OneToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id",nullable = true, unique = false)
    private Hospital hospital;

    // Constructors, getters, and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(usertype, users.usertype) && Objects.equals(contact, users.contact) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(province, users.province) && Objects.equals(division, users.division) && Objects.equals(district, users.district) && Objects.equals(tehsil, users.tehsil) && Objects.equals(hospital, users.hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, usertype, contact, email, password, province, division, district, tehsil, hospital);
    }

    /*public byte[] getProfilePicture() {return profilePicture;}

    public void setProfilePicture(byte[] profilePicture) {this.profilePicture = profilePicture;}*/
}
