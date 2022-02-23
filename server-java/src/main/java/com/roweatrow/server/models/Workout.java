package com.roweatrow.server.models;

import java.util.List;

public interface Workout<SplitType> {
  Long getWorkout();

  String getName();

  List<SplitType> getSplits();
}
