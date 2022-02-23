package com.roweatrow.server.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class AddSplit {
  @NotNull Long workoutId;
  @NotNull Long athleteId;
  Timestamp duration;
  Long distance;
  Long heartRate;
  Long power;

  // Water specific, not needed.
  Boolean withFlow;
  Long flowRate;
}
