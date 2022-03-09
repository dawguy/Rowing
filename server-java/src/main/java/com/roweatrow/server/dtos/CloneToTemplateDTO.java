package com.roweatrow.server.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloneToTemplateDTO {
  @NotNull Long workoutId;
  @NotNull Long team;
}
