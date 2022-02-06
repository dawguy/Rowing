package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.CompetitionLevel;
import com.roweatrow.server.respository.CompetitionLevelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/competitionLevels")
public class CompetitionLevelsController {
  private final CompetitionLevelRepository competitionLevelRepository;

  public CRUDController(CompetitionLevelRepository competitionLevelRepository) {
    this.competitionLevelRepository = competitionLevelRepository;
  }

  @GetMapping(value = "/{competitionLevelId}")
  public @ResponseBody CompetitionLevel getCompetitionLevel(@PathVariable Long competitionLevelId) {
    Optional<CompetitionLevel> competitionLevel =
        competitionLevelRepository.findById(competitionLevelId);
    return competitionLevel.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody CompetitionLevel createCompetitionLevel(
      @RequestBody CompetitionLevel requestBody) {
    if (requestBody.getCompetitionLevel() != null) {
      Optional<CompetitionLevel> competitionLevel =
          competitionLevelRepository.findById(requestBody.getCompetitionLevel());

      if (competitionLevel.isPresent()) {
        return competitionLevel.get();
      }
    }

    return competitionLevelRepository.save(requestBody);
  }
}
