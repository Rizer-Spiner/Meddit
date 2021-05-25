package com.endregaswarriors.meddit;


import com.endregaswarriors.meddit.repositories.internal.ThreadRepository;
import com.endregaswarriors.meddit.services.ThreadService;
import com.endregaswarriors.meddit.services.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedditApplication.class, args);

    }


}
