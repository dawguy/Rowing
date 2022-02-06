package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.Athlete;
import com.roweatrow.server.respository.AthleteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/athletes")
public class AthletesController {
  private final AthleteRepository athleteRepository;

  public AthletesController(AthleteRepository athleteRepository) {
    this.athleteRepository = athleteRepository;
  }

  @GetMapping(value = "/{athleteId}")
  public @ResponseBody Athlete getAthlete(@PathVariable Long athleteId) {
    Optional<Athlete> athlete = athleteRepository.findById(athleteId);
    return athlete.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody Athlete createAthlete(@RequestBody Athlete requestBody) {
    if (requestBody.getAthlete() != null) {
      Optional<Athlete> athlete = athleteRepository.findById(requestBody.getAthlete());

      if (athlete.isPresent()) {
        return athlete.get();
      }
    }

    return athleteRepository.save(requestBody);
  }
}
