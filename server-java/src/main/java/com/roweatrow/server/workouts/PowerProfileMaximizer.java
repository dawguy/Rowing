package com.roweatrow.server.workouts;

import com.roweatrow.server.model.Split;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PowerProfileMaximizer {
    List<PowerProfileSegment> unitSegmentList = new ArrayList<>();
    PowerProfileSegment[] maxDurationSegmentList;


    class PowerComparer implements Comparator<PowerProfileSegment> {
        public int compare(PowerProfileSegment a, PowerProfileSegment b){
            if(a.getPower() < b.getPower()) {
                return -1;
            }
            return 1;
        }
    }

    class StartTimeComparer implements Comparator<PowerProfileSegment> {
        public int compare(PowerProfileSegment a, PowerProfileSegment b){
            if(a.getStartTime() < b.getStartTime()) {
                return -1;
            }
            return 1;
        }
    }

    public List<PowerProfileSegment> calculateBestPower(List<PowerProfileSegment> unitSegmentList){
        this.unitSegmentList = unitSegmentList;
        int segmentCount = unitSegmentList.size();
        maxDurationSegmentList = new PowerProfileSegment[segmentCount+1];

        List<PowerProfileSegment> segmentList = unitSegmentList.stream()
                .map(s -> new PowerProfileSegment(s.getPower(), s.getStartTime(), s.getDuration()))
                .collect(Collectors.toList());

        int highestPowerIndex = getMaxPowerSegment(segmentList);
        maxDurationSegmentList[1] = new PowerProfileSegment(
                segmentList.get(highestPowerIndex).getPower(),
                segmentList.get(highestPowerIndex).getStartTime(),
                segmentList.get(highestPowerIndex).getDuration());

        while(segmentList.size() > 1) {
            int i = getMaxPowerSegment(segmentList);
            PowerProfileSegment powerProfileSegment = segmentList.get(i);
            int start = powerProfileSegment.getStartTime();
            int end = start + powerProfileSegment.getDuration();
            PowerProfileSegment left = i -1 >= 0 ? segmentList.get(i-1) : null;
            PowerProfileSegment right = i + 1 < segmentList.size() ? segmentList.get(i+1) : null;
            boolean joinLeft = powerProfileSegment.joinLeft(left, right);

            if(joinLeft){
                int joinStart = left.getStartTime();
                int joinEnd = joinStart + left.getDuration();

                updateMaxDurations(powerProfileSegment, joinStart, joinEnd);
                PowerProfileSegment updatedSegment = powerProfileSegment.join(left);
                segmentList.set(i, updatedSegment);
                segmentList.remove(i-1);
            } else {
                int joinStart = start;
                int joinEnd = end + right.getDuration();

                updateMaxDurations(powerProfileSegment, joinStart, joinEnd);
                PowerProfileSegment updatedSegment = powerProfileSegment.join(right);
                segmentList.set(i, updatedSegment);
                segmentList.remove(i+1);
            }
        }

        List<PowerProfileSegment> list = new ArrayList<>(Arrays.asList(maxDurationSegmentList));
        list.remove(0);
        return list;
    }

    private int getMaxPowerSegment(List<PowerProfileSegment> powerProfileSegments){
        OptionalInt i = IntStream.range(0, powerProfileSegments.size())
                .reduce((a,b) -> powerProfileSegments.get(a).getPower() < powerProfileSegments.get(b).getPower() ? b : a);
        return i.isPresent() ? i.getAsInt() : -1;
    }

    private void updateMaxDurations(PowerProfileSegment existingSegment, int startIndex, int endIndex){
        PowerProfileSegment cur = new PowerProfileSegment(existingSegment.getPower(), existingSegment.getStartTime(), existingSegment.getDuration());

        // We're joining right
        if(existingSegment.getStartTime() == startIndex){
            for(int j = startIndex + existingSegment.getDuration(); j < endIndex; j++){
                PowerProfileSegment unitSegment = unitSegmentList.get(j);

                double updatedPower = cur.calculatePowerWithAddedSegment(unitSegment);
                int minStartTime = Math.min(cur.getStartTime(), unitSegment.getStartTime());
                int maxEndTime = Math.max(cur.getEndTime(), unitSegment.getEndTime());
                int totalDuration = maxEndTime - minStartTime;
                int durationIndex = totalDuration;

                cur = new PowerProfileSegment(updatedPower, minStartTime, totalDuration);

                if(maxDurationSegmentList[durationIndex] == null || maxDurationSegmentList[durationIndex].getPower() < updatedPower){
                    maxDurationSegmentList[durationIndex] = cur;
                }
            }
        } else {
            for(int j = endIndex - 1; j >= startIndex; j--){
                PowerProfileSegment unitSegment = unitSegmentList.get(j);

                double updatedPower = cur.calculatePowerWithAddedSegment(unitSegment);
                int minStartTime = Math.min(cur.getStartTime(), unitSegment.getStartTime());
                int maxEndTime = Math.max(cur.getEndTime(), unitSegment.getEndTime());
                int totalDuration = maxEndTime - minStartTime;
                int durationIndex = totalDuration;

                cur = new PowerProfileSegment(updatedPower, minStartTime, totalDuration);

                if(maxDurationSegmentList[durationIndex] == null || maxDurationSegmentList[durationIndex].getPower() < updatedPower){
                    maxDurationSegmentList[durationIndex] = cur;
                }
            }
        }
    }
}
