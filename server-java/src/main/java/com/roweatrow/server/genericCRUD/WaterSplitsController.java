package com.roweatrow.server.genericCRUD;

import com.roweatrow.server.models.WaterSplit;
import com.roweatrow.server.respository.WaterSplitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/waterSplits")
public class WaterSplitsController {
  private final WaterSplitRepository waterSplitRepository;

  public WaterSplitsController(WaterSplitRepository waterSplitRepository) {
    this.waterSplitRepository = waterSplitRepository;
  }

  @GetMapping(value = "/{waterSplitId}")
  public @ResponseBody WaterSplit getWaterSplit(@PathVariable Long waterSplitId) {
    Optional<WaterSplit> waterSplit = waterSplitRepository.findById(waterSplitId);
    return waterSplit.orElse(null);
  }

  @PostMapping(value = "")
  public @ResponseBody WaterSplit createWaterSplit(@RequestBody WaterSplit requestBody) {
    if (requestBody.getWaterSplit() != null) {
      Optional<WaterSplit> waterSplit = waterSplitRepository.findById(requestBody.getWaterSplit());

      if (waterSplit.isPresent()) {
        return waterSplit.get();
      }
    }

    return waterSplitRepository.save(requestBody);
  }
}
