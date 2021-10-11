package com.roweatrow.server;

import com.roweatrow.server.model.Athlete;
import com.roweatrow.server.respository.AthleteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerApplication {

    private static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner aaa(AthleteRepository athleteRepository){
        return (args) -> {
            log.info("Find all");
            for(Athlete a : athleteRepository.findAll()){
                log.info(a.toString());
            }
            log.info("Find David");
            log.info("{}", athleteRepository.findById(1L));
            log.info("Find by name Test");
            for(Athlete a : athleteRepository.findAthleteByName("Test")){
                log.info(a.toString());
            }
        };
    }
}
