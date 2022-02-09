package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.AssignedWorkout;
import com.roweatrow.server.respository.AssignedWorkoutRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/assignedWorkouts")
public class AssignedWorkoutsController {
  private final AssignedWorkoutRepository assignedWorkoutRepository;

  public AssignedWorkoutsController(AssignedWorkoutRepository assignedWorkoutRepository) {
    this.assignedWorkoutRepository = assignedWorkoutRepository;
  }

  @GetMapping(value = "/{assignedWorkoutId}")
  public @ResponseBody AssignedWorkout getAssignedWorkout(@PathVariable Long assignedWorkoutId) {
    Optional<AssignedWorkout> assignedWorkout =
        assignedWorkoutRepository.findById(assignedWorkoutId);
    return assignedWorkout.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody AssignedWorkout createAssignedWorkout(
      @RequestBody AssignedWorkout requestBody) {
    if (requestBody.getAssignedWorkout() != null) {
      Optional<AssignedWorkout> assignedWorkout =
          assignedWorkoutRepository.findById(requestBody.getAssignedWorkout());

      if (assignedWorkout.isPresent()) {
        return assignedWorkout.get();
      }
    }

    return assignedWorkoutRepository.save(requestBody);
  }
}
