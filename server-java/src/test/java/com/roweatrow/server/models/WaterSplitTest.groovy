package com.roweatrow.server.models

import spock.lang.Specification

class WaterSplitTest extends Specification {
    def "GetHeartRate"() {
        expect:
        WaterSplit waterSplit = new WaterSplit(waterWorkoutAthleteSplit: athleteSplits)
        expected == waterSplit.getHeartRate()

        where:
        athleteSplits | expected
        [new WaterWorkoutAthleteSplit(heartRate:15L), new WaterWorkoutAthleteSplit(heartRate:5L)]  | 10L
        [new WaterWorkoutAthleteSplit(heartRate:5L)]                                               | 5L
        []                                                                                         | 0L
        [new WaterWorkoutAthleteSplit(heartRate:null), new WaterWorkoutAthleteSplit(heartRate:5L)] | 5L
    }

    def "GetPower"(WaterWorkoutAthleteSplit[] athleteSplits, Long expected) {
        expect:
        WaterSplit waterSplit = new WaterSplit(waterWorkoutAthleteSplit: athleteSplits)
        expected == waterSplit.getPower()

        where:
        athleteSplits | expected
        [new WaterWorkoutAthleteSplit(power:15L), new WaterWorkoutAthleteSplit(power:5L)]  | 10L
        [new WaterWorkoutAthleteSplit(power:5L)]                                           | 5L
        []                                                                                 | 0L
        [new WaterWorkoutAthleteSplit(power:null), new WaterWorkoutAthleteSplit(power:5L)] | 5L
    }
}
