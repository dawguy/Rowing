package com.roweatrow.server.models;

import java.sql.Timestamp;

public interface Split {
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

  Long getWorkout();

  void setWorkout(Long workout);
}
