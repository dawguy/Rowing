package com.roweatrow.server.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ErgAssignmentDTO {
  @NotNull Long assignedWorkoutId;
  @NotNull Long athleteId;
  @NotNull Date date;
}
