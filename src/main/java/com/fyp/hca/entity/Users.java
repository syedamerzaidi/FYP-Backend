package com.fyp.hca.entity;

import jakarta.persistence.*;

import java.util.Objects;

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

    @OneToOne(cascade = CascadeType.ALL, optional = true)
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


    public Users() {
    }

    public Users(int id, String firstName, String lastName, String usertype, String contact, String cnic, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.usertype = usertype;
        this.contact = contact;
        this.cnic = cnic;
        this.email = email;
        this.password = password;
    }



    public Users(int id, String firstName, String lastName, String usertype, String contact, String cnic, String email, String password, Province province, Division division, District district, Tehsil tehsil, Hospital hospital) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.usertype = usertype;
        this.contact = contact;
        this.cnic = cnic;
        this.email = email;
        this.password = password;
        this.province = province;
        this.division = division;
        this.district = district;
        this.tehsil = tehsil;
        this.hospital = hospital;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsertype() {
        return usertype;
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

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Division getDivision() {
        return division;
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
