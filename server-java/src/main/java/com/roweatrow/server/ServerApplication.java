package com.roweatrow.server;

import com.roweatrow.server.model.Athlete;
import com.roweatrow.server.model.Boat;
import com.roweatrow.server.respository.AthleteRepository;
import com.roweatrow.server.respository.BoatRepository;
import com.roweatrow.server.workouts.WorkoutService;
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

//    public CommandLineRunner a(AthleteRepository athleteRepository){
//        return (args) -> {
//            log.info("Find all");
//            for(Athlete a : athleteRepository.findAll()){
//                log.info(a.toString());
//            }
//            log.info("Find David");
//            log.info("{}", athleteRepository.findById(1L));
//            log.info("Find by name Test");
//            for(Athlete a : athleteRepository.findAthleteByName("Test")){
//                log.info(a.toString());
//            }
//        };
//    }

//    @Bean
//    public CommandLineRunner b(BoatRepository boatRepository){
//        return (args) -> {
//            log.info("Find all");
//            for(Boat b : boatRepository.findAll()){
//                log.info(b.toString());
//            }
//            log.info("Find David");
//            log.info("{}", boatRepository.findById(1L));
//            log.info("Find by name Test");
//            for(Boat b : boatRepository.retrieveAll()){
//                log.info("{}", b);
//                log.info("{}", b.getCoxswain());
//                log.info("{}", b.getSeat_1());
//                log.info("{}", b.getSeat_8());
//            }
//        };
//    }

    @Bean
    public CommandLineRunner c(WorkoutService workoutService){
        return (args) -> {
            log.info("Find all");
            log.info("By athlete {}", workoutService.getWorkoutsByAthlete(1));
            log.info("Find David");
            log.info("{}", workoutService.getErgWorkoutsByAthlete(1));
            log.info("{}", workoutService.getWaterWorkoutsByAthlete(1));
        };
    }

}
