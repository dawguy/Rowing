package com.roweatrow.server.models;

import java.sql.Timestamp;

public interface Split {
  Timestamp getDuration();

  default Long getPower() {
    return null;
  }

  default Long getHeartRate() {
    return null;
  }

  Long getDistance();
}
