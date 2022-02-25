package com.roweatrow.server.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class WaterAssignmentDTO {
  @NotNull Long assignedWorkoutId;
  @NotNull Long boatId;
  @NotNull Date date;
}
