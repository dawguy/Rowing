package com.roweatrow.server.model;

import java.sql.Timestamp;
import java.time.Duration;

public interface Split {
    public Timestamp getDuration();
    default public Integer getPower() {
        return null;
    }
    default public Integer getHeartRate() {
        return null;
    }
    public int getDistance();
}
