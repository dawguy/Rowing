package com.roweatrow.server.models;

import java.sql.Timestamp;

public interface Split {
    public Timestamp getDuration();
    default public Long getPower() {
        return null;
    }
    default public Long getHeartRate() {
        return null;
    }
    public Long getDistance();
}
