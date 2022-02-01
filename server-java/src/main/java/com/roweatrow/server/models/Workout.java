package com.roweatrow.server.models;

import java.util.List;

public interface Workout {
    public List<? extends Split> getSplits();
}
