package com.roweatrow.server.models;

import java.sql.Timestamp;

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
