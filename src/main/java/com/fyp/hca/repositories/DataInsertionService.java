package com.fyp.hca.repositories;

import com.fyp.hca.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class DataInsertionService {

    @Autowired
    public DiseaseRepository diseaseRepository;

    @Autowired
    public DivisionRepository divisionRepository;

    @Autowired
    public DistrictRepository districtRepository;

    @Autowired
    public TehsilRepository tehsilRepository;

    @Autowired
    public HospitalRepository hospitalRepository;

    @Autowired
    public ProvinceRepository provinceRepository;

    @Autowired
    protected UsersRepository usersRepository;

    public void insertDummyData() {
        for (int i = 1; i <= 100; i++) {
            Disease disease = new Disease();
            disease.setName("Disease " + i);
            disease.setDescription("Description of disease " + i);
            disease.setCauses("Causes of disease " + i);
            disease.setSymptoms("Symptoms of disease " + i);
            diseaseRepository.save(disease);
        }

        String[] provinceNames = {"Punjab", "Sindh", "Khyber Pakhtunkhwa", "Balochistan", "Gilgit-Baltistan", "Azad Jammu and Kashmir"};

        for (String provinceName : provinceNames) {
            Province province = new Province();
            province.setName(provinceName);
            provinceRepository.save(province);

            for (int i = 1; i <= 30; i++) {
                Division division = new Division();
                division.setProvince(province);
                division.setName("Division " + i + " of " + provinceName);
                divisionRepository.save(division);

                for (int j = 1; j <= 10; j++) {
                    District district = new District();
                    district.setDivision(division);
                    district.setName("District " + ((i - 1) * 10 + j) + " of Division " + i + " of " + provinceName);
                    districtRepository.save(district);

                    for (int k = 1; k <= 10; k++) {
                        Tehsil tehsil = new Tehsil();
                        tehsil.setDistrict(district);
                        tehsil.setName("Tehsil " + ((i - 1) * 100 + (j - 1) * 10 + k) + " of District " + ((i - 1) * 10 + j) + " of Division " + i + " of " + provinceName);
                        tehsilRepository.save(tehsil);
                    }
                }
            }
        }

        // Insert dummy data for hospitals
        for (int i = 1; i <= 1000; i++) {
            Hospital hospital = new Hospital();
            hospital.setTehsil(tehsilRepository.findById((i % 100) + 1).orElse(null)); // Assign hospitals in round-robin fashion to tehsils
            hospital.setCode("Code " + i);
            hospital.setAddress("Address " + i);
            hospital.setHospitalType("Type " + i);
            hospital.setName("Hospital " + i);
            hospitalRepository.save(hospital);
            System.out.println("Hospital inserted: " + hospital.getName());
        }

        // Insert dummy data for users

// Create sets to store used foreign key ids
        Set<Integer> hospitalIds = new HashSet<>();
        Set<Integer> tehsilIds = new HashSet<>();
        Set<Integer> districtIds = new HashSet<>();
        Set<Integer> divisionIds = new HashSet<>();
        Set<Integer> provinceIds = new HashSet<>();
        String[] USER_TYPES = {"Super Administrator", "Province Administrator", "Division Administrator", "District Administrator", "Tehsil Administrator", "Hospital Administrator"};

        for (int i = 1; i <= 1000; i++) {
            Users user = new Users();
            user.setFirstName("First Name " + i);
            user.setLastName("Last Name " + i);
            user.setEmail("email" + i + "@example.com");
            user.setPassword("password" + i);
            user.setContact("123456789" + i);
            user.setCnic("12345-6-789" + i);
            int randomUserTypeIndex = (int) (Math.random() * USER_TYPES.length);
            user.setUsertype(USER_TYPES[randomUserTypeIndex]);

            // Check and assign unique foreign keys
            switch(randomUserTypeIndex) {
                case 5:
                    Hospital randomHospital;
                    do {
                        randomHospital = hospitalRepository.findById((int) (Math.random() * 1000 + 1)).orElse(null);
                    } while (!hospitalIds.add(randomHospital.getId()));
                    user.setHospital(randomHospital);
                    break;
                case 4:
                    Tehsil randomTehsil;
                    do {
                        randomTehsil = tehsilRepository.findById((int) (Math.random() * 1000 + 1)).orElse(null);
                    } while (!tehsilIds.add(randomTehsil.getId()));
                    user.setTehsil(randomTehsil);
                    break;
                case 3:
                    District randomDistrict;
                    do {
                        randomDistrict = districtRepository.findById((int) (Math.random() * 300 + 1)).orElse(null);
                    } while (!districtIds.add(randomDistrict.getId()));
                    user.setDistrict(randomDistrict);
                    break;
                case 2:
                    Division randomDivision;
                    do {
                        randomDivision = divisionRepository.findById((int) (Math.random() * 30 + 1)).orElse(null);
                    } while (!divisionIds.add(randomDivision.getId()));
                    user.setDivision(randomDivision);
                    break;
                case 1:
                    Province randomProvince;
                    do {
                        randomProvince = provinceRepository.findById((int) (Math.random() * 6 + 1)).orElse(null);
                    } while (!provinceIds.add(randomProvince.getId()));
                    user.setProvince(randomProvince);
                    break;
                case 0:
                    break;
            }

            System.out.println("User inserted: " + user.getFirstName());
            usersRepository.save(user);
        }

    }
}
