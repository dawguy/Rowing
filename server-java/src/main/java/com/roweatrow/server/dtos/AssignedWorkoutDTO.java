package com.roweatrow.server.dtos;

import com.roweatrow.server.models.TemplateWorkout;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignedWorkoutDTO {
  private Long assignedWorkout;
  private java.sql.Date date;
  private String name;
  private Long team;
  private Long templateWorkout;
}
