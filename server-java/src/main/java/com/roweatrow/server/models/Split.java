package com.roweatrow.server.models;

import java.sql.Timestamp;

public interface Split<WorkoutType> {
  Timestamp getDuration();

  Long getDistance();

  default Long getPower() {
    return null;
  }

  default Long getHeartRate() {
    return null;
  }

  Long getSeq();

  void setSeq(Long seq);

  WorkoutType getWorkout();

  void setWorkout(WorkoutType workout);
}
