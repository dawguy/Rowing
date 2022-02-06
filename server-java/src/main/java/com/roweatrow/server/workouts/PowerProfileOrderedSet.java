package com.roweatrow.server.workouts;

import java.util.*;

public class PowerProfileOrderedSet {
  int size = 0;

  // This set will always remain ordered by time
  private final NavigableSet<PowerProfileSegment> timeSet;

  // This set will always be ordered by power
  private final NavigableSet<PowerProfileSegment> powerSet;

  public PowerProfileOrderedSet() {
    timeSet = new TreeSet<>(new TimeCompare());
    powerSet = new TreeSet<>(new PowerCompare());
  }

  public void add(PowerProfileSegment pps) {
    timeSet.add(pps);
    powerSet.add(pps);
    size++;
  }

  public void addAll(Collection<PowerProfileSegment> collection) {
    for (PowerProfileSegment pps : collection) {
      add(pps);
    }
  }

  public void remove(PowerProfileSegment pps) {
    timeSet.remove(pps);
    powerSet.remove(pps);
    size--;
  }

  public PowerProfileSegment getMaxPower() {
    return powerSet.last();
  }

  public PowerProfileSegment getHigherPowerTimeNeighbor(PowerProfileSegment pps) {
    PowerProfileSegment prev = timeSet.lower(pps);
    PowerProfileSegment next = timeSet.higher(pps);

    if (prev == null) return next;
    if (next == null) return prev;

    // Return the higher value of next and prev
    return Double.compare(prev.getPower(), next.getPower()) <= 0 ? next : prev;
  }

  public int size() {
    return size;
  }

  private static class TimeCompare implements Comparator<PowerProfileSegment> {
    @Override
    public int compare(PowerProfileSegment o1, PowerProfileSegment o2) {
      return o1.getStartTime() - o2.getStartTime();
    }
  }

  private static class PowerCompare implements Comparator<PowerProfileSegment> {
    @Override
    public int compare(PowerProfileSegment o1, PowerProfileSegment o2) {
      int comp = Double.compare(o1.getPower(), o2.getPower());
      if (comp == 0) {
        return Integer.compare(o2.getDuration(), o1.getDuration());
      }

      return comp;
    }
  }
}
