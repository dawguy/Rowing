package com.roweatrow.server.workouts;

import com.roweatrow.server.dtos.AddSplit;
import com.roweatrow.server.models.*;
import com.roweatrow.server.respository.ErgWorkoutRepository;
import com.roweatrow.server.respository.WaterWorkoutAthleteSplitRepository;
import com.roweatrow.server.respository.WaterWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/workouts")
public class WorkoutsController {
  private final WaterWorkoutRepository waterWorkoutRepository;
  private final ErgWorkoutRepository ergWorkoutRepository;
  private final WorkoutService workoutService;
  private final WorkoutSplitsService workoutSplitsService;
  private final WaterWorkoutAthleteSplitRepository waterWorkoutAthleteSplitRepository;

  public WorkoutsController(
      WaterWorkoutRepository waterWorkoutRepository,
      ErgWorkoutRepository ergWorkoutRepository,
      WorkoutService workoutService,
      WorkoutSplitsService workoutSplitsService,
      WaterWorkoutAthleteSplitRepository waterWorkoutAthleteSplitRepository) {
    this.waterWorkoutRepository = waterWorkoutRepository;
    this.ergWorkoutRepository = ergWorkoutRepository;
    this.workoutService = workoutService;
    this.workoutSplitsService = workoutSplitsService;
    this.waterWorkoutAthleteSplitRepository = waterWorkoutAthleteSplitRepository;
  }

  @GetMapping(value = "/water/{workoutId}")
  public @ResponseBody Workout getWaterWorkout(@PathVariable Long workoutId) {
    Optional<WaterWorkout> workout = waterWorkoutRepository.findById(workoutId);
    return workout.orElse(null);
  }

  @GetMapping(value = "/erg/{workoutId}")
  public @ResponseBody Workout getErgWorkout(@PathVariable Long workoutId) {
    Optional<ErgWorkout> workout = ergWorkoutRepository.findById(workoutId);
    return workout.orElse(null);
  }

  @GetMapping(value = "/athlete/{athleteId}")
  public @ResponseBody List<? extends Workout> getAtheleteWorkouts(@PathVariable Long athleteId) {
    return workoutService.getWorkoutsByAthlete(athleteId);
  }

  @GetMapping(value = "/boat/{boatId}")
  public @ResponseBody List<? extends Workout> getBoatWorkouts(@PathVariable Long boatId) {
    return workoutService.getWorkoutsByBoat(boatId);
  }

  @PostMapping(value = "/water/addSplit")
  public @ResponseBody WaterWorkout addWaterSplit(@RequestBody AddSplit addSplit)
      throws NoSuchFieldException {
    Optional<WaterWorkout> oWorkout = waterWorkoutRepository.findById(addSplit.getWorkoutId());

    if (oWorkout.isEmpty()) {
      return null;
    }

    WaterWorkout w = oWorkout.get();

    WaterWorkoutAthleteSplit waterWorkoutAthleteSplit =
        WaterWorkoutAthleteSplit.builder()
            .athlete(addSplit.getAthleteId())
            .heartRate(addSplit.getHeartRate())
            .power(addSplit.getPower())
            .build();

    WaterSplit noAthleteWaterSplit =
        WaterSplit.builder()
            .distance(addSplit.getDistance())
            .duration(addSplit.getDuration())
            .flowRate(addSplit.getFlowRate())
            .withFlow(addSplit.getWithFlow())
            .waterWorkoutAthleteSplit(List.of(waterWorkoutAthleteSplit))
            .build();
    workoutSplitsService.addSplit(w, noAthleteWaterSplit);
    return waterWorkoutRepository.findById(addSplit.getWorkoutId()).orElse(null);
  }
}
