package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.Shell;
import com.roweatrow.server.respository.ShellRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/shells")
public class ShellsController {
  private final ShellRepository shellRepository;

  public CRUDController(ShellRepository shellRepository) {
    this.shellRepository = shellRepository;
  }

  @GetMapping(value = "/{shellId}")
  public @ResponseBody Shell getShell(@PathVariable Long shellId) {
    Optional<Shell> shell = shellRepository.findById(shellId);
    return shell.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody Shell createShell(@RequestBody Shell requestBody) {
    if (requestBody.getShell() != null) {
      Optional<Shell> shell = shellRepository.findById(requestBody.getShell());

      if (shell.isPresent()) {
        return shell.get();
      }
    }

    return shellRepository.save(requestBody);
  }
}
