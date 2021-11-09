package com.roweatrow.server.model;

import java.time.Duration;
import java.util.List;

public interface Workout {
    public List<? extends Split> getSplits();
}
