package com.roweatrow.server.respository;

import com.roweatrow.server.models.ErgWorkout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ErgWorkoutRepository extends CrudRepository<ErgWorkout, Long> {
    List<ErgWorkout> findErgWorkoutsByAthlete(long athleteId);
}
