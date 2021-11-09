package com.roweatrow.server.workouts;


import com.roweatrow.server.model.Split;

import java.util.List;

public class PowerProfile {
    private final PowerProfileSegmenter powerProfileSegmenter;
    private final PowerProfileMaximizer powerProfileMaximizer;

    private List<PowerProfileSegment> splitSegmentList;
    private List<PowerProfileSegment> powerProfileSegmentList;


    public PowerProfile(PowerProfileSegmenter powerProfileSegmenter, PowerProfileMaximizer powerProfileMaximizer){
        this.powerProfileSegmenter = powerProfileSegmenter;
        this.powerProfileMaximizer = powerProfileMaximizer;
    }

    public List<PowerProfileSegment> setSplits(List<? extends Split> splits){
        splitSegmentList = powerProfileSegmenter.splitsToUnitPowerProfileSegments(splits);
        return splitSegmentList;
    }

    public List<PowerProfileSegment> calculateBestPower(){
        powerProfileSegmentList = powerProfileMaximizer.calculateBestPower(splitSegmentList);
        return powerProfileSegmentList;
    }

    public int getDuration(){
        return splitSegmentList.stream().map(PowerProfileSegment::getDuration).reduce(0, Integer::sum);
    }




}
