package com.roweatrow.server.respository;

import com.roweatrow.server.models.AssignedWorkout;
import com.roweatrow.server.models.TemplateSplit;
import org.springframework.data.repository.CrudRepository;

public interface TemplateSplitRepository extends CrudRepository<TemplateSplit, Long> {}
