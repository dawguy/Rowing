package com.roweatrow.server.respository;

import com.roweatrow.server.models.Boat;
import com.roweatrow.server.models.WaterSplit;
import com.roweatrow.server.models.WaterWorkout;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface WaterWorkoutRepository extends CrudRepository<WaterWorkout, Long> {
  List<WaterWorkout> findWaterWorkoutsByBoatIn(Collection<Long> boats);

  List<WaterWorkout> findWaterWorkoutByBoat(long boat);

  @Query("select w from WaterWorkout w join WaterSplit ws ON w = ws.waterWorkout where ws = ?1")
  List<WaterWorkout> findWaterWorkoutsByWaterSplits(WaterSplit waterSplit);
}
