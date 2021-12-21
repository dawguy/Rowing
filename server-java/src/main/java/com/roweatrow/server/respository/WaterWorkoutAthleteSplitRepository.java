package com.roweatrow.server.respository;

import com.roweatrow.server.model.WaterSplit;
import com.roweatrow.server.model.WaterWorkoutAthleteSplit;
import org.springframework.data.repository.CrudRepository;

public interface WaterWorkoutAthleteSplitRepository extends CrudRepository<WaterWorkoutAthleteSplit, Long> {
}
