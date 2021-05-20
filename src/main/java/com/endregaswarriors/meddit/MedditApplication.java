package com.endregaswarriors.meddit;


import com.endregaswarriors.meddit.repositories.external.MovieRepository;
import com.endregaswarriors.meddit.repositories.external.MovieRepositoryImpl;
import com.endregaswarriors.meddit.repositories.external.TMDBContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedditApplication.class, args);

        MovieRepository movieRepository = new MovieRepositoryImpl(new TMDBContext());

    }


}
