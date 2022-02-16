package com.roweatrow.server.respository;

import com.roweatrow.server.ServerApplication;
import com.roweatrow.server.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class WorkoutRepository {
    private static final Logger log = LoggerFactory.getLogger(WorkoutRepository.class);

    private final ErgWorkoutRepository ergWorkoutRepository;
    private final WaterWorkoutRepository waterWorkoutRepository;
    private final TemplateWorkoutRepository templateWorkoutRepository;
    private final AssignedWorkoutRepository assignedWorkoutRepository;

    public WorkoutRepository(
            ErgWorkoutRepository ergWorkoutRepository,
            WaterWorkoutRepository waterWorkoutRepository,
            TemplateWorkoutRepository templateWorkoutRepository,
            AssignedWorkoutRepository assignedWorkoutRepository
    ){
        this.ergWorkoutRepository = ergWorkoutRepository;
        this.waterWorkoutRepository = waterWorkoutRepository;
        this.templateWorkoutRepository = templateWorkoutRepository;
        this.assignedWorkoutRepository = assignedWorkoutRepository;
    }

    public <T extends Workout> T save(T w){
        if(w instanceof ErgWorkout){
            return (T) ergWorkoutRepository.save((ErgWorkout) w);
        } else if(w instanceof WaterWorkout){
            return (T) waterWorkoutRepository.save((WaterWorkout) w);
        } else if(w instanceof TemplateWorkout){
            return (T) templateWorkoutRepository.save((TemplateWorkout) w);
        } else if(w instanceof AssignedWorkout) {
            return (T) assignedWorkoutRepository.save((AssignedWorkout) w);
        } else {
            log.error("Invalid workout save called.");
        }

        return null;
    }
}
