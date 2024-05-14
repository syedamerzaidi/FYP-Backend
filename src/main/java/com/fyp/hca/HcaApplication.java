package com.fyp.hca;
//import com.fyp.hca.repositories.DataInsertionService;
//import com.fyp.hca.repositories.DataInsertionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HcaApplication {

    /*@Autowired
    public HcaApplication(DataInsertionService dataInsertionService) {
        dataInsertionService.insertDummyData();
    }*/
    public static void main(String[] args) {
        SpringApplication.run(HcaApplication.class, args);
    }
}
