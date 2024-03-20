package com.fyp.hca.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String usertype;
    private String contact;
    private String email;
    private String password;
    private Integer province_id;
    private Integer division_id;
    private Integer district_id;
    private Integer tehsil_id;
    private Integer hospital_id;
}
