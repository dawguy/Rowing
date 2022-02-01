package com.roweatrow.server.models;

import com.roweatrow.server.model.Split;

import java.util.List;

public interface Workout {
    public List<? extends Split> getSplits();
}
