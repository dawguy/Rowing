package com.roweatrow.server.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AddWaterWorkoutAthleteSplit {
  @NotNull Long splitId;
  @NotNull Long workoutId;
  @NotNull Long athleteId;
  Long heartRate;
  Long power;
}
