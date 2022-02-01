package com.roweatrow.server.workouts

import com.roweatrow.server.models.ErgSplit
import com.roweatrow.server.models.Split
import spock.lang.Specification
import spock.lang.Subject

import java.time.Duration

class PowerProfileSegmenterTest extends Specification {
    @Subject PowerProfileSegmenter powerProfileSegmenter = new PowerProfileSegmenter();

    def "Unit Power Profile Of Empty List Of Splits"(){
        given: "An empty list of splits"
        List<Split> splits = []
        when: "splitsToUnitPowerProfileSegments is called"
        List<PowerProfileSegment> results = powerProfileSegmenter.splitsToUnitPowerProfileSegments(splits);
        then:
        0 == results.size()
    }

    def "Unit Power Profile Of A Single Split"(){
        given: "An a split of power 100 and duration of 10 seconds"
        List<Split> splits = [new ErgSplit(duration: Duration.ofSeconds(60), power: 100)]
        when: "splitsToUnitPowerProfileSegments is called"
        List<PowerProfileSegment> results = powerProfileSegmenter.splitsToUnitPowerProfileSegments(splits);
        then:
        60 == results.size()
        var firstSplit = results.first()
        1 == firstSplit.getDuration()
        100 == firstSplit.getPower()
    }

    def "Unit Power Profile Of A Multiple Splits"(){
        given: "An a split of power 100 and duration of 10 seconds"
        List<Split> splits = [
            new ErgSplit(duration: Duration.ofSeconds(60), power: 100),
            new ErgSplit(duration: Duration.ofSeconds(30), power: 300)
        ]

        when: "splitsToUnitPowerProfileSegments is called"
        List<PowerProfileSegment> results = powerProfileSegmenter.splitsToUnitPowerProfileSegments(splits);
        then:
        90 == results.size()
        var firstSplit = results.first()
        1 == firstSplit.getDuration()
        100 == firstSplit.getPower()
        var finalSplit = results.last()
        1 == finalSplit.getDuration()
        300 == finalSplit.getPower()
    }
}
