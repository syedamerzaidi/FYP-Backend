package com.fyp.hca;
import com.fyp.hca.repositories.DataInsertionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HcaApplication {

    private DataInsertionService dataInsertionService=new DataInsertionService();

    public HcaApplication(DataInsertionService dataInsertionService) {
        this.dataInsertionService = dataInsertionService;
       //dataInsertionService.insertDummyData();

    }
    public static void main(String[] args) {
        SpringApplication.run(HcaApplication.class, args);
    }
}
