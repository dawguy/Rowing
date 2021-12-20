package com.roweatrow.server.respository;

import com.roweatrow.server.model.Boat;
import com.roweatrow.server.model.WaterWorkout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WaterWorkoutRepository extends CrudRepository<WaterWorkout, Long> {
    public List<WaterWorkout> findWaterWorkoutByBoatIn(Collection<Boat> boats);
    public List<WaterWorkout> findWaterWorkoutByBoat(long boat);
}
