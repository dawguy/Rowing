package com.roweatrow.server.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AddWaterSplit {
  @NotNull Long workoutId;
  @NotNull Long boatId;
  Timestamp duration;
  Long distance;

  // Water specific, not needed.
  Boolean withFlow;
  Long flowRate;
}
