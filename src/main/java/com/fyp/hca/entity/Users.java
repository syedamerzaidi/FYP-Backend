package com.fyp.hca.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
public class Users extends BaseEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Getter
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Getter
    @Column(name = "usertype", nullable = false, length = 255)
    private String usertype;

    @Column(name = "contact", length = 255)
    private String contact;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Getter
    @OneToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

    @Getter
    @OneToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private Division division;

    @OneToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @OneToOne
    @JoinColumn(name = "tehsil_id", referencedColumnName = "id")
    private Tehsil tehsil;

    @OneToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    public Users() {
    }

    // Constructors, getters, and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(usertype, users.usertype) && Objects.equals(contact, users.contact) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(province, users.province) && Objects.equals(division, users.division) && Objects.equals(district, users.district) && Objects.equals(tehsil, users.tehsil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, usertype, contact, email, password, province, division, district, tehsil);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Tehsil getTehsil() {
        return tehsil;
    }

    public void setTehsil(Tehsil tehsil) {
        this.tehsil = tehsil;
    }

    public void setUserType(String userType) {
        this.usertype=userType;
    }

    public void setHospital(Hospital hospital) {
        this.hospital=hospital;
    }

    /*public byte[] getProfilePicture() {return profilePicture;}

    public void setProfilePicture(byte[] profilePicture) {this.profilePicture = profilePicture;}*/
}
