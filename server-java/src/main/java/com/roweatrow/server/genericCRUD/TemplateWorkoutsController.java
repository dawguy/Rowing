package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.TemplateWorkout;
import com.roweatrow.server.respository.TemplateWorkoutRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/templateWorkouts")
public class TemplateWorkoutsController {
  private final TemplateWorkoutRepository templateWorkoutRepository;

  public TemplateWorkoutsController(TemplateWorkoutRepository templateWorkoutRepository) {
    this.templateWorkoutRepository = templateWorkoutRepository;
  }

  @GetMapping(value = "/{templateWorkoutId}")
  public @ResponseBody TemplateWorkout getTemplateWorkout(@PathVariable Long templateWorkoutId) {
    Optional<TemplateWorkout> templateWorkout =
        templateWorkoutRepository.findById(templateWorkoutId);
    return templateWorkout.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody TemplateWorkout createTemplateWorkout(
      @RequestBody TemplateWorkout requestBody) {
    if (requestBody.getTemplateWorkout() != null) {
      Optional<TemplateWorkout> templateWorkout =
          templateWorkoutRepository.findById(requestBody.getTemplateWorkout());

      if (templateWorkout.isPresent()) {
        return templateWorkout.get();
      }
    }

    return templateWorkoutRepository.save(requestBody);
  }
}
