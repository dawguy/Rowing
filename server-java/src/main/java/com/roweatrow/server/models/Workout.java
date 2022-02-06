package com.roweatrow.server.models;

import java.util.List;

public interface Workout {
  List<? extends Split> getSplits();
}
