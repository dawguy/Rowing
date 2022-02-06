package com.roweatrow.server.respository;

import com.roweatrow.server.models.Boat;
import com.roweatrow.server.models.WaterWorkout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WaterWorkoutRepository extends CrudRepository<WaterWorkout, Long> {
  List<WaterWorkout> findWaterWorkoutsByBoatIn(Collection<Long> boats);

  List<WaterWorkout> findWaterWorkoutByBoat(long boat);
}
