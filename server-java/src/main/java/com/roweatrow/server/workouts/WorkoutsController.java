package com.roweatrow.server.workouts;

import com.roweatrow.server.dtos.*;
import com.roweatrow.server.models.*;
import com.roweatrow.server.respository.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/workouts")
public class WorkoutsController {
  private final WaterWorkoutRepository waterWorkoutRepository;
  private final ErgWorkoutRepository ergWorkoutRepository;
  private final TemplateWorkoutRepository templateWorkoutRepository;
  private final WaterSplitRepository waterSplitRepository;
  private final WorkoutService workoutService;
  private final WorkoutSplitsService workoutSplitsService;
  private final BoatRepository boatRepository;
  private final AssignedWorkoutRepository assignedWorkoutRepository;
  private static final Logger log = LoggerFactory.getLogger(WorkoutsController.class);

  public WorkoutsController(
      WaterWorkoutRepository waterWorkoutRepository,
      ErgWorkoutRepository ergWorkoutRepository,
      WorkoutService workoutService,
      WorkoutSplitsService workoutSplitsService,
      TemplateWorkoutRepository templateWorkoutRepository,
      WaterSplitRepository waterSplitRepository,
      AssignedWorkoutRepository assignedWorkoutRepository,
      BoatRepository boatRepository) {
    this.waterWorkoutRepository = waterWorkoutRepository;
    this.ergWorkoutRepository = ergWorkoutRepository;
    this.workoutService = workoutService;
    this.workoutSplitsService = workoutSplitsService;
    this.templateWorkoutRepository = templateWorkoutRepository;
    this.waterSplitRepository = waterSplitRepository;
    this.boatRepository = boatRepository;
    this.assignedWorkoutRepository = assignedWorkoutRepository;
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
  public @ResponseBody WaterWorkout addWaterSplit(@RequestBody AddWaterSplit addSplit) {
    Optional<WaterWorkout> oWorkout = waterWorkoutRepository.findById(addSplit.getWorkoutId());

    if (oWorkout.isEmpty()) {
      return null;
    }

    WaterWorkout w = oWorkout.get();
    WaterSplit noAthleteWaterSplit =
        WaterSplit.builder()
            .distance(addSplit.getDistance())
            .duration(addSplit.getDuration())
            .flowRate(addSplit.getFlowRate())
            .withFlow(addSplit.getWithFlow())
            .build();
    workoutSplitsService.addSplit(w, noAthleteWaterSplit);
    return waterWorkoutRepository.findById(addSplit.getWorkoutId()).orElse(null);
  }

  @PostMapping(value = "/water/split/addAthlete")
  public @ResponseBody WaterSplit addAthleteToWaterSplit(@RequestBody AddWaterWorkoutAthleteSplit addSplit) {
    Optional<WaterSplit> oSplit = waterSplitRepository.findById(addSplit.getSplitId());
    if (oSplit.isEmpty()) {
      return null;
    }
    WaterSplit split = oSplit.get();
    WaterWorkout waterWorkout= workoutService.getWaterWorkoutBySplit(split);
    List<WaterSplit> splits = waterWorkout.getSplits().stream().filter(s -> s.getWaterSplit() == addSplit.getSplitId()).collect(Collectors.toList());
    WaterSplit foundSplit = splits.get(0);

    WaterWorkoutAthleteSplit waterWorkoutAthleteSplit =
        WaterWorkoutAthleteSplit.builder()
            .waterSplit(foundSplit)
            .athlete(addSplit.getAthleteId())
            .heartRate(addSplit.getHeartRate())
            .power(addSplit.getPower())
            .build();
    workoutSplitsService.addAthleteToWaterSplit(foundSplit, waterWorkoutAthleteSplit);
    waterWorkoutRepository.save(waterWorkout);

    return foundSplit;
  }

  @PostMapping(value = "/erg/addSplit")
  public @ResponseBody ErgWorkout addErgSplit(@RequestBody AddSplit addSplit) {
    Optional<ErgWorkout> oWorkout = ergWorkoutRepository.findById(addSplit.getWorkoutId());

    if (oWorkout.isEmpty()) {
      return null;
    }

    ErgWorkout w = oWorkout.get();
    ErgSplit ergSplit =
        ErgSplit.builder()
            .distance(addSplit.getDistance())
            .duration(addSplit.getDuration())
            .heartRate(addSplit.getHeartRate())
            .power(addSplit.getPower())
            .build();
    workoutSplitsService.addSplit(w, ergSplit);
    return ergWorkoutRepository.findById(addSplit.getWorkoutId()).orElse(null);
  }

  @PostMapping(value = "/template/addSplit")
  public @ResponseBody TemplateWorkout addTemplateSplit(@RequestBody AddSplit addSplit) {
    Optional<TemplateWorkout> oWorkout =
        templateWorkoutRepository.findById(addSplit.getWorkoutId());

    if (oWorkout.isEmpty()) {
      return null;
    }

    TemplateWorkout w = oWorkout.get();
    TemplateSplit templateSplit =
        TemplateSplit.builder()
            .distance(addSplit.getDistance())
            .duration(addSplit.getDuration())
            .build();
    workoutSplitsService.addSplit(w, templateSplit);
    return templateWorkoutRepository.findById(addSplit.getWorkoutId()).orElse(null);
  }

  @PostMapping(value = "/assignment/toErgWorkout")
  public @ResponseBody ErgWorkout assignToErgWorkout(@RequestBody ErgAssignmentDTO ergAssignmentDTO) {
    Optional<AssignedWorkout> oWorkout = assignedWorkoutRepository.findById(ergAssignmentDTO.getAssignedWorkoutId());
    if (oWorkout.isEmpty()) {
      return null;
    }

    AssignedWorkout w = oWorkout.get();
    return workoutSplitsService.assignAsErgWorkout(w, ergAssignmentDTO.getAthleteId(), ergAssignmentDTO.getDate());
  }

  @PostMapping(value = "/assignment/toWaterWorkout")
  public @ResponseBody WaterWorkout assignToErgWorkout(@RequestBody WaterAssignmentDTO waterAssignmentDTO) {
    Optional<AssignedWorkout> oWorkout = assignedWorkoutRepository.findById(waterAssignmentDTO.getAssignedWorkoutId());
    Optional<Boat> oBoat = boatRepository.findById(waterAssignmentDTO.getBoatId());
    if (oWorkout.isEmpty() || oBoat.isEmpty()) {
      return null;
    }

    AssignedWorkout w = oWorkout.get();
    Boat b = oBoat.get();
    return workoutSplitsService.assignAsWaterWorkout(w, b, waterAssignmentDTO.getDate());
  }

  @PostMapping(value = "/clone/erg/toTemplateWorkout")
  public @ResponseBody TemplateWorkout cloneErgToTemplateWorkout(@RequestBody CloneToTemplateDTO cloneToTemplateDTO){
    Optional<ErgWorkout> oErgWorkout = ergWorkoutRepository.findById(cloneToTemplateDTO.getWorkoutId());

    if(oErgWorkout.isEmpty()) {
      return null;
    }
    ErgWorkout ergWorkout = oErgWorkout.get();

    return workoutSplitsService.createTemplateWorkoutFromSplits(ergWorkout, cloneToTemplateDTO.getTeam());
  }

  @PostMapping(value = "/clone/water/toTemplateWorkout")
  public @ResponseBody TemplateWorkout cloneWaterToTemplateWorkout(@RequestBody CloneToTemplateDTO cloneToTemplateDTO){
    Optional<WaterWorkout> oWaterWorkout = waterWorkoutRepository.findById(cloneToTemplateDTO.getWorkoutId());

    if(oWaterWorkout.isEmpty()) {
      return null;
    }
    WaterWorkout waterWorkout = oWaterWorkout.get();

    return workoutSplitsService.createTemplateWorkoutFromSplits(waterWorkout, cloneToTemplateDTO.getTeam());
  }
}
