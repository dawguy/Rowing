package com.roweatrow.server.respository;

import com.roweatrow.server.model.WaterWorkout;
import org.springframework.data.repository.CrudRepository;

public interface WaterWorkoutRepository extends CrudRepository<WaterWorkout, Long> {
}
