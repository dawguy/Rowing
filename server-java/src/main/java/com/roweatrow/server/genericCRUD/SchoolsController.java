package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.Team;
import com.roweatrow.server.respository.TeamRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/teams")
public class TeamsController {
  private final TeamRepository teamRepository;

  public TeamsController(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @GetMapping(value = "/{teamId}")
  public @ResponseBody Team getTeam(@PathVariable Long teamId) {
    Optional<Team> team = teamRepository.findById(teamId);
    return team.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody Team createTeam(@RequestBody Team requestBody) {
    if (requestBody.getTeam() != null) {
      Optional<Team> team = teamRepository.findById(requestBody.getTeam());

      if (team.isPresent()) {
        return team.get();
      }
    }

    return teamRepository.save(requestBody);
  }
}
