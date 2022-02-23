package com.roweatrow.server.respository;

import com.roweatrow.server.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class SplitRepository {
  private static final Logger log = LoggerFactory.getLogger(SplitRepository.class);

  private final ErgSplitRepository ergSplitRepository;
  private final WaterSplitRepository waterSplitRepository;
  private final TemplateSplitRepository templateSplitRepository;

  public SplitRepository(
      ErgSplitRepository ergSplitRepository,
      WaterSplitRepository waterSplitRepository,
      TemplateSplitRepository templateSplitRepository) {
    this.ergSplitRepository = ergSplitRepository;
    this.waterSplitRepository = waterSplitRepository;
    this.templateSplitRepository = templateSplitRepository;
  }

  public void save(Split w) {
    if (w instanceof ErgSplit) {
      ergSplitRepository.save((ErgSplit) w);
    } else if (w instanceof WaterSplit) {
      waterSplitRepository.save((WaterSplit) w);
    } else if (w instanceof TemplateSplit) {
      templateSplitRepository.save((TemplateSplit) w);
    } else {
      log.error("Invalid split save called.");
    }
  }
}
