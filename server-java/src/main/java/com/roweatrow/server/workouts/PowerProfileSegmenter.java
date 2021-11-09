package com.roweatrow.server.workouts;

import com.roweatrow.server.model.Split;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class PowerProfileSegmenter {

    // The goal of splitting splits to unit duration segments is that it will allow precise calculation of average power
    // at any point in time without any additional algorithmic complexity. Using non-unit segments would allow you to
    // still calculate the correct maximums per time duration, but the in-between averages wouldn't be calculable.
    public List<PowerProfileSegment> splitsToUnitPowerProfileSegments(List<? extends Split> splits){
        List<PowerProfileSegment> powerProfileSegments = new ArrayList<>();
        int currentStartTime = 0;

        for(Split s : splits){
            Duration d = s.getDuration();
            long l = d.getSeconds();
            int power = s.getPower();

            for(int i = 0; i < l; i++){
                PowerProfileSegment p = new PowerProfileSegment(power, currentStartTime, 1);
                currentStartTime++;
                powerProfileSegments.add(p);
            }
        }

        return powerProfileSegments;
    }

}
