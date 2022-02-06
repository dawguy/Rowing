package com.roweatrow.server.workouts;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PowerProfileSegment {
  private final long powerSum;
  private final int startTime;
  private final int duration;
  private final int index = 0;

  public PowerProfileSegment(long powerSum, int startTime, int duration) {
    this.powerSum = powerSum;
    this.startTime = startTime;
    this.duration = duration;
  }

  public double getPower() {
    return powerSum / (double) duration;
  }

  public int compareStartTimeTo(PowerProfileSegment other) {
    if (other == null) return 1;

    if (this.getStartTime() < other.getStartTime()) {
      return -1;
    } else if (this.getStartTime() > other.getStartTime()) {
      return 1;
    } else {
      return 0;
    }
  }

  public int comparePowerTo(PowerProfileSegment other) {
    if (other == null) return 1;

    if (this.getPower() < other.getPower()) {
      return -1;
    } else if (this.getPower() > other.getPower()) {
      return 1;
    } else {
      return 0;
    }
  }

  public int compareDurationTo(PowerProfileSegment other) {
    if (other == null) return 1;

    if (this.getDuration() < other.getDuration()) {
      return -1;
    } else if (this.getDuration() > other.getDuration()) {
      return 1;
    } else {
      return 0;
    }
  }

  public PowerProfileSegment join(PowerProfileSegment other) {
    int newStartTime = Math.min(this.getStartTime(), other.getStartTime());
    int newDuration = this.getDuration() + other.getDuration();
    long newPowerSum = calculatePowerSumWithAddedSegment(other.getPowerSum());

    return new PowerProfileSegment(newPowerSum, newStartTime, newDuration);
  }

  // Higher average (this.power) can be used for joining. But in order to get second by second power
  // averages,
  // the lower power needs to be added split by split. So we're gonna need to track
  public PowerProfileSegment joinWith(
      PowerProfileSegment leftSegment, PowerProfileSegment rightSegment) {
    if (leftSegment == null && rightSegment == null) {
      return this;
    }
    if (leftSegment == null) {
      leftSegment = rightSegment;
      rightSegment = null;
    }

    PowerProfileSegment toJoinSegment;

    toJoinSegment = joinLeft(leftSegment, rightSegment) ? leftSegment : rightSegment;
    return this.join(toJoinSegment);
  }

  public double calculatePowerWithAddedSegment(PowerProfileSegment powerProfileSegment) {
    int newDuration = this.getDuration() + powerProfileSegment.getDuration();
    double newAvgPower =
        ((this.getDuration() * this.getPower())
                + (powerProfileSegment.getDuration() * powerProfileSegment.getPower()))
            / newDuration;

    return newAvgPower;
  }

  public long calculatePowerSumWithAddedSegment(long powerSum) {
    return this.getPowerSum() + powerSum;
  }

  public double calculatePowerWithAddedSegment(long powerSum, int duration) {
    return (double) (this.getPowerSum() + powerSum) / (this.getDuration() + duration);
  }

  public boolean joinLeft(PowerProfileSegment leftSegment, PowerProfileSegment rightSegment) {
    // may need to throw an runtime exception if left and right are both null. For now assuming
    // only 1 is null is good enough for the normal flow.
    if (leftSegment == null) {
      return false;
    }

    if (leftSegment.comparePowerTo(rightSegment) == 0) {
      return leftSegment.compareDurationTo(rightSegment) >= 0;
    } else {
      return leftSegment.comparePowerTo(rightSegment) >= 0;
    }
  }

  public int getEndTime() {
    return this.startTime + this.duration;
  }
}
