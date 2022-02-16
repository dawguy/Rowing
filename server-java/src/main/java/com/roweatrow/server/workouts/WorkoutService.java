package com.roweatrow.server.workouts;

import com.roweatrow.server.models.Boat;
import com.roweatrow.server.models.Split;
import com.roweatrow.server.models.Workout;
import com.roweatrow.server.respository.BoatRepository;
import com.roweatrow.server.respository.ErgWorkoutRepository;
import com.roweatrow.server.respository.WaterWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WorkoutService {
  private final ErgWorkoutRepository ergWorkoutRepository;
  private final WaterWorkoutRepository waterWorkoutRepository;
  private final BoatRepository boatRepository;

  @Autowired
  public WorkoutService(
      ErgWorkoutRepository ergWorkoutRepository,
      WaterWorkoutRepository waterWorkoutRepository,
      BoatRepository boatRepository) {
    this.ergWorkoutRepository = ergWorkoutRepository;
    this.waterWorkoutRepository = waterWorkoutRepository;
    this.boatRepository = boatRepository;
  }

  public List<? extends Workout> getWorkoutsByAthlete(long athleteId) {
    return Stream.concat(
            getErgWorkoutsByAthlete(athleteId).stream(),
            getWaterWorkoutsByAthlete(athleteId).stream())
        .collect(Collectors.toList());
  }

  public List<? extends Workout> getErgWorkoutsByAthlete(long athleteId) {
    return ergWorkoutRepository.findErgWorkoutsByAthlete(athleteId);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public List<? extends Workout> getWaterWorkoutsByAthlete(long athleteId) {
    List<Boat> boats = getBoatsByAthlete(athleteId);
    List<Long> boatsL = boats.stream().map(Boat::getBoat).collect(Collectors.toList());
    return waterWorkoutRepository.findWaterWorkoutsByBoatIn(boatsL);
  }

  public List<? extends Workout> getWorkoutsByBoat(long boatId) {
    return waterWorkoutRepository.findWaterWorkoutByBoat(boatId);
  }

  public List<Boat> getBoatsByAthlete(long athleteId) {
    return boatRepository.retrieveAllByAthleteId(athleteId);
  }
}
