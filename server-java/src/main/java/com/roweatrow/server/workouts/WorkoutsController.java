package com.roweatrow.server.workouts;

import com.roweatrow.server.model.ErgWorkout;
import com.roweatrow.server.model.WaterWorkout;
import com.roweatrow.server.model.Workout;
import com.roweatrow.server.respository.ErgWorkoutRepository;
import com.roweatrow.server.respository.WaterWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/workouts")
public class WorkoutsController {
    private final WaterWorkoutRepository waterWorkoutRepository;
    private final ErgWorkoutRepository ergWorkoutRepository;
    private final WorkoutService workoutService;

    public WorkoutsController(
            WaterWorkoutRepository waterWorkoutRepository,
            ErgWorkoutRepository ergWorkoutRepository,
            WorkoutService workoutService
    ) {
        this.waterWorkoutRepository = waterWorkoutRepository;
        this.ergWorkoutRepository = ergWorkoutRepository;
        this.workoutService = workoutService;
    }

    @GetMapping(value = "/water/{workoutId}")
    public @ResponseBody Workout getWaterWorkout(@PathVariable Long workoutId){
        Optional<WaterWorkout> workout = waterWorkoutRepository.findById(workoutId);
        return workout.orElse(null);
    }

    @GetMapping(value = "/erg/{workoutId}")
    public @ResponseBody Workout  getErgWorkout(@PathVariable Long workoutId){
        Optional<ErgWorkout> workout = ergWorkoutRepository.findById(workoutId);
        return workout.orElse(null);
    }

    @GetMapping(value = "/athlete/{athleteId}")
    public @ResponseBody List<? extends Workout> getAtheleteWorkouts(@PathVariable Long athleteId){
        return workoutService.getWorkoutsByAthlete(athleteId);
    }

}
