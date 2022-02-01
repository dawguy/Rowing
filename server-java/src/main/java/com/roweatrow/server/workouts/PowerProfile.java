package com.roweatrow.server.workouts;


import com.roweatrow.server.models.Split;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Getter
public class PowerProfile {
    private List<PowerProfileSegment> splitSegmentList;
    private List<PowerProfileSegment> powerProfileSegmentList;

    public PowerProfile(List<PowerProfileSegment> splitSegmentList, List<PowerProfileSegment> powerProfileSegmentList){
        this.splitSegmentList = splitSegmentList;
        this.powerProfileSegmentList = powerProfileSegmentList;
    }

    public int getDuration(){
        return powerProfileSegmentList.size();
    }

    public double getPower(int duration){
        if(duration > getDuration() || duration <= 0) throw new IndexOutOfBoundsException();

        return powerProfileSegmentList.get(duration).getPower();
    }
}
