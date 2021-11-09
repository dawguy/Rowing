package com.roweatrow.server.workouts;

import com.roweatrow.server.respository.ErgWorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    private final ErgWorkoutRepository ergWorkoutRepository;

    public WorkoutService(ErgWorkoutRepository ergWorkoutRepository){
        this.ergWorkoutRepository = ergWorkoutRepository;
    }


}
