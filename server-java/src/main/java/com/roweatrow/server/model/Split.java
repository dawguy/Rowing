package com.roweatrow.server.model;

import java.time.Duration;

public interface Split {
    public Duration getDuration();
    public int getPower();
    public int getHeartRate();
    public int getDistance();
}
