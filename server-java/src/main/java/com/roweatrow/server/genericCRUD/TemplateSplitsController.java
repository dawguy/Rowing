package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.TemplateSplit;
import com.roweatrow.server.respository.TemplateSplitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/templateSplits")
public class TemplateSplitsController {
  private final TemplateSplitRepository templateSplitRepository;

  public TemplateSplitsController(TemplateSplitRepository templateSplitRepository) {
    this.templateSplitRepository = templateSplitRepository;
  }

  @GetMapping(value = "/{templateSplitId}")
  public @ResponseBody TemplateSplit getTemplateSplit(@PathVariable Long templateSplitId) {
    Optional<TemplateSplit> templateSplit = templateSplitRepository.findById(templateSplitId);
    return templateSplit.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody TemplateSplit createTemplateSplit(@RequestBody TemplateSplit requestBody) {
    if (requestBody.getTemplateSplit() != null) {
      Optional<TemplateSplit> templateSplit =
          templateSplitRepository.findById(requestBody.getTemplateSplit());

      if (templateSplit.isPresent()) {
        return templateSplit.get();
      }
    }

    return templateSplitRepository.save(requestBody);
  }
}
