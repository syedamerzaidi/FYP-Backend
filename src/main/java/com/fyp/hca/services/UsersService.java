package com.fyp.hca.services;

import com.fyp.hca.entity.Users;
import com.fyp.hca.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fyp.hca.dto.UsersDto;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DivisionService divisionService;
    @Autowired
    DistrictService districtService;
    @Autowired
    TehsilService tehsilService;
    @Autowired
    HospitalService hospitalService;

    public UsersService(UsersRepository usersRepository,ProvinceService provinceService, TehsilService tehsilService, DivisionService divisionService,
                        DistrictService districtService, HospitalService hospitalService) {
        this.usersRepository = usersRepository;
        this.provinceService=provinceService;
        this.tehsilService = tehsilService;
        this.divisionService = divisionService;
        this.districtService = districtService;
        this.hospitalService = hospitalService;
    }

    public void save(List<UsersDto> usersDto) {
        for (UsersDto user : usersDto) {
            usersRepository.save(convertDtoToEntity(user));
        }
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    public List<Users> getUsers() {
        return new ArrayList<Users>(usersRepository.findAll());
    }

    public Optional<Users> getUserById(Integer id) {
        return usersRepository.findById(id);
    }

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }

    public void updateUsers(Users users) {
        usersRepository.save(users);
    }

    public Optional<Users> isValidUser(String email, String password) {
        return usersRepository.findByEmailAndPassword(email,password);
    }

    public List<Map<String,Object>> getAllUsers() {
        return usersRepository.getAllUsers();
    }
    public Users convertDtoToEntity(UsersDto usersDto){
        Users user = new Users();
        if (usersDto.getId() != null) user.setId(usersDto.getId());
        user.setFirstName(usersDto.getFirstName());
        user.setLastName(usersDto.getLastName());
        user.setUsertype(usersDto.getUsertype());
        user.setContact(usersDto.getContact());
        user.setEmail(usersDto.getEmail());
        user.setPassword(usersDto.getPassword());
        user.setProvince(usersDto.getProvince_id() != null ? provinceService.getProvinceById(usersDto.getProvince_id()).orElse(null) : null);
        user.setDivision(usersDto.getDivision_id() != null ? divisionService.getDivisionById(usersDto.getDivision_id()).orElse(null) : null);
        user.setDistrict(usersDto.getDistrict_id() != null ? districtService.getDistrictById(usersDto.getDistrict_id()).orElse(null) : null);
        user.setTehsil(usersDto.getTehsil_id() != null ? tehsilService.getTehsilById(usersDto.getTehsil_id()).orElse(null) : null);
        user.setHospital(usersDto.getHospital_id() != null ? hospitalService.getHospitalById(usersDto.getHospital_id()).orElse(null) : null);
        return user;
    }
}
