package com.roweatrow.server.workouts;

import com.roweatrow.server.model.Boat;
import com.roweatrow.server.model.Split;
import com.roweatrow.server.model.Workout;
import com.roweatrow.server.respository.BoatRepository;
import com.roweatrow.server.respository.ErgWorkoutRepository;
import com.roweatrow.server.respository.WaterWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WorkoutService {
    private final ErgWorkoutRepository ergWorkoutRepository;
    private final WaterWorkoutRepository waterWorkoutRepository;
    private final BoatRepository boatRepository;

    @Autowired
    public WorkoutService(ErgWorkoutRepository ergWorkoutRepository, WaterWorkoutRepository waterWorkoutRepository, BoatRepository boatRepository){
        this.ergWorkoutRepository = ergWorkoutRepository;
        this.waterWorkoutRepository = waterWorkoutRepository;
        this.boatRepository = boatRepository;
    }

    public List<? extends Workout> getWorkoutsByAthlete(long athleteId){
        return Stream.concat(getErgWorkoutsByAthlete(athleteId).stream(), getWaterWorkoutsByAthlete(athleteId).stream())
                .collect(Collectors.toList());
    }

    public List<? extends Workout> getErgWorkoutsByAthlete(long athleteId){
        return ergWorkoutRepository.findErgWorkoutsByAthleteAthlete(athleteId);
    }

    public List<? extends Workout> getWaterWorkoutsByAthlete(long athleteId){
        List<Boat> boats = getBoatsByAthlete(athleteId);
        List<? extends Workout> workouts = waterWorkoutRepository.findWaterWorkoutByBoatIn(boats);
        List<List<? extends Split>> splits = workouts.stream().map(Workout::getSplits).collect(Collectors.toList());
        return workouts;
    }

    public List<? extends Workout> getWaterWorkoutsByBoat(long boatId){
        return waterWorkoutRepository.findWaterWorkoutByBoat(boatId);
    }

    public List<Boat> getBoatsByAthlete(long athleteId){
        return boatRepository.retrieveAllByAthleteId(athleteId);
    }


}
