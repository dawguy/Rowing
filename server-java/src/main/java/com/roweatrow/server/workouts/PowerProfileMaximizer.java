package com.roweatrow.server.workouts;

import com.roweatrow.server.models.Split;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PowerProfileMaximizer {
  private List<PowerProfileSegment> unitSegmentList = new ArrayList<>();
  private PowerProfileSegment[] maxDurationSegmentList;

  static int[] timesChecked;

  class PowerComparer implements Comparator<PowerProfileSegment> {
    public int compare(PowerProfileSegment a, PowerProfileSegment b) {
      if (a.getPower() < b.getPower()) {
        return -1;
      }
      return 1;
    }
  }

  class StartTimeComparer implements Comparator<PowerProfileSegment> {
    public int compare(PowerProfileSegment a, PowerProfileSegment b) {
      if (a.getStartTime() < b.getStartTime()) {
        return -1;
      }
      return 1;
    }
  }

  public List<PowerProfileSegment> calculateBestPower(List<PowerProfileSegment> unitSegmentList) {
    this.unitSegmentList = unitSegmentList;
    int segmentCount = unitSegmentList.size();
    maxDurationSegmentList = new PowerProfileSegment[segmentCount + 1];
    timesChecked = new int[segmentCount + 1];

    if (segmentCount == 0) {
      return new ArrayList<>();
    }

    List<PowerProfileSegment> segmentList =
        unitSegmentList.stream()
            .map(s -> new PowerProfileSegment(s.getPowerSum(), s.getStartTime(), s.getDuration()))
            .collect(Collectors.toList());

    PowerProfileOrderedSet powerProfileOrderedSet = new PowerProfileOrderedSet();
    powerProfileOrderedSet.addAll(segmentList);

    PowerProfileSegment maxPowerProfileSegment = powerProfileOrderedSet.getMaxPower();
    maxDurationSegmentList[1] =
        new PowerProfileSegment(
            maxPowerProfileSegment.getPowerSum(),
            maxPowerProfileSegment.getStartTime(),
            maxPowerProfileSegment.getDuration());

    while (powerProfileOrderedSet.size() > 1) {
      PowerProfileSegment powerProfileSegment = powerProfileOrderedSet.getMaxPower();
      // Should never be null due to the size > 1 comparison above
      PowerProfileSegment neighborPowerProfileSegment =
          powerProfileOrderedSet.getHigherPowerTimeNeighbor(powerProfileSegment);
      updateMaxDurations(powerProfileSegment, neighborPowerProfileSegment);

      PowerProfileSegment updatedSegment = powerProfileSegment.join(neighborPowerProfileSegment);
      powerProfileOrderedSet.remove(powerProfileSegment);
      powerProfileOrderedSet.remove(neighborPowerProfileSegment);
      powerProfileOrderedSet.add(updatedSegment);
    }

    List<PowerProfileSegment> list = new ArrayList<>(Arrays.asList(maxDurationSegmentList));
    list.remove(0);
    return list;
  }

  private void updateMaxDurations(
      PowerProfileSegment existingSegment, PowerProfileSegment neighborSegment) {
    long curPowerSum = existingSegment.getPowerSum();
    int curStartTime = existingSegment.getStartTime();
    int curDuration = existingSegment.getDuration();

    if (neighborSegment.getStartTime() > existingSegment.getStartTime()) {
      // We're joining right
      int start = neighborSegment.getStartTime();
      int end = neighborSegment.getEndTime();

      for (int j = start; j < end; j++) {
        PowerProfileSegment unitSegment = unitSegmentList.get(j);

        long updatedPowerSum = unitSegment.calculatePowerSumWithAddedSegment(curPowerSum);
        int minStartTime = Math.min(curStartTime, unitSegment.getStartTime());
        int maxEndTime = Math.max(curStartTime + curDuration, unitSegment.getEndTime());
        int totalDuration = maxEndTime - minStartTime;
        int durationIndex = totalDuration;

        curPowerSum = updatedPowerSum;
        curStartTime = minStartTime;
        curDuration = totalDuration;

        double averagePower = curPowerSum / (double) curDuration;

        timesChecked[durationIndex]++;
        if (maxDurationSegmentList[durationIndex] == null
            || maxDurationSegmentList[durationIndex].getPower() < averagePower) {
          maxDurationSegmentList[durationIndex] =
              new PowerProfileSegment(curPowerSum, curStartTime, curDuration);
        }
      }
    } else {
      // We're joining left
      int start = neighborSegment.getEndTime() - 1;
      int end = neighborSegment.getStartTime();
      for (int j = start; j >= end; j--) {
        PowerProfileSegment unitSegment = unitSegmentList.get(j);

        long updatedPowerSum = unitSegment.calculatePowerSumWithAddedSegment(curPowerSum);
        int minStartTime = Math.min(curStartTime, unitSegment.getStartTime());
        int maxEndTime = Math.max(curStartTime + curDuration, unitSegment.getEndTime());
        int totalDuration = maxEndTime - minStartTime;
        int durationIndex = totalDuration;

        curPowerSum = updatedPowerSum;
        curStartTime = minStartTime;
        curDuration = totalDuration;
        double averagePower = curPowerSum / (double) curDuration;

        timesChecked[durationIndex]++;
        if (maxDurationSegmentList[durationIndex] == null
            || maxDurationSegmentList[durationIndex].getPower() < averagePower) {
          maxDurationSegmentList[durationIndex] =
              new PowerProfileSegment(curPowerSum, curStartTime, curDuration);
        }
      }
    }
  }

  public static void printTimesChecked() {
    System.out.println(timesChecked.toString());
  }
}
