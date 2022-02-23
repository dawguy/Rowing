package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.dtos.AssignedWorkoutDTO;
import com.roweatrow.server.models.AssignedWorkout;
import com.roweatrow.server.models.TemplateWorkout;
import com.roweatrow.server.respository.AssignedWorkoutRepository;
import com.roweatrow.server.respository.TemplateWorkoutRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/assignedWorkouts")
public class AssignedWorkoutsController {
  private final AssignedWorkoutRepository assignedWorkoutRepository;
  private final TemplateWorkoutRepository templateWorkoutRepository;

  public AssignedWorkoutsController(
      AssignedWorkoutRepository assignedWorkoutRepository,
      TemplateWorkoutRepository templateWorkoutRepository) {
    this.assignedWorkoutRepository = assignedWorkoutRepository;
    this.templateWorkoutRepository = templateWorkoutRepository;
  }

  @GetMapping(value = "/{assignedWorkoutId}")
  public @ResponseBody AssignedWorkout getAssignedWorkout(@PathVariable Long assignedWorkoutId) {
    Optional<AssignedWorkout> assignedWorkout =
        assignedWorkoutRepository.findById(assignedWorkoutId);
    return assignedWorkout.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody AssignedWorkout createAssignedWorkout(
      @RequestBody AssignedWorkoutDTO requestBody) {
    if (requestBody.getAssignedWorkout() != null) {
      Optional<AssignedWorkout> assignedWorkoutFromDB =
          assignedWorkoutRepository.findById(requestBody.getAssignedWorkout());

      if (assignedWorkoutFromDB.isPresent()) {
        return assignedWorkoutFromDB.get();
      }
    }

    Optional<TemplateWorkout> optionalTemplateWorkout =
        templateWorkoutRepository.findById(requestBody.getTemplateWorkout());
    if (optionalTemplateWorkout.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid template workout");
    }

    AssignedWorkout assignedWorkout =
        AssignedWorkout.builder()
            .name(requestBody.getName())
            .date(requestBody.getDate())
            .templateWorkout(optionalTemplateWorkout.get())
            .team(requestBody.getTeam())
            .build();

    return assignedWorkoutRepository.save(assignedWorkout);
  }
}
